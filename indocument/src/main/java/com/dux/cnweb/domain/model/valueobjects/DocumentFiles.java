package com.dux.cnweb.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@EqualsAndHashCode
public class DocumentFiles {
    private DocumentFile mainFile;
    private List<DocumentFile> attachments;

    public DocumentFiles(DocumentFile mainFile) {
        this(mainFile, new ArrayList<>());
    }

    public DocumentFiles(DocumentFile mainFile, List<DocumentFile> attachments) {
        this.mainFile = mainFile;
        this.attachments = new ArrayList<>(attachments);
    }

    public static DocumentFiles of(DocumentFile mainFile, List<DocumentFile> attachments) {
        return new DocumentFiles(mainFile, attachments);
    }

    public void setMainFile(DocumentFile mainFile) {
        this.mainFile = mainFile;
    }

    public List<DocumentFile> getAttachments() {
        return Collections.unmodifiableList(attachments);
    }

    public void addAttachment(DocumentFile attachment) {
        if (attachment != null) {
            this.attachments.add(attachment);
        }
    }

    public void removeAttachment(DocumentFile attachment) {
        this.attachments.remove(attachment);
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class DocumentFile {
        private String fileUri; // VD: file:///home/abc.pdf hoặc s3://bucket/abc.pdf
        private String fileName; // VD: abc.pdf
        private String fileExtension; // VD: pdf
        private String contentType; // VD: application/pdf
        private Long fileSize; // VD: 123456L (bytes)
        private LocalDateTime uploadDate;
    }

}