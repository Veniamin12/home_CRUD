package com.example.home_crud.Repository.mappers;

/*
public class OrderRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto order = new OrderDto();
        order.setId(rs.getInt("id"));
        order.setDate(rs.getDate("date"));
        order.setCost(rs.getDouble("cost"));


        List<ProductDto> product = new ArrayList<>();
        do{
                ProductDto product1 = new ProductDto();
                product1.setId(rs.getInt("id"));
                product1.setName(rs.getString("name"));
                product1.setCost(rs.getDouble("cost"));
                product.add(product1);

            } while (rs.next() && rs.getInt("id")== order.getId());
        order.setProduct(product);
        return order;
    }
}

*/