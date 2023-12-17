package com.example.home_crud.Repository.product;

import com.example.home_crud.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
