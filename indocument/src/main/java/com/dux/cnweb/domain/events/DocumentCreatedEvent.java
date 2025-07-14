package com.dux.cnweb.domain.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;

import java.util.Date;

import com.dux.cnweb.domain.model.entities.DocumentId;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles;
import com.dux.cnweb.domain.model.valueobjects.DocumentIdentifier;
import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.DocumentType;
import com.dux.cnweb.domain.model.valueobjects.IssuingAgency;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import com.dux.cnweb.domain.model.valueobjects.SignatureInfo;

import lombok.Getter;

@Getter
public class DocumentCreatedEvent extends DomainEvent {
    private final String documentId;
    private final Notebook notebook;
    private final DocumentPriority documentPriority;
    private final DocumentType documentType;
    private final DocumentIdentifier documentIdentifier;
    private final Date recievedDate;
    private final Date dueDate;
    private final Date issuedDate;
    private final IssuingAgency issuingAgency;
    private final SignatureInfo signatureInfo;
    private final String documentSummary;
    private final String documentNote;
    private final DocumentFiles documentFiles;

    public DocumentCreatedEvent(DocumentId documentId, DocumentType documentType, Notebook notebook,
            DocumentPriority documentPriority, DocumentIdentifier documentIdentifier,
            Date recievedDate, Date dueDate, Date issuedDate, IssuingAgency issuingAgency,
            SignatureInfo signatureInfo, String documentSummary, String documentNote,
            DocumentFiles documentFiles) {
        super();
        this.documentId = documentId.toString();
        this.documentType = documentType;
        this.notebook = notebook;
        this.documentPriority = documentPriority;
        this.documentIdentifier = documentIdentifier;
        this.recievedDate = recievedDate;
        this.dueDate = dueDate;
        this.issuedDate = issuedDate;
        this.issuingAgency = issuingAgency;
        this.signatureInfo = signatureInfo;
        this.documentSummary = documentSummary;
        this.documentNote = documentNote;
        this.documentFiles = documentFiles;
    }
}
