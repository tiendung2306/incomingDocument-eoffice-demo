package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.DocumentFiles;
import com.dux.cnweb.domain.model.valueobjects.DocumentIdentifier;
import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.DocumentType;
import com.dux.cnweb.domain.model.valueobjects.IssuingAgency;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import com.dux.cnweb.domain.model.valueobjects.SignatureInfo;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles.DocumentFile;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.print.Doc;

@Value
public class CreateDocumentCommand {
    String documentId;
    Notebook notebook;
    DocumentPriority documentPriority;
    DocumentType documentType;
    DocumentIdentifier documentIdentifier;
    Date recievedDate;
    Date dueDate;
    Date issuedDate;
    IssuingAgency issuingAgency;
    SignatureInfo signatureInfo;
    String documentSummary;
    String documentNote;
    DocumentFiles documentFiles;

    public static CreateDocumentCommand of(String notebookId, String notebookNumber, String documentTypeInNotebook,
            int documentPriorityLevel, int documentTypeValue, String documentIdentifier, Date recievedDate,
            Date dueDate, Date issuedDate, String issuingAgencyName, String signatureName, String signaturePosition,
            Date signatureDate, String documentSummary, String documentNote, DocumentFile mainFile,
            DocumentFile... additionalFiles) {

        return new CreateDocumentCommand(
                null, // sẽ được generate
                Notebook.of(notebookId, notebookNumber, documentTypeInNotebook),
                DocumentPriority.of(documentPriorityLevel),
                DocumentType.of(documentTypeValue),
                DocumentIdentifier.of(documentIdentifier),
                recievedDate,
                dueDate,
                issuedDate,
                IssuingAgency.of(issuingAgencyName),
                SignatureInfo.of(signatureName, signaturePosition, signatureDate),
                documentSummary,
                documentNote,
                DocumentFiles.of(mainFile, Arrays.asList(additionalFiles)));
    }
}
