package com.example.home_crud.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer ID;
    private Date date;
    private Double cost;
    private List<Product> products;
}
