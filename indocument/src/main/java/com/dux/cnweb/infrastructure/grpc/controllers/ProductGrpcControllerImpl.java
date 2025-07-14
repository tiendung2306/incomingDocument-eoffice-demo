package com.dux.cnweb.infrastructure.grpc.controllers;

import com.dux.cnweb.application.dto.DocumentDto;
import com.dux.cnweb.application.ports.DocumentController;
import com.dux.cnweb.infrastructure.rest.dto.CreateDocumentRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateDocumentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductGrpcControllerImpl implements DocumentController {

    @Override
    public String createProduct(CreateDocumentRequest request) {
        // gRPC implementation would go here
        return "gRPC createProduct not implemented";
    }

    @Override
    public DocumentDto getProduct(String productId) {
        // gRPC implementation would go here
        return null; // Placeholder for gRPC response
    }

    @Override
    public List<DocumentDto> getAllProducts() {
        // gRPC implementation would go here
        return List.of(); // Placeholder for gRPC response
    }

    @Override
    public void updateProduct(String productId, UpdateDocumentRequest request) {
        // gRPC implementation would go here
    }
}
