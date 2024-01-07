package com.example.home_crud.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate date;
    private Double cost;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product;
}
