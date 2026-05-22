package com.viinidev.catalog.dto;

import com.viinidev.catalog.domain.Category;

import java.io.Serializable;

public record CategoryResponse(Long id, String name) implements Serializable {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
