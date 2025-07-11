package com.dux.cnweb.application.commands;

import java.util.Date;
import java.util.UUID;

import jakarta.annotation.Nullable;

public record CreatePaperDocumentCommand(UUID creator, String notebook, String docType,
            int receivedDocumentNumber, String emergencyLevel, String referenceCode,
            Date receiveDate, @Nullable Date dueDate, @Nullable Date dateOfIssue, String issuingAgency,
            @Nullable String signer, @Nullable String signerPosition, String content, @Nullable String note) {
}
