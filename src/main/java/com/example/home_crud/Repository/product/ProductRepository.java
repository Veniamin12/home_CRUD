package com.example.home_crud.Repository.product;

import com.example.home_crud.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {
}
