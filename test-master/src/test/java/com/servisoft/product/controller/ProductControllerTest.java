package com.servisoft.product.controller;

import com.servisoft.product.entities.ProductEntity;
import com.servisoft.product.exceptions.SimpleException;
import com.servisoft.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductTest() {
        final var list = new ArrayList<ProductEntity>();
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        list.add(product);
        Mockito.when(this.productService.getAllProducts()).thenReturn(list);

        final var response = this.productController.getAllProduct();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), list);
    }

    @Test
    void getProductByIdTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.productService.getProductById(ArgumentMatchers.anyLong())).thenReturn(product);

        final var response = this.productController.getProductById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), product);
    }

    @Test
    void createProductTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.productService.createProduct(ArgumentMatchers.any())).thenReturn(product);

        final var response = this.productController.createProduct(product);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), product);
    }

    @Test
    void updateProductTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.productService.updateProduct(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(product);

        final var response = this.productController.updateProduct(1, product);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody(), product);
    }

    @Test
    void deleteProductTest() throws SimpleException {
        Mockito.doNothing().when(this.productService).deleteProduct(ArgumentMatchers.anyLong());

        this.productController.deleteProduct(1);

        Mockito.verify(this.productService, Mockito.times(1)).deleteProduct(1);
    }
}
