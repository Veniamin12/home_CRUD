package com.example.home_crud.Mappers;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = ProductMapper.class, componentModel = "spring")
public interface OrderMapper {

    OrderDto fromModel(Order order);

    Order toModel(OrderDto orderDto);

    @Mappings({
            @Mapping(target = "id", source = "orderDto.id"),
            @Mapping(target = "date", source = "orderDto.date"),
            @Mapping(target = "cost", source = "orderDto.cost"),
            @Mapping(target = "product", source = "orderDto.product")
    })
    Order toModel(OrderDto orderDto, Order order);

    List<OrderDto> fromModel(Iterable<Order> orders);

}
