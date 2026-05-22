package com.viinidev.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProductCatalogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApiApplication.class, args);
    }
}
