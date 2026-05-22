package com.viinidev.catalog.service;

import com.viinidev.catalog.domain.Category;
import com.viinidev.catalog.dto.CategoryRequest;
import com.viinidev.catalog.dto.CategoryResponse;
import com.viinidev.catalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("Category already exists.");
        }
        return CategoryResponse.from(categoryRepository.save(new Category(request.name().trim())));
    }

    public List<CategoryResponse> list() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).toList();
    }
}
