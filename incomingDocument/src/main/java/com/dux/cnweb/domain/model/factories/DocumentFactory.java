package com.dux.cnweb.domain.model.factories;

import java.util.UUID;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import com.dux.cnweb.domain.events.DocumentCreated;
import com.dux.cnweb.domain.events.DocumentLogged;
import com.dux.cnweb.domain.model.Document;
import com.dux.cnweb.domain.model.valueObjects.DocContent;
import com.dux.cnweb.domain.model.valueObjects.DocType;
import com.dux.cnweb.domain.model.valueObjects.EmergencyLevel;
import com.dux.cnweb.domain.model.valueObjects.ReceiveType;
import com.dux.cnweb.domain.model.valueObjects.ReceivedDocumentNumber;
import com.dux.cnweb.domain.model.valueObjects.SignerInfo;

import jakarta.annotation.Nullable;

@Component
public class DocumentFactory {
    public Document createPaperDocument(UUID creator, String notebook, String docType,
            int receivedDocumentNumber, String emergencyLevel, String referenceCode,
            Date receiveDate, @Nullable Date dueDate, @Nullable Date dateOfIssue, String issuingAgency,
            @Nullable String signer, @Nullable String signerPosition, String content, @Nullable String note) {
        Document newDoc = new Document();
        newDoc.setId(UUID.randomUUID());
        newDoc.setCreator(creator);
        newDoc.setNotebook(notebook);
        newDoc.setDocType(new DocType(docType));
        newDoc.setReceivedDocumentNumber(new ReceivedDocumentNumber(receivedDocumentNumber));
        newDoc.setEmergencyLevel(EmergencyLevel.fromValue(emergencyLevel));
        newDoc.setReferenceCode(referenceCode);
        newDoc.setReceiveDate(receiveDate);
        newDoc.setDueDate(dueDate);
        newDoc.setDateOfIssue(dateOfIssue);
        newDoc.setIssuingAgency(issuingAgency);
        newDoc.setSignerInfo(new SignerInfo(signer, signerPosition));
        newDoc.setDocContent(DocContent.of(content, note));
        newDoc.setReceiveType(ReceiveType.PAPER);
        newDoc.addMultipleEvents(List.of(new DocumentCreated(newDoc), new DocumentLogged(newDoc.getId(), notebook)));
        return newDoc;
    }

    public Document createDigitalDocument(UUID creator, String docType, String emergencyLevel, 
            String referenceCode, Date receiveDate, @Nullable Date dueDate, 
            @Nullable Date dateOfIssue, String issuingAgency, @Nullable String signer, 
            @Nullable String signerPosition, String content, String note, String type) {
        Document newDoc = new Document();
        newDoc.setId(UUID.randomUUID());
        newDoc.setCreator(creator);
        newDoc.setDocType(new DocType(docType));
        newDoc.setEmergencyLevel(EmergencyLevel.fromValue(emergencyLevel));
        newDoc.setReferenceCode(referenceCode);
        newDoc.setReceiveDate(receiveDate);
        newDoc.setDueDate(dueDate);
        newDoc.setDateOfIssue(dateOfIssue);
        newDoc.setIssuingAgency(issuingAgency);
        newDoc.setSignerInfo(new SignerInfo(signer, signerPosition));
        newDoc.setDocContent(DocContent.of(content, note));
        newDoc.setReceiveType(type == "external" ? ReceiveType.EXTERNAL_DIGITAL : ReceiveType.INTERNAL_DIGITAL);
        newDoc.addMultipleEvents(List.of(new DocumentCreated(newDoc)));
        return newDoc;
    }
}
