package com.dux.cnweb.application.handlers;

import com.dux.cnweb.application.commands.CreateDocumentCommand;
import com.dux.cnweb.application.commands.DistributeDocumentCommand;
import com.dux.cnweb.application.services.DocumentService;
import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.shared.infrastructure.events.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentCommandHandler {

    private final DocumentService documentService;
    private final DomainEventPublisher eventPublisher;

    public String handle(CreateDocumentCommand command) {
        Document document = documentService.processDocument(command);

        // Publish domain events
        eventPublisher.publishEvents(document.getDomainEvents());
        document.clearDomainEvents();

        return document.getDocumentId().getValue();
    }

    public String handle(DistributeDocumentCommand command) {
        Document document = documentService.processDocument(command);

        // Publish domain events
        eventPublisher.publishEvents(document.getDomainEvents());
        document.clearDomainEvents();

        return document.getDocumentId().getValue();
    }

    // public void handle(UpdateProductCommand command) {
    // Document product = productRepository.findById(command.getProductId())
    // .orElseThrow(() -> new RuntimeException("Product not found: " +
    // command.getProductId()));

    // product.update(command.getName(), command.getPrice());

    // productRepository.save(product);

    // // Publish domain events
    // eventPublisher.publishEvents(product.getDomainEvents());
    // product.clearDomainEvents();
    // }
}
