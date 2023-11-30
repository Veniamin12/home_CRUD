package com.example.home_crud.Service;


import com.example.home_crud.DTO.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return Optional.ofNullable(orderList.get(id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public void addOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public void upgradeOrder(Integer id, Integer date) {
        Order order = orderList.get(id);
        order.setDate(date);
    }

    @Override
    public void dropOrder(Integer id) {
        orderList.remove(id);

    }
}
