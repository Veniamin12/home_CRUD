package com.example.home_crud.Mappers;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDto productFromModel(Product product);

    List<ProductDto> productFromModel(Iterable<Product> productList);

    Product productToModel(ProductDto productDto);

    @Mappings({
            @Mapping(target = "id", source = "productDto.id"),
            @Mapping(target = "name", source = "productDto.name"),
            @Mapping(target = "cost", source = "productDto.cost")
    })
    Product productToModel(ProductDto productDto, Product product);



    List<Product> toProductList (List<ProductDto> dtos);

    List<ProductDto> toProductDtoList (List<Product> products);

}
