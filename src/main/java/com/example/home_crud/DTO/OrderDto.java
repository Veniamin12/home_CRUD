package com.example.home_crud.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer id;
    private LocalDate date;
    private Double cost;
    private List<ProductDto> product;
}
