package com.dux.cnweb.domain.events.dto;

import java.util.Date;
import java.util.UUID;

import com.dux.cnweb.domain.model.Document;

import lombok.Getter;

@Getter
public class DocumentDTO {
    private UUID id;
    private UUID creator;
    private String notebook;
    private String docType;
    private int receivedDocumentNumber;
    private String emergencyLevel;
    private String referenceCode;
    private Date receiveDate;
    private Date dueDate;
    private Date dateOfIssue;
    private String issuingAgency;
    private String signer;
    private String signerPosition;
    private String content;
    private String note;
    private String receiveType;

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

    // public DocumentDTO() {

    // }
    
}
