package com.servisoft.product.service;

import com.servisoft.product.entities.ProductEntity;
import com.servisoft.product.exceptions.SimpleException;
import com.servisoft.product.jpa.repository.ProductJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceTest {

    @Mock
    private ProductJpaRepository jpaRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsTest() {
        final var list = new ArrayList<ProductEntity>();
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        list.add(product);
        Mockito.when(this.jpaRepository.findAll()).thenReturn(list);

        final var response = this.productService.getAllProducts();

        Assertions.assertEquals(response, list);
    }

    @Test
    void getProductByIdTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(product));

        final var response = this.productService.getProductById(1);

        Assertions.assertEquals(response, product);
    }

    @Test
    void getProductByIdWithEmptyIdTest() {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(product));

        Assertions.assertThrows(SimpleException.class, () -> this.productService.getProductById(0));
    }

    @Test
    void createProductTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        final var response = this.productService.createProduct(product);

        Assertions.assertEquals(response, product);
    }

    @Test
    void createProductWithEmptyNameTest()  {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.createProduct(product));
    }

    @Test
    void createProductWithEmptyPriceTest()  {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Name");
        product.setPrice(0);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.createProduct(product));
    }

    @Test
    void createProductWithEmptyStockTest()  {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Name");
        product.setPrice(1000);
        product.setStock(0);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.createProduct(product));
    }

    @Test
    void updateProductTest() throws SimpleException {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        final var response = this.productService.updateProduct(1, product);

        Assertions.assertEquals(response, product);
        Assertions.assertEquals(response.getId(), product.getId());
        Assertions.assertEquals(response.getName(), product.getName());
        Assertions.assertEquals(response.getPrice(), product.getPrice());
        Assertions.assertEquals(response.getStock(), product.getStock());
    }

    @Test
    void updateProductWithEmptyIdTest() {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Milk");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.updateProduct(0, product));
    }

    @Test
    void updateProductWithEmptyNameTest() {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("");
        product.setPrice(1000);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.updateProduct(1, product));
    }

    @Test
    void updateProductWithEmptyPriceTest() {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Name");
        product.setPrice(0);
        product.setStock(20);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.updateProduct(1, product));
    }

    @Test
    void updateProductWithEmptyStockTest() {
        final var product = new ProductEntity();
        product.setId(1);
        product.setName("Name");
        product.setPrice(1000);
        product.setStock(0);
        Mockito.when(this.jpaRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Assertions.assertThrows(SimpleException.class, () -> this.productService.updateProduct(1, product));
    }

    @Test
    void deleteProductTest() throws SimpleException {
        Mockito.doNothing().when(this.jpaRepository).deleteById(ArgumentMatchers.anyLong());

        this.productService.deleteProduct(1);

        Mockito.verify(this.jpaRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteProductWithEmptyIdTest() {
        Mockito.doNothing().when(this.jpaRepository).deleteById(ArgumentMatchers.anyLong());

        Assertions.assertThrows(SimpleException.class, () -> this.productService.deleteProduct(0));
    }
}
