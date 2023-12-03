package com.example.home_crud.DTO;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Integer ID;
    private Integer date;
    private Double cost;

    private Product product;
}
