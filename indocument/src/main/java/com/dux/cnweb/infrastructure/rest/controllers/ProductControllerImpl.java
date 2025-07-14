package com.dux.cnweb.infrastructure.rest.controllers;

import com.dux.cnweb.application.commands.CreateDocumentCommand;
import com.dux.cnweb.application.commands.UpdateProductCommand;
import com.dux.cnweb.application.dto.DocumentDto;
import com.dux.cnweb.application.handlers.DocumentCommandHandler;
import com.dux.cnweb.application.handlers.ProductQueryHandler;
import com.dux.cnweb.application.queries.GetAllDocumentsQuery;
import com.dux.cnweb.application.queries.GetProductQuery;
import com.dux.cnweb.application.ports.DocumentController;
import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import com.dux.cnweb.infrastructure.rest.dto.CreateDocumentRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateDocumentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Component
public class ProductControllerImpl implements DocumentController {

    private final DocumentCommandHandler commandHandler;
    private final ProductQueryHandler queryHandler;

    @PostMapping
    public ResponseEntity<String> createProductRest(@RequestBody CreateDocumentRequest request) {
        String productId = createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<DocumentDto> getProductRest(@PathVariable String productId) {
        DocumentDto product = getProduct(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllProductsRest() {
        List<DocumentDto> products = getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProductRest(
            @PathVariable String productId,
            @RequestBody UpdateDocumentRequest request) {
        updateProduct(productId, request);
        return ResponseEntity.ok().build();
    }

    // @Override
    // public String createProduct(CreateProductRequest request) {
    // CreateProductCommand command = CreateProductCommand.of(
    // request.getName(),
    // request.getPrice(),
    // request.getCurrency());

    // return commandHandler.handle(command);
    // }

    @Override
    public DocumentDto getProduct(String productId) {
        GetProductQuery query = new GetProductQuery(productId);
        return queryHandler.handle(query);
    }

    @Override
    public List<DocumentDto> getAllProducts() {
        GetAllDocumentsQuery query = new GetAllDocumentsQuery();
        return queryHandler.handle(query);
    }

    // @Override
    // public void updateProduct(String productId, UpdateProductRequest request) {
    // UpdateProductCommand command = new UpdateProductCommand(
    // productId,
    // Notebook.of(request.getName()),
    // DocumentPriority.of(request.getPrice(), request.getCurrency())
    // );

    // commandHandler.handle(command);
    // }
}
