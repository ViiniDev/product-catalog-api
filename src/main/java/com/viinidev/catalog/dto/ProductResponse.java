package com.viinidev.catalog.dto;

import com.viinidev.catalog.domain.Product;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        CategoryResponse category
) implements Serializable {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                CategoryResponse.from(product.getCategory())
        );
    }
}
