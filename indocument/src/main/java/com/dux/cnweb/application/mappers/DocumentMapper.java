package com.dux.cnweb.application.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dux.cnweb.application.dto.DocumentDto;
import com.dux.cnweb.application.dto.DocumentFileDto;
import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles.DocumentFile;

public class DocumentMapper {

    public static DocumentDto toDto(Document document) {
        if (document == null) {
            return null;
        }

        DocumentFileDto mainFileDto = toFileDto(
                document.getDocumentFiles().getMainFile());
        List<DocumentFileDto> additionalFilesDto = document.getDocumentFiles().getAttachments()
                .stream()
                .map(DocumentMapper::toFileDto)
                .collect(Collectors.toList());

        return DocumentDto.of(
                document.getDocumentId().getValue(),
                document.getNotebook().getNotebookId(),
                document.getNotebook().getNotebookNumber(),
                document.getNotebook().getDocumentTypeInNotebook(),
                document.getDocumentPriority().getPriorityLevel(),
                0, // Assuming documentTypeValue needs to be retrieved from DocumentType
                document.getDocumentIdentifier().toString(),
                document.getRecievedDate(),
                document.getDueDate(),
                document.getIssuedDate(),
                document.getIssuingAgency().getAgencyId(),
                document.getSignatureInfo().getSignerName(),
                document.getSignatureInfo().getSignerPosition(),
                document.getSignatureInfo().getSignatureDate(),
                document.getDocumentSummary(),
                document.getDocumentNote(),
                mainFileDto,
                additionalFilesDto);
    }

    public static DocumentFileDto toFileDto(DocumentFile file) {
        if (file == null) {
            return null;
        }

        return new DocumentFileDto(
                file.getFileUri(),
                file.getFileName(),
                file.getFileExtension(),
                file.getContentType(),
                file.getFileSize(),
                file.getUploadDate());
    }

    public static List<DocumentDto> toDtoList(List<Document> documents) {
        if (documents == null) {
            return new ArrayList<>();
        }

        return documents.stream()
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }
}
