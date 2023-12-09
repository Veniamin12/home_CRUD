package com.example.home_crud.Repository.jdbc;

import com.example.home_crud.DTO.Product;
import com.example.home_crud.Repository.mappers.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id= ";
    private final String SELECT_ALL_PRODUCT = "SELECT* FROM product";
    private final String SAVE_NEW_PRODUCT = "INSERT INTO product (id, name, cost)\n" +
            "VALUES (?,?,?);";
    private final String UPDATE_PRODUCT = " UPDATE product\n" +
            "SET name =?,\n" +
            "cost=?\n" +
            "WHERE id= ";
    private final String DELETE_BY_ID_PRODUCT = "DELETE  FROM product\n" +
            "WHERE id= ";

    public Product getById(Integer id) {
        Product result = jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID + id, new ProductRowMapper());
        return result;
    }

    public List<Product> getProducts() {
        List<Product> productList = jdbcTemplate.query(SELECT_ALL_PRODUCT, new ProductRowMapper());
        return productList;
    }

    public void saveProduct(Product product) {
        jdbcTemplate.update(SAVE_NEW_PRODUCT, product.getId(), product.getName(), product.getCost());
    }

    public void upgradeProduct(Product product,Integer id) {
        jdbcTemplate.update(UPDATE_PRODUCT +id, product.getName(), product.getCost());
    }

    public void deleteProduct(Integer id) {
        jdbcTemplate.update(DELETE_BY_ID_PRODUCT + id);
    }
}
