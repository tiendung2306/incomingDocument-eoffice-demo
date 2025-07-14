package com.dux.cnweb.application.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class DocumentFileDto {
    String fileUri;
    String fileName;
    String fileExtension;
    String contentType;
    Long fileSize;
    LocalDateTime uploadDate;
}