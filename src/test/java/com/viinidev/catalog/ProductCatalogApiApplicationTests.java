package com.viinidev.catalog;

import com.viinidev.catalog.dto.CategoryRequest;
import com.viinidev.catalog.dto.ProductRequest;
import com.viinidev.catalog.service.CategoryService;
import com.viinidev.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductCatalogApiApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Test
    void shouldCreateAndSearchProduct() {
        var category = categoryService.create(new CategoryRequest("Notebooks"));
        productService.create(new ProductRequest(
                "Notebook Pro",
                "Notebook para desenvolvimento backend",
                BigDecimal.valueOf(4500),
                8,
                category.id()
        ));

        var result = productService.list("notebook", PageRequest.of(0, 10));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).name()).isEqualTo("Notebook Pro");
    }
}
