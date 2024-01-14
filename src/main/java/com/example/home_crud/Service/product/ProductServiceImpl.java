package com.example.home_crud.Service.product;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Mappers.ProductMapper;
import com.example.home_crud.Model.Product;
import com.example.home_crud.Repository.product.ProductRepository;
import com.example.home_crud.Service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productMapper.productFromModel(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return productMapper.productFromModel(products);
    }

    @Override
    public void addProduct(ProductDto productdto) {
       Product product = productMapper.productToModel(productdto);
       productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDto productdto, Integer id) {
       Product old = productRepository.findById(id).orElseThrow();
       Product update = productMapper.productToModel(productdto, old);
       productRepository.save(update);


    }

    @Override
    public void dropProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
