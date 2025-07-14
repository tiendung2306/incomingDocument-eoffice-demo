package com.dux.cnweb.application.ports;

import com.dux.cnweb.application.dto.DocumentDto;
import com.dux.cnweb.infrastructure.rest.dto.CreateDocumentRequest;
import com.dux.cnweb.infrastructure.rest.dto.UpdateDocumentRequest;

import java.util.List;

public interface DocumentController {
    String createDocument(CreateDocumentRequest request);

    DocumentDto getDocument(String documentId);

    List<DocumentDto> getAllDocuments();

    void updateDocument(String productId, UpdateDocumentRequest request);
}
