package com.example.home_crud.Service;

import com.example.home_crud.Converter.ProductConverter;
import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Model.Product;
import com.example.home_crud.Repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productConverter.productFromModel(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return productConverter.productFromModel(products);
    }

    @Override
    public void addProduct(ProductDto productdto) {
       Product product = productConverter.productToModel(productdto);
       productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDto productdto, Integer id) {
       Product old = productRepository.findById(id).orElseThrow();
       Product update = productConverter.productToModel(productdto, old);
       productRepository.save(update);


    }

    @Override
    public void dropProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
