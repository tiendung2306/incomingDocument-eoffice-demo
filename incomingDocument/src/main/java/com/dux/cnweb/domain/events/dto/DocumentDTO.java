package com.dux.cnweb.domain.events.dto;

import java.util.Date;
import java.util.UUID;

import com.dux.cnweb.domain.model.Document;

import lombok.Getter;

@Getter
public class DocumentDTO {
    private final UUID id;
    private final UUID creator;
    private final String notebook;
    private final String docType;
    private final int receivedDocumentNumber;
    private final String emergencyLevel;
    private final String referenceCode;
    private final Date receiveDate;
    private final Date dueDate;
    private final Date dateOfIssue;
    private final String issuingAgency;
    private final String signer;
    private final String signerPosition;
    private final String content;
    private final String note;
    private final String receiveType;

    public DocumentDTO(Document doc) {
        this.id = doc.getId();
        this.creator = doc.getCreator();
        this.notebook = doc.getNotebook();
        this.docType = doc.getDocType().getValue();
        this.receivedDocumentNumber = doc.getReceivedDocumentNumber().getValue();
        this.emergencyLevel = doc.getEmergencyLevel().toString();
        this.referenceCode = doc.getReferenceCode();
        this.receiveDate = doc.getReceiveDate();
        this.dueDate = doc.getDueDate();
        this.dateOfIssue = doc.getDateOfIssue();
        this.issuingAgency = doc.getIssuingAgency();
        this.signer = doc.getSignerInfo().getSigner();
        this.signerPosition = doc.getSignerInfo().getSignerPosition();
        this.content = doc.getDocContent().getMainContent();
        this.note = doc.getDocContent().getNote();
        this.receiveType = doc.getReceiveType().toString();
    }

}
