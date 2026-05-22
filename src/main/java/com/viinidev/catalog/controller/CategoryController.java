package com.viinidev.catalog.controller;

import com.viinidev.catalog.dto.CategoryRequest;
import com.viinidev.catalog.dto.CategoryResponse;
import com.viinidev.catalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@RequestBody @Valid CategoryRequest request) {
        return categoryService.create(request);
    }

    @GetMapping
    public List<CategoryResponse> list() {
        return categoryService.list();
    }
}
