package com.dux.cnweb.infrastructure.rest.controllers;

import com.dux.cnweb.application.commands.CreateProductCommand;
import com.dux.cnweb.application.commands.UpdateProductCommand;
import com.dux.cnweb.application.dto.ProductDto;
import com.dux.cnweb.application.handlers.ProductCommandHandler;
import com.dux.cnweb.application.handlers.ProductQueryHandler;
import com.dux.cnweb.application.queries.GetAllProductsQuery;
import com.dux.cnweb.application.queries.GetProductQuery;
import com.dux.cnweb.application.ports.ProductController;
import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import com.dux.cnweb.infrastructure.rest.dto.CreateProductRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateProductRequest;
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
public class ProductControllerImpl implements ProductController {

    private final ProductCommandHandler commandHandler;
    private final ProductQueryHandler queryHandler;

    @PostMapping
    public ResponseEntity<String> createProductRest(@RequestBody CreateProductRequest request) {
        String productId = createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductRest(@PathVariable String productId) {
        ProductDto product = getProduct(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsRest() {
        List<ProductDto> products = getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProductRest(
            @PathVariable String productId,
            @RequestBody UpdateProductRequest request) {
        updateProduct(productId, request);
        return ResponseEntity.ok().build();
    }

    @Override
    public String createProduct(CreateProductRequest request) {
        CreateProductCommand command = CreateProductCommand.of(
                request.getName(),
                request.getPrice(),
                request.getCurrency()
        );
        
        return commandHandler.handle(command);
    }

    @Override
    public ProductDto getProduct(String productId) {
        GetProductQuery query = new GetProductQuery(productId);
        return queryHandler.handle(query);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        GetAllProductsQuery query = new GetAllProductsQuery();
        return queryHandler.handle(query);
    }

    @Override
    public void updateProduct(String productId, UpdateProductRequest request) {
        UpdateProductCommand command = new UpdateProductCommand(
                productId,
                ProductName.of(request.getName()),
                Price.of(request.getPrice(), request.getCurrency())
        );
        
        commandHandler.handle(command);
    }
}
