package com.example.home_crud.Service;

import com.example.home_crud.DTO.Order;
import com.example.home_crud.DTO.Product;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrderById(Integer id);

    List<Order> getAllOrders();

    void addOrder(Order order);

    void upgradeOrder(Order order, Integer id);

    void dropOrder(Integer id);

}
