package com.dux.cnweb.shared.infrastructure.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private LocalDateTime timestamp;
    private String path;
    
    public static ErrorResponse of(String message, String code, String path) {
        return new ErrorResponse(message, code, LocalDateTime.now(), path);
    }
}
