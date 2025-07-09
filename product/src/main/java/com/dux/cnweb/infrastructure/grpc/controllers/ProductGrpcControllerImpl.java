package com.dux.cnweb.infrastructure.grpc.controllers;

import com.dux.cnweb.application.dto.ProductDto;
import com.dux.cnweb.application.ports.ProductController;
import com.dux.cnweb.infrastructure.rest.dto.CreateProductRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductGrpcControllerImpl implements ProductController {

    @Override
    public String createProduct(CreateProductRequest request) {
        // gRPC implementation would go here
        return "gRPC createProduct not implemented";
    }

    @Override
    public ProductDto getProduct(String productId) {
        // gRPC implementation would go here
        return null; // Placeholder for gRPC response
    }

    @Override
    public List<ProductDto> getAllProducts() {
        // gRPC implementation would go here
        return List.of(); // Placeholder for gRPC response
    }

    @Override
    public void updateProduct(String productId, UpdateProductRequest request) {
        // gRPC implementation would go here
    }
}
