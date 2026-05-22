package com.viinidev.catalog.service;

import com.viinidev.catalog.domain.Product;
import com.viinidev.catalog.dto.ProductRequest;
import com.viinidev.catalog.dto.ProductResponse;
import com.viinidev.catalog.repository.CategoryRepository;
import com.viinidev.catalog.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse create(ProductRequest request) {
        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
        Product product = new Product(
                request.name().trim(),
                request.description().trim(),
                request.price(),
                request.stock(),
                category
        );
        return ProductResponse.from(productRepository.save(product));
    }

    @Cacheable(value = "products", key = "{#search, #pageable.pageNumber, #pageable.pageSize, #pageable.sort.toString()}")
    public Page<ProductResponse> list(String search, Pageable pageable) {
        Page<Product> page = search == null || search.isBlank()
                ? productRepository.findAll(pageable)
                : productRepository.findByNameContainingIgnoreCase(search, pageable);
        return page.map(ProductResponse::from);
    }
}
