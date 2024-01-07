package com.example.home_crud.Controller;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Model.Product;
import com.example.home_crud.Repository.product.ProductRepository;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private Product productFirst;
    private Product productSecond;

    @BeforeEach
    public void init() {
        productFirst = Product.builder()
                .name("Toy")
                .cost(55.55)
                .build();
        productSecond = Product.builder()
                .name("Coca-Cola")
                .cost(17.88)
                .build();
    }
    @AfterEach
    public  void cleanDataBase(){
        productRepository.deleteAll();;
    }

    @Test
    void shouldGetProductById() {
        Product saveproduct = productRepository.save(productFirst);

        ProductDto result = restTemplate.getForObject("http://localhost:" + port + "/api/v1/products/" + saveproduct.getId(), ProductDto.class);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(saveproduct.getId(), result.getId());
    }

    @Test
    void shouldGetAllProducts() {
        Product saveFirst = productRepository.save(productFirst);
        Product saveSecond = productRepository.save(productSecond);

        ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/v1/products", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {
                });
        List<ProductDto> result = responseEntity.getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2,result.size());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(saveFirst.getId(), result.get(0).getId());
        Assertions.assertEquals(saveSecond.getId(), result.get(1).getId());
    }

    @Test
    void shouldDropProductById() {
        Product saveFirst = productRepository.save(productFirst);

        restTemplate.delete("http://localhost:" + port + "/api/v1/products/delete/" + saveFirst.getId(), ProductDto.class);
        Optional<Product> result = productRepository.findById(saveFirst.getId());

        Assertions.assertTrue(result.isEmpty());

    }

}