package com.example.home_crud.Service;

import com.example.home_crud.DTO.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDto getOrderById(Integer id);

    List<OrderDto> getAllOrders();

    void addOrder(OrderDto orderdto);

    void upgradeOrder(OrderDto orderdto, Integer id);

    void dropOrder(Integer id);

}
