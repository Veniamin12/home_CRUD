package com.example.home_crud.Service;


import com.example.home_crud.Converter.OrderConverter;
import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Model.Order;
import com.example.home_crud.Repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderConverter.fromModel(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return orderConverter.fromModel(orders);
    }

    @Override
    public void addOrder(OrderDto orderdto) {
        Order order = orderConverter.toModel(orderdto);
        orderRepository.save(order);
    }

    @Override
    public void upgradeOrder(OrderDto orderdto, Integer id) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderConverter.toModel(orderdto, old);
        orderRepository.save(updated);
    }

    @Override
    public void dropOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
