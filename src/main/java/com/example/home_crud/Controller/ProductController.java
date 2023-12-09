package com.example.home_crud.Controller;

import com.example.home_crud.DTO.Product;
import com.example.home_crud.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductById(@PathVariable("productId") Integer productId, @RequestBody Product product) {
        productService.updateProduct(product, productId);
    }

    @DeleteMapping("/delete/{id}")
    public void dropProductById(@PathVariable("id") Integer id) {
        productService.dropProductById(id);
    }
}
