package com.viinidev.catalog.controller;

import com.viinidev.catalog.dto.ProductRequest;
import com.viinidev.catalog.dto.ProductResponse;
import com.viinidev.catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        return productService.create(request);
    }

    @GetMapping
    public Page<ProductResponse> list(@RequestParam(required = false) String search, Pageable pageable) {
        return productService.list(search, pageable);
    }
}
