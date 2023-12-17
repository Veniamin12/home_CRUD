package com.example.home_crud.Converter;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductConverter {

    public ProductDto productFromModel(Product product) {
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .cost(product.getCost())
                .build();
        return productDto;
    }

    public List<ProductDto> productFromModel(Iterable<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .cost(product.getCost())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public Product productToModel(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .cost(productDto.getCost())
                .build();
    }

    public Product productToModel(ProductDto productDto, Product product){
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        return product;
    }
}
