package com.dux.cnweb.application.handlers;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.dux.cnweb.application.commands.CreatePaperDocumentCommand;
import com.dux.cnweb.domain.model.Document;
import com.dux.cnweb.domain.model.factories.DocumentFactory;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentCommandHandler {
    private DocumentFactory factory;

    public DocumentCommandHandler(DocumentFactory factory) {
        this.factory = factory;
    }
    public Document createPaperDoc(CreatePaperDocumentCommand cmd) {
        return factory.createPaperDocument(cmd.creator(), cmd.notebook(), cmd.docType(), cmd.receivedDocumentNumber(),
         cmd.emergencyLevel(), cmd.referenceCode(), cmd.receiveDate(), cmd.dueDate(), cmd.dateOfIssue(),
          cmd.issuingAgency(), cmd.signer(), cmd.signerPosition(), cmd.content(), cmd.note());
    }
}
