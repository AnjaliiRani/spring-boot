package com.example.RedisForOrderManagementSystem.Service;

import com.example.RedisForOrderManagementSystem.dto.ProductDto;
import com.example.RedisForOrderManagementSystem.entities.Product;
import com.example.RedisForOrderManagementSystem.repository.ProductRepository;
import com.example.RedisForOrderManagementSystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    @Test
    void shouldReturnProductById(){
        Product product = new Product();
        product.setActive(true);
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(50000.0));

        ProductDto productDto = productService.getById(1L);
    }
}
