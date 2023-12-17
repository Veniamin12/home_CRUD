package com.example.home_crud.Repository.jdbc;

import com.example.home_crud.DTO.OrderDto;
import com.example.home_crud.DTO.ProductDto;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
@Repository
@RequiredArgsConstructor
public class OrderJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_ORDER_BY_ID = "SELECT orders.id, orders.date, orders.cost, product.id as product, product.name, product.cost as product_cost\n" +
            "FROM orders\n" +
            "JOIN product ON orders.product = product.id\n" +
            "WHERE orders.id = ";
    private final String SELECT_ALL_ORDERS = "SELECT orders.id, orders.date, orders.cost, product.id as product, product.name, product.cost as product_cost\n" +
            "FROM orders\n" +
            "JOIN product ON orders.product = product.id";
    private final String SAVE_ORDER = "INSERT INTO orders VALUES (?,?,?);";
    private final String SAVE_PRODUCT = "INSERT INTO product VALUES (?,?,?);";
    private final String UPDATE_ORDER = " UPDATE orders\n" +
            "SET date =?,\n" +
            "cost=?\n" +
            "WHERE id= ";
    private final String UPDATE_PRODUCT = " UPDATE product\n" +
            "SET name =?,\n" +
            "cost=?\n" +
            "WHERE id= ";
    private final String DELETE_BY_ID_ORDER = "DELETE  FROM orders\n" +
            "WHERE id= ";
    private final String DELETE_BY_ID_PRODUCT = "DELETE  FROM product\n" +
            "WHERE id= ";


    public OrderDto getById(int id) {
        OrderDto result = jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID + id, new OrderRowMapper());
        return result;
    }

    public List<OrderDto> getAll() {
        List<OrderDto> orderList = jdbcTemplate.query(SELECT_ALL_ORDERS, new OrderRowMapper());
        return orderList;
    }

    public void saveOrder(OrderDto order) {
        jdbcTemplate.update(SAVE_ORDER, order.getId(), order.getDate(), order.getCost());

        List<ProductDto> products = order.getProduct();
        if (products != null && !products.isEmpty()) {
            for (ProductDto product : products) {
                jdbcTemplate.update(SAVE_PRODUCT, product.getId(), product.getName(), product.getCost());
            }
        }
    }

    public void updateById(OrderDto order, Integer id) {
        jdbcTemplate.update(UPDATE_ORDER +id, order.getDate(), order.getCost());

        List<ProductDto> products = order.getProduct();
        if (products != null && !products.isEmpty()) {
            for (ProductDto product : products) {
                jdbcTemplate.update(UPDATE_PRODUCT+id, product.getName(), product.getCost());
            }
        }
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE_BY_ID_ORDER + id);
        jdbcTemplate.update(DELETE_BY_ID_PRODUCT + id);
    }
}
*/