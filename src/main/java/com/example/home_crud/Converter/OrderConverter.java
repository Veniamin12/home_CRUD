package com.example.home_crud.Converter;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Model.Order;
import com.example.home_crud.Model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    public OrderDto fromModel(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .cost(order.getCost())
                .product(productsFromModel(order.getProduct()))
                .build();
    }

    public Order toModel(OrderDto orderDto) {
        return Order.builder()
                .date(orderDto.getDate())
                .cost(orderDto.getCost())
                .product(productsToModel(orderDto.getProduct()))
                .build();
    }
    public Order toModel(OrderDto orderDto, Order order){

        order.setDate(orderDto.getDate());
        order.setCost(orderDto.getCost());
        order.setProduct(productsToModel(orderDto.getProduct()));
        return  order;
    }

    public List<OrderDto> fromModel(Iterable<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(fromModel(order));
        }
        return orderDtos;
    }

    private List<ProductDto> productsFromModel(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductDto dto = ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .cost(product.getCost())
                        .build();
                productDtos.add(dto);
            }
        }
        return productDtos;
    }

    private List<Product> productsToModel(List<ProductDto> productDto) {
        List<Product> products = new ArrayList<>();
        if (!productDto.isEmpty()) {
            for (ProductDto dtos : productDto) {
                Product product = Product.builder()
                        .id(dtos.getId())
                        .name(dtos.getName())
                        .cost(dtos.getCost())
                        .build();
                products.add(product);
            }
        }
        return products;
    }
}
