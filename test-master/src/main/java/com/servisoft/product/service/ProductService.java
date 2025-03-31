package com.servisoft.product.service;

import com.servisoft.product.entities.ProductEntity;
import com.servisoft.product.exceptions.SimpleException;
import com.servisoft.product.jpa.repository.ProductJpaRepository;
import com.servisoft.product.utils.ValidateValuesUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductJpaRepository productRepository;

    public ProductService(final ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return this.productRepository.findAll();
    }

    public ProductEntity getProductById(final long id) throws SimpleException {
        ValidateValuesUtils.validateEmptyLong(id, "id");
        return this.productRepository.findById(id).orElse(null);
    }

    public ProductEntity createProduct(final ProductEntity productEntity) throws SimpleException {
        productEntity.setId(0);
        this.validateValues(productEntity);
        return this.productRepository.save(productEntity);
    }

    public ProductEntity updateProduct(final long id, final ProductEntity productEntity) throws SimpleException {
        ValidateValuesUtils.validateEmptyLong(id, "id");
        productEntity.setId(id);
        this.validateValues(productEntity);
        return this.productRepository.save(productEntity);
    }

    public void deleteProduct(final long id) throws SimpleException {
        ValidateValuesUtils.validateEmptyLong(id, "id");
        this.productRepository.deleteById(id);
    }

    private void validateValues(final ProductEntity productEntity) throws SimpleException {
        ValidateValuesUtils.validateEmptyString(productEntity.getName(), "name");
        ValidateValuesUtils.validateEmptyDouble(productEntity.getPrice(), "price");
        ValidateValuesUtils.validateEmptyInteger(productEntity.getStock(), "stock");
    }

}