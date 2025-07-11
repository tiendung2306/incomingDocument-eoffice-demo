package com.dux.cnweb.application;

import com.dux.cnweb.application.commands.CreateDigitalDocumentCommand;
import com.dux.cnweb.application.commands.CreatePaperDocumentCommand;
import com.dux.cnweb.application.handlers.DocumentCommandHandler;
import com.dux.cnweb.domain.model.Document;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomingApplicationService {
    private final DocumentCommandHandler documentCommandHandler;

    public IncomingApplicationService(DocumentCommandHandler documentCommandHandler) {
        this.documentCommandHandler = documentCommandHandler;
    }

    public Document createPaperDocument(CreatePaperDocumentCommand command) {
        return documentCommandHandler.createPaperDoc(command);
    }

    public Document createDigitalDocument(CreateDigitalDocumentCommand command) {
        return documentCommandHandler.createDigitalDoc(command);
    }
}
