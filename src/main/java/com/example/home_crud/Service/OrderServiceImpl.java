package com.example.home_crud.Service;


import com.example.home_crud.DTO.Order;
import com.example.home_crud.DTO.Product;
import com.example.home_crud.Repository.jdbc.OrderJDBCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderJDBCRepository orderJDBCRepository;

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return Optional.ofNullable(orderJDBCRepository.getById(id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderJDBCRepository.getAll();
    }

    @Override
    public void addOrder(Order order) {
        orderJDBCRepository.saveOrder(order);
    }

    @Override
    public void upgradeOrder(Order order, Integer id) {
        orderJDBCRepository.updateById(order,id);
    }

    @Override
    public void dropOrder(Integer id) {
        orderJDBCRepository.deleteById(id);
    }
}
