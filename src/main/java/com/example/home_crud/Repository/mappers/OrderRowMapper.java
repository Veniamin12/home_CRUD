package com.example.home_crud.Repository.mappers;

import com.example.home_crud.DTO.Order;
import com.example.home_crud.DTO.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setID(rs.getInt("id"));
        order.setDate(rs.getInt("date"));
        order.setCost((double) rs.getInt("cost"));

        Product product = new Product();
        product.setId(rs.getInt("product"));
        product.setName(rs.getString("name"));
        product.setCost((double) rs.getInt("cost"));

        order.setProduct(product);
        return order;
    }
}
