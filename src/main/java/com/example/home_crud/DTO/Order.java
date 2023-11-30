package com.example.home_crud.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer ID;
    private Integer date;
    private Double cost;
    private Product product;
}
