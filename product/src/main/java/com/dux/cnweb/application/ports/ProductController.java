package com.dux.cnweb.application.ports;

import com.dux.cnweb.application.dto.ProductDto;
import com.dux.cnweb.infrastructure.rest.dto.CreateProductRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateProductRequest;

import java.util.List;

public interface ProductController {
    String createProduct(CreateProductRequest request);
    ProductDto getProduct(String productId);
    List<ProductDto> getAllProducts();
    void updateProduct(String productId, UpdateProductRequest request);
}
