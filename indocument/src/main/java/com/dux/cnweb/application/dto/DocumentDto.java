package com.dux.cnweb.application.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles.DocumentFile;

@Value
public class DocumentDto {
    String documentId;
    String notebookId;
    String notebookNumber;
    String documentTypeInNotebook;
    int documentPriorityLevel;
    int documentTypeValue;
    String documentIdentifier;
    Date recievedDate;
    Date dueDate;
    Date issuedDate;
    String issuingAgencyName;
    String signatureName;
    String signaturePosition;
    Date signatureDate;
    String documentSummary;
    String documentNote;
    DocumentFileDto mainFile;
    List<DocumentFileDto> additionalFiles;

    public static DocumentDto of(
            String documentId,
            String notebookId,
            String notebookNumber,
            String documentTypeInNotebook,
            int documentPriorityLevel,
            int documentTypeValue,
            String documentIdentifier,
            Date recievedDate,
            Date dueDate,
            Date issuedDate,
            String issuingAgencyName,
            String signatureName,
            String signaturePosition,
            Date signatureDate,
            String documentSummary,
            String documentNote,
            DocumentFileDto mainFile,
            List<DocumentFileDto> additionalFiles) {

        return new DocumentDto(
                documentId, notebookId, notebookNumber, documentTypeInNotebook,
                documentPriorityLevel, documentTypeValue, documentIdentifier,
                recievedDate, dueDate, issuedDate, issuingAgencyName,
                signatureName, signaturePosition, signatureDate,
                documentSummary, documentNote, mainFile, additionalFiles);
    }

    public static DocumentDto mapToDto(Document document) {
        if (document == null) {
            return null;
        }

        // Map main file
        DocumentFileDto mainFileDto = null;
        if (document.getDocumentFiles() != null && document.getDocumentFiles().getMainFile() != null) {
            DocumentFile mainFile = document.getDocumentFiles().getMainFile();
            mainFileDto = new DocumentFileDto(
                    mainFile.getFileUri(),
                    mainFile.getFileName(),
                    mainFile.getFileExtension(),
                    mainFile.getContentType(),
                    mainFile.getFileSize(),
                    mainFile.getUploadDate());
        }

        // Map additional files
        List<DocumentFileDto> additionalFileDtos = new ArrayList<>();
        if (document.getDocumentFiles() != null && document.getDocumentFiles().getAttachments() != null) {
            additionalFileDtos = document.getDocumentFiles().getAttachments().stream()
                    .map(file -> new DocumentFileDto(
                            file.getFileUri(),
                            file.getFileName(),
                            file.getFileExtension(),
                            file.getContentType(),
                            file.getFileSize(),
                            file.getUploadDate()))
                    .collect(Collectors.toList());
        }

        return DocumentDto.of(
                document.getDocumentId().getValue(),
                document.getNotebook() != null ? document.getNotebook().getNotebookId() : null,
                document.getNotebook() != null ? document.getNotebook().getNotebookNumber() : null,
                document.getNotebook() != null ? document.getNotebook().getDocumentTypeInNotebook() : null,
                document.getDocumentPriority() != null ? document.getDocumentPriority().getPriorityLevel() : 0,
                document.getDocumentType() != null ? document.getDocumentType().ordinal() : 0,
                document.getDocumentIdentifier() != null ? document.getDocumentIdentifier().toString() : null,
                document.getRecievedDate(),
                document.getDueDate(),
                document.getIssuedDate(),
                document.getIssuingAgency() != null ? document.getIssuingAgency().getAgencyId() : null,
                document.getSignatureInfo() != null ? document.getSignatureInfo().getSignerName() : null,
                document.getSignatureInfo() != null ? document.getSignatureInfo().getSignerPosition() : null,
                document.getSignatureInfo() != null ? document.getSignatureInfo().getSignatureDate() : null,
                document.getDocumentSummary(),
                document.getDocumentNote(),
                mainFileDto,
                additionalFileDtos);

    }
}
