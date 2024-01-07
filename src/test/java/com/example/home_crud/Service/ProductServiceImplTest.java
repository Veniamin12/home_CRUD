package com.example.home_crud.Service;

import com.example.home_crud.DTO.ProductDto;
import com.example.home_crud.Mappers.ProductMapper;
import com.example.home_crud.Model.Product;
import com.example.home_crud.Repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    public static final int ORDER_ID = 1;
    @InjectMocks
    private ProductServiceImpl testInstance;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private Product product;
    @Mock
    private List<Product> productList;
    private ProductDto productDto;

    private List<ProductDto> productDtoList;

    @BeforeEach
    public void init() {
        productDto = new ProductDto();
        productDto.setId(1);
        productDto.setCost(5.44);
        productDto.setName("Toy");

        productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
    }

    @Test
    void shouldGetProductById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productMapper.productFromModel(product)).thenReturn(productDto);

        ProductDto result = testInstance.getProductById(ORDER_ID);

        verify(productRepository).findById(ORDER_ID);
        verify(productMapper).productFromModel(product);
        assertEquals(ORDER_ID, result.getId());
        assertNotNull(result);
    }

    @Test
    void shouldNotGetProductById() {
        assertThrows(NoSuchElementException.class, () -> {
            testInstance.getProductById(ORDER_ID);
        });
    }

    @Test
    void shouldGetProducts() {
        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.productFromModel(productList)).thenReturn(productDtoList);

        List<ProductDto> result = testInstance.getProducts();

        verify(productRepository).findAll();
        verify(productMapper).productFromModel(productList);
        assertEquals(productDtoList, result);
        assertNotNull(result);
    }

    @Test
    void shouldNotGetProducts() {
        when(productRepository.findAll()).thenReturn(null);

        List<ProductDto> result = testInstance.getProducts();

        verify(productRepository).findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAddProduct() {
        when(productMapper.productToModel(productDto)).thenReturn(product);

        testInstance.addProduct(productDto);

        verify(productMapper).productToModel(productDto);
        verify(productRepository).save(product);
        assertNotNull(product.getId());
    }

    @Test
    void shouldUpdateProduct() {
        when(productRepository.findById(ORDER_ID)).thenReturn(Optional.of(product));
        when(productMapper.productToModel(productDto, product)).thenReturn(product);

        testInstance.updateProduct(productDto, ORDER_ID);

        verify(productRepository).findById(ORDER_ID);
        verify(productMapper).productToModel(productDto, product);
        verify(productRepository).save(product);
        assertNotNull(product.getCost());
    }

    @Test
    void shouldNotUpdateProduct() {
        assertThrows(NoSuchElementException.class, () -> {
            testInstance.updateProduct(productDto, ORDER_ID);
        });
    }

    @Test
    void shouldDropProductById() {
        testInstance.dropProductById(ORDER_ID);

        verify(productRepository).deleteById(ORDER_ID);
    }

    @Test
    void shouldNotDropProductById() {
        doThrow(new RuntimeException("Exception of delete")).when(productRepository).deleteById(ORDER_ID);

        RuntimeException result = assertThrows(RuntimeException.class, () -> testInstance.dropProductById(ORDER_ID));

        verify(productRepository).deleteById(ORDER_ID);
        assertEquals("Exception of delete", result.getMessage());
    }
}