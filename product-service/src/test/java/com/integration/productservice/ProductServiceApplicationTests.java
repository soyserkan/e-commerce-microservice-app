package com.integration.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.productservice.dto.ProductCreateRequest;
import com.integration.productservice.repository.ProductRepository;
import com.integration.productservice.repository.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:13.6-alpine")).withDatabaseName("test").withUsername("sa").withPassword("sa");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductCreateRequest productRequest = this.productResponseBuilder();
        String productResponseString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/create")
                        .contentType(MediaType.APPLICATION_JSON).content(productResponseString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
    }


    private static ProductCreateRequest productResponseBuilder() {
        return ProductCreateRequest.builder()
                .userId(1L)
                .categoryId(1L)
                .brandId(1L)
                .barcode("86943345222334")
                .title("Example Product")
                .status(true)
                .listPrice(12.90)
                .salePrice(10.90)
                .vatRate(18D)
                .currency("TRY")
                .quantity(120L)
                .description("Example Description")
                .shortDescription("Example Short Description").
                images(Arrays.asList("http://localhost:3000/testImages"))
                .mainProductCode("1234_5ABC")
                .modelCode("12345")
                .platformCode("platform123")
                .build();
    }
}
