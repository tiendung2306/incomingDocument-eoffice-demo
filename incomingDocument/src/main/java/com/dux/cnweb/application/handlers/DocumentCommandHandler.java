package com.dux.cnweb.application.handlers;

import org.h2.schema.Domain;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.dux.cnweb.application.commands.CreatePaperDocumentCommand;
import com.dux.cnweb.application.commands.CreateDigitalDocumentCommand;
import com.dux.cnweb.domain.model.Document;
import com.dux.cnweb.domain.model.factories.DocumentFactory;
import com.dux.cnweb.domain.repositories.DocumentEventRepository;
import com.dux.cnweb.shared.domain.events.DomainEvent;
import com.dux.cnweb.shared.infrastructure.events.DomainEventPublisher;

@Component
public class DocumentCommandHandler {
    private DocumentFactory factory;
    private DomainEventPublisher eventPublisher;

    public DocumentCommandHandler(DocumentFactory factory, DomainEventPublisher eventPublisher) {
        this.factory = factory;
        this.eventPublisher = eventPublisher;
    }

    public Document createPaperDoc(CreatePaperDocumentCommand cmd) {
        Document doc = factory.createPaperDocument(cmd.creator(), cmd.notebook(), cmd.docType(), cmd.receivedDocumentNumber(),
         cmd.emergencyLevel(), cmd.referenceCode(), cmd.receiveDate(), cmd.dueDate(), cmd.dateOfIssue(),
          cmd.issuingAgency(), cmd.signer(), cmd.signerPosition(), cmd.content(), cmd.note());
        eventPublisher.publishEvents(doc.getDomainEvents());
        return doc;
    }

    public Document createDigitalDocument(CreateDigitalDocumentCommand cmd) {
        Document doc = factory.createDigitalDocument(cmd.creator(), cmd.docType(), cmd.emergencyLevel(),
                cmd.referenceCode(), cmd.receiveDate(), cmd.dueDate(), cmd.dateOfIssue(), cmd.issuingAgency(),
                cmd.signer(), cmd.signerPosition(), cmd.content(), cmd.note(), cmd.type());
        eventPublisher.publishEvents(doc.getDomainEvents());
        return doc;
    }
}
