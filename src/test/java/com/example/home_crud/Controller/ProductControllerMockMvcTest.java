package com.example.home_crud.Controller;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ActiveProfiles("test")
class ProductControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldGetProductById() throws Exception {
        when(productService.getProductById(1)).thenReturn(ProductDto.builder()
                .id(1)
                .name("Blaster")
                .cost(33.7)
                .build());

        this.mockMvc.perform(get("/api/v1/products/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldGetAllProducts() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(ProductDto.builder()
                .id(1)
                .name("Blaster")
                .cost(33.7)
                .build()));

        this.mockMvc.perform(get("/api/v1/products")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "testuser",password = "naluvka",roles = "USER")
    void shouldDropPoductById() throws Exception {
        this.mockMvc.perform(delete("/api/v1/products/delete/1")).andDo(print()).andExpect(status().isOk());
    }


}