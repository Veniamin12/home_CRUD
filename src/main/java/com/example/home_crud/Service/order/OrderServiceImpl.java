package com.example.home_crud.Service.order;


import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Mappers.OrderMapper;
import com.example.home_crud.Model.Order;
import com.example.home_crud.Repository.order.OrderRepository;
import com.example.home_crud.Service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderMapper.fromModel(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return orderMapper.fromModel(orders);
    }

    @Override
    public void addOrder(OrderDto orderdto) {
        Order order = orderMapper.toModel(orderdto);
        orderRepository.save(order);
    }

    @Override
    public void upgradeOrder(OrderDto orderdto, Integer id) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderMapper.toModel(orderdto, old);
        orderRepository.save(updated);
    }

    @Override
    public void dropOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
