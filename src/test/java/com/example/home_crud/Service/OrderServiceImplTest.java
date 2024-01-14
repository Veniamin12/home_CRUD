package com.example.home_crud.Service;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.Mappers.OrderMapper;
import com.example.home_crud.Model.Order;
import com.example.home_crud.Repository.order.OrderRepository;
import com.example.home_crud.Service.order.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final int ORDER_ID = 1;
    @InjectMocks
    private OrderServiceImpl testInstance;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private Order order;
    @Mock
    private List<Order> orderList;

    private OrderDto orderDto;
    private List<OrderDto> orderDtoList;

    @BeforeEach
    public void init() {
        orderDto = new OrderDto();
        orderDto.setId(ORDER_ID);
        orderDto.setCost(7.33);
        LocalDate specificDate = LocalDate.of(1998, 5, 5);
        orderDto.setDate(specificDate);

        orderDtoList = new ArrayList<>();
        orderDtoList.add(orderDto);
    }

    @Test
    void shouldGetOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderMapper.fromModel(order)).thenReturn(orderDto);

        OrderDto result = testInstance.getOrderById(ORDER_ID);

        verify(orderRepository).findById(ORDER_ID);
        verify(orderMapper).fromModel(order);
        assertEquals(ORDER_ID, result.getId());
        assertNotNull(result);
    }

    @Test
    void shouldNotGetOrderById() {
        assertThrows(NoSuchElementException.class, () -> {
            testInstance.getOrderById(ORDER_ID);
        });
    }

    @Test
    void shouldGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(orderList);
        when(orderMapper.fromModel(orderList)).thenReturn(orderDtoList);

        List<OrderDto> result = testInstance.getAllOrders();

        verify(orderRepository).findAll();
        verify(orderMapper).fromModel(orderList);
        assertEquals(orderDtoList, result);
        assertNotNull(result);

    }

    @Test
    void shouldNotGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(null);

        List<OrderDto> result = testInstance.getAllOrders();

        verify(orderRepository).findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAddOrder() {
        when(orderMapper.toModel(orderDto)).thenReturn(order);

        testInstance.addOrder(orderDto);

        verify(orderMapper).toModel(orderDto);
        verify(orderRepository).save(order);
        assertNotNull(orderDto.getId());
    }

    @Test
    void shouldUpgradeOrder() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderMapper.toModel(orderDto, order)).thenReturn(order);

        testInstance.upgradeOrder(orderDto, ORDER_ID);

        verify(orderRepository).findById(ORDER_ID);
        verify(orderMapper).toModel(orderDto, order);
        verify(orderRepository).save(order);
        assertNotNull(order.getCost());
    }

    @Test
    void shouldNotUpgradeOrder() {
        assertThrows(NoSuchElementException.class, () -> {
            testInstance.upgradeOrder(orderDto, ORDER_ID);
        });
    }

    @Test
    void shouldDropOrder() {
        testInstance.dropOrder(ORDER_ID);

        verify(orderRepository).deleteById(ORDER_ID);
    }

    @Test
    void shouldNotDropOrder() {
        doThrow(new RuntimeException("Exception of delete")).when(orderRepository).deleteById(ORDER_ID);

        RuntimeException result = assertThrows(RuntimeException.class, () -> testInstance.dropOrder(ORDER_ID));

        verify(orderRepository).deleteById(ORDER_ID);
        assertEquals("Exception of delete", result.getMessage());
    }
}