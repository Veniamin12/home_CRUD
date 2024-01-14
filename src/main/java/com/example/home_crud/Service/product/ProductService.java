package com.example.home_crud.Service.product;

import com.example.home_crud.DTO.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto getProductById(Integer id);

    List<ProductDto> getProducts();

    void addProduct(ProductDto productdto);

    void updateProduct(ProductDto productdto, Integer id);

    void dropProductById(Integer id);
}
