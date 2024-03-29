package com.example.home_crud.Controller;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Service.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ActiveProfiles("test")
class OrderControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldGetOrderById() throws Exception {
        when(orderService.getOrderById(1)).thenReturn(OrderDto.builder()
                .id(1)
                .cost(55.5)
                .product(emptyList())
                .date(LocalDate.now())
                .build());

        this.mockMvc.perform(get("/api/v1/orders/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldGetOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(OrderDto.builder()
                .id(11)
                .cost(11.1)
                .product(emptyList())
                .build()));

        this.mockMvc.perform(get("/api/v1/orders")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldDropById() throws Exception {
        this.mockMvc.perform(delete("/api/v1/orders/delete/1")).andDo(print()).andExpect(status().isOk());
    }


}