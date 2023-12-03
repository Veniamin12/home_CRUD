package com.example.home_crud.Controller;

import com.example.home_crud.DTO.Order;
import com.example.home_crud.Service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Optional<Order> getByID(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@RequestBody Order order) {
        orderService.upgradeOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void dropById(@PathVariable("id") Integer id) {
        orderService.dropOrder(id);
    }
}
