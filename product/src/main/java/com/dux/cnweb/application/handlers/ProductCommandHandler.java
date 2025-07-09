package com.dux.cnweb.application.handlers;

import com.dux.cnweb.application.commands.CreateProductCommand;
import com.dux.cnweb.application.commands.UpdateProductCommand;
import com.dux.cnweb.domain.model.aggregates.Product;
import com.dux.cnweb.domain.model.entities.ProductId;
import com.dux.cnweb.domain.repositories.ProductRepository;
import com.dux.cnweb.infrastructure.events.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandHandler {

    private final ProductRepository productRepository;
    private final DomainEventPublisher eventPublisher;

    public String handle(CreateProductCommand command) {
        ProductId productId = ProductId.newId();
        
        Product product = Product.create(productId, command.getName(), command.getPrice());
        
        productRepository.save(product);
        
        // Publish domain events
        eventPublisher.publishEvents(product.getDomainEvents());
        product.clearDomainEvents();
        
        return productId.getValue();
    }

    public void handle(UpdateProductCommand command) {
        Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + command.getProductId()));
        
        product.update(command.getName(), command.getPrice());
        
        productRepository.save(product);
        
        // Publish domain events
        eventPublisher.publishEvents(product.getDomainEvents());
        product.clearDomainEvents();
    }
}
