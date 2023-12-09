package com.example.home_crud.Service;

import com.example.home_crud.DTO.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer id);

    List<Product> getProducts();

    void addProduct(Product product);

    void updateProduct(Product product,Integer id);

    void dropProductById(Integer id);
}
