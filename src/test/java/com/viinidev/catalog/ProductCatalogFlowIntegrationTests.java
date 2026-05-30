package com.viinidev.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductCatalogFlowIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCategoryCreateProductAndSearchWithPagination() throws Exception {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        long categoryId = createCategory("Notebooks " + suffix);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Notebook Pro %s",
                                  "description": "Notebook para desenvolvimento backend",
                                  "price": 4500.00,
                                  "stock": 8,
                                  "categoryId": %d
                                }
                                """.formatted(suffix, categoryId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Notebook Pro " + suffix))
                .andExpect(jsonPath("$.category.id").value(categoryId));

        mockMvc.perform(get("/api/products")
                        .param("search", suffix)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.content[0].name").value("Notebook Pro " + suffix));
    }

    @Test
    void shouldRejectProductWithInvalidPrice() throws Exception {
        long categoryId = createCategory("Perifericos " + UUID.randomUUID());

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Mouse",
                                  "description": "Mouse sem fio",
                                  "price": 0,
                                  "stock": 5,
                                  "categoryId": %d
                                }
                                """.formatted(categoryId)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void shouldListCreatedCategories() throws Exception {
        String categoryName = "Acessorios " + UUID.randomUUID();
        createCategory(categoryName);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == '%s')]".formatted(categoryName)).exists());
    }

    private long createCategory(String name) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "%s"
                                }
                                """.formatted(name)))
                .andExpect(status().isCreated())
                .andReturn();
        return objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }
}
