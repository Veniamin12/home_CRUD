package com.example.home_crud.Service.order;

import com.example.home_crud.DTO.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto getOrderById(Integer id);

    List<OrderDto> getAllOrders();

    void addOrder(OrderDto orderdto);

    void upgradeOrder(OrderDto orderdto, Integer id);

    void dropOrder(Integer id);

}
