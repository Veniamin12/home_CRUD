package com.example.home_crud.Controller;


import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Model.Order;
import com.example.home_crud.Model.Product;
import com.example.home_crud.Repository.order.OrderRepository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.coyote.Response;
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
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OrderControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    private Order orderFirst;
    private Order orderSecond;
    private Order orderThird;

    @BeforeEach
    public void init() {
        orderFirst = Order.builder()
                .date(LocalDate.now())
                .cost(45.6)
                .product(List.of(Product.builder()
                        .cost(67.7)
                        .name("Toy")
                        .build()))
                .build();

        orderSecond = Order.builder()
                .date(LocalDate.now())
                .cost(11.6)
                .product(List.of(Product.builder()
                        .cost(334.7)
                        .name("Car")
                        .build()))
                .build();

        orderThird = Order.builder()
                .date(LocalDate.now())
                .cost(11.68)
                .product(List.of(Product.builder()
                        .cost(33.666)
                        .name("Barbie")
                        .build()))
                .build();
    }
    @AfterEach
    public void cleanDataBase(){
        orderRepository.deleteAll();
    }

    @Test
    void shouldGetOrderById() {
        Order saveOrder = orderRepository.save(orderFirst);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("testuser", "password");
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<OrderDto> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/orders/" + saveOrder.getId(),
                HttpMethod.GET,
                entity,
                OrderDto.class
        );


        //  OrderDto result = restTemplate.getForObject("http://localhost:" + port + "/api/v1/orders/" + saveOrder.getId(), OrderDto.class);

        Assertions.assertEquals(saveOrder.getId(), response.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response);
    }

    @Test
    void shouldGetOrders() {
        Order saveFirst = orderRepository.save(orderFirst);
        Order saveSecond = orderRepository.save(orderSecond);
        Order saveThird = orderRepository.save(orderThird);

        ResponseEntity<List<OrderDto>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/v1/orders", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDto>>() {
                });
        List<OrderDto> result = responseEntity.getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(saveFirst.getId(), result.get(0).getId());
        Assertions.assertEquals(saveSecond.getId(), result.get(1).getId());
        Assertions.assertEquals(saveThird.getId(), result.get(2).getId());
    }

    @Test
    void shouldDropByid() {
        Order saveFirst = orderRepository.save(orderFirst);

        restTemplate.delete("http://localhost:" + port + "/api/v1/orders/delete/" + saveFirst.getId(), OrderDto.class);
        Optional<Order> result = orderRepository.findById(saveFirst.getId());

        Assertions.assertTrue(result.isEmpty());
    }
}