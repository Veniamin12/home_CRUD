package com.example.home_crud.Controller;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    @GetMapping("/{id}")
    public OrderDto getByID(@PathVariable("id") Integer id)  {
        log.debug("Received order by id: "+ id);
        return orderService.getOrderById(id);
    }

    @GetMapping()
    public List<OrderDto> getAll() {
        log.debug("Received all orders!");
        return orderService.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestBody OrderDto order) {
        log.debug("The new order has been created!");
        orderService.addOrder(order);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody OrderDto order) {
        log.debug("Order has been updated by id :"+ orderId);
        orderService.upgradeOrder(order, orderId);
    }

    @DeleteMapping("/delete/{id}")
    public void dropById(@PathVariable("id") Integer id) {
        log.debug("Order has been deleted by id :"+ id);
        orderService.dropOrder(id);
    }
}
