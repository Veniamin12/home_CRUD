package com.example.home_crud.Service;

import com.example.home_crud.DTO.Product;
import com.example.home_crud.Repository.jdbc.ProductJDBCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJDBCRepository productJDBCRepository;
    @Override
    public Product getProductById(Integer id) {
        return productJDBCRepository.getById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productJDBCRepository.getProducts();
    }

    @Override
    public void addProduct(Product product) {
      productJDBCRepository.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product,Integer id) {
      productJDBCRepository.upgradeProduct(product,id);
    }

    @Override
    public void dropProductById(Integer id) {
     productJDBCRepository.deleteProduct(id);
    }
}
