package com.example.home_crud.Repository.mappers;

import com.example.home_crud.DTO.Order;
import com.example.home_crud.DTO.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setID(rs.getInt("id"));
        order.setDate(rs.getDate("date"));
        order.setCost(rs.getDouble("cost"));


        List<Product> product = new ArrayList<>();
        do{
                Product product1 = new Product();
                product1.setId(rs.getInt("id"));
                product1.setName(rs.getString("name"));
                product1.setCost(rs.getDouble("cost"));
                product.add(product1);

            } while (rs.next() && rs.getInt("id")== order.getID());
        order.setProducts(product);
        return order;
    }
}

