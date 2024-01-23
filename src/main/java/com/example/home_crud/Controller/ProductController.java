package com.example.home_crud.Controller;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") Integer id) {
        log.debug("Received product by id:"+id);
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        log.debug("Received all products!");
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewProduct(@RequestBody ProductDto product) {
        log.debug("New product has been saved!");
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductById(@PathVariable("productId") Integer productId, @RequestBody ProductDto product) {
        log.debug("Product has been updated by id:"+productId);
        productService.updateProduct(product, productId);
    }

    @DeleteMapping("/delete/{id}")
    public void dropProductById(@PathVariable("id") Integer id) {
        log.debug("Product has been deleted by id:"+ id);
        productService.dropProductById(id);
    }
}
