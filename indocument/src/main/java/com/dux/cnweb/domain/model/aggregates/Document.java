package com.dux.cnweb.domain.model.aggregates;

import com.dux.cnweb.shared.domain.model.AggregateRoot;

import jakarta.persistence.Access;

import com.dux.cnweb.domain.events.DocumentAclUpdatedEvent;
import com.dux.cnweb.domain.events.DocumentCreatedEvent;
import com.dux.cnweb.domain.events.DocumentUpdatedEvent;
import com.dux.cnweb.domain.model.entities.DocumentId;

import java.security.Signature;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dux.cnweb.domain.model.valueobjects.AccessControlList;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles;
import com.dux.cnweb.domain.model.valueobjects.DocumentIdentifier;
import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.DocumentType;
import com.dux.cnweb.domain.model.valueobjects.IssuingAgency;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import com.dux.cnweb.domain.model.valueobjects.SignatureInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Document extends AggregateRoot {
    private DocumentId documentId;
    private List<AccessControlList> aclList;
    private DocumentType documentType;
    private Notebook notebook;
    private DocumentPriority documentPriority;
    private DocumentIdentifier documentIdentifier;
    private Date recievedDate;
    private Date dueDate;
    private Date issuedDate;
    private IssuingAgency issuingAgency;
    private SignatureInfo signatureInfo;
    private String documentSummary;
    private String documentNote;
    private DocumentFiles documentFiles;

    public static Document create(DocumentId documentId, DocumentType documentType, Notebook notebook,
            DocumentPriority documentPriority, DocumentIdentifier documentIdentifier,
            Date recievedDate, Date dueDate, Date issuedDate, IssuingAgency issuingAgency,
            SignatureInfo signatureInfo, String documentSummary, String documentNote,
            DocumentFiles documentFiles) {
        Document document = new Document();
        document.documentId = documentId;
        document.documentType = documentType;
        document.notebook = notebook;
        document.documentPriority = documentPriority;
        document.documentIdentifier = documentIdentifier;
        document.recievedDate = recievedDate;
        document.dueDate = dueDate;
        document.issuedDate = issuedDate;
        document.issuingAgency = issuingAgency;
        document.signatureInfo = signatureInfo;
        document.documentSummary = documentSummary;
        document.documentNote = documentNote;
        document.documentFiles = documentFiles;

        document.addDomainEvent(new DocumentCreatedEvent(documentId, documentType, notebook,
                documentPriority, documentIdentifier,
                recievedDate, dueDate, issuedDate, issuingAgency,
                signatureInfo, documentSummary, documentNote,
                documentFiles));

        return document;
    }

    // public Document update(DocumentId documentId, DocumentType documentType,
    // Notebook notebook,
    // DocumentPriority documentPriority, DocumentIdentifier documentIdentifier,
    // Date recievedDate, Date dueDate, Date issuedDate, IssuingAgency
    // issuingAgency,
    // SignatureInfo signatureInfo, String documentSummary, String documentNote,
    // DocumentFiles documentFiles) {
    // if (documentId != null)
    // this.documentId = documentId;
    // if (documentType != null)
    // this.documentType = documentType;
    // if (notebook != null)
    // this.notebook = notebook;
    // if (documentPriority != null)
    // this.documentPriority = documentPriority;
    // if (documentIdentifier != null)
    // this.documentIdentifier = documentIdentifier;
    // if (recievedDate != null)
    // this.recievedDate = recievedDate;
    // if (dueDate != null)
    // this.dueDate = dueDate;
    // if (issuedDate != null)
    // this.issuedDate = issuedDate;
    // if (issuingAgency != null)
    // this.issuingAgency = issuingAgency;
    // if (signatureInfo != null)
    // this.signatureInfo = signatureInfo;
    // if (documentSummary != null)
    // this.documentSummary = documentSummary;
    // if (documentNote != null)
    // this.documentNote = documentNote;
    // if (documentFiles != null)
    // this.documentFiles = documentFiles;
    // this.addDomainEvent(new DocumentUpdatedEvent(
    // this.documentId.toString(),
    // this.documentType,
    // this.notebook,
    // this.documentPriority,
    // this.documentIdentifier,
    // this.recievedDate,
    // this.dueDate,
    // this.issuedDate,
    // this.issuingAgency,
    // this.signatureInfo,
    // this.documentSummary,
    // this.documentNote,
    // this.documentFiles));

    // return this;
    // }

    public void updateAccessControlList(List<AccessControlList> acl) {
        if (aclList == null) {
            return;
        }
        aclList.addAll(acl);
        this.addDomainEvent(new DocumentAclUpdatedEvent(acl));
    }

}
