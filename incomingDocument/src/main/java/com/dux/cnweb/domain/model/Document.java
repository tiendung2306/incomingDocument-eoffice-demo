package com.dux.cnweb.domain.model;

import com.dux.cnweb.domain.events.DocumentLogged;
import com.dux.cnweb.domain.events.DocumentRecalled;
import com.dux.cnweb.domain.events.DocumentSigned;
import com.dux.cnweb.domain.model.exceptions.InvalidReceiveTypeException;
import com.dux.cnweb.domain.model.valueObjects.DocContent;
import com.dux.cnweb.domain.model.valueObjects.DocType;
import com.dux.cnweb.domain.model.valueObjects.ReceivedDocumentNumber;
import com.dux.cnweb.domain.model.valueObjects.SignerInfo;
import com.dux.cnweb.shared.domain.model.AggregateRoot;

import jakarta.annotation.Nullable;

import com.dux.cnweb.domain.model.valueObjects.ReceiveType;
import com.dux.cnweb.domain.model.valueObjects.EmergencyLevel;
import com.dux.cnweb.domain.model.valueObjects.ReceiveState;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.annotation.Nullable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Document extends AggregateRoot {
    private UUID id;
    private UUID creator;
    private @Nullable String notebook;
    private DocType docType;
    private @Nullable ReceivedDocumentNumber receivedDocumentNumber;
    private EmergencyLevel emergencyLevel;
    private String referenceCode;
    private Date receiveDate;
    private @Nullable Date dueDate;
    private @Nullable Date dateOfIssue;
    private String issuingAgency;
    private @Nullable SignerInfo signerInfo;
    private DocContent docContent;
    private ReceiveType receiveType;
    private ReceiveState receiveState;

    public Document acceptDocument() {
        this.setReceiveState(this.receiveState.accept());
        return this;
    }

    public void rejectDocument() {
        this.setReceiveState(this.receiveState.reject());
    }
    // add events later

    public Document logDocument(String notebook) {
        this.setNotebook(notebook);
        this.addDomainEvent(new DocumentLogged(this.getId(), notebook));
        return this;
    }

    public Document signDocument(String signer, String signerPosition) {
        if (this.receiveType != ReceiveType.PAPER)
            throw new InvalidReceiveTypeException("Only paper documents can be signed.");
        // additional logic here (?)
        this.addDomainEvent(new DocumentSigned(this.getId()));
        return this;
    }

    public Document recallDocument() {
        if (this.receiveType != ReceiveType.PAPER)
            throw new InvalidReceiveTypeException("Only paper documents can be recalled.");
        this.setReceiveState(this.receiveState.recall());
        this.addDomainEvent(new DocumentRecalled(this.getId()));
        return this;
    }
}
