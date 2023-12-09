package com.example.home_crud.Controller;

import com.example.home_crud.DTO.Order;
import com.example.home_crud.DTO.Product;
import com.example.home_crud.Service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Optional<Order> getByID(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody Order order) {
        orderService.upgradeOrder(order, orderId);
    }

    @DeleteMapping("/delete/{id}")
    public void dropById(@PathVariable("id") Integer id) {
        orderService.dropOrder(id);
    }
}
