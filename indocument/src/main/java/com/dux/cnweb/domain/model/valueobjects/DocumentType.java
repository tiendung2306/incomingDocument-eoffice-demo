package com.dux.cnweb.domain.model.valueobjects;

public enum DocumentType {
    PAPER(0),
    INTRANET(1),
    INTERNET(2);

    private final int value;

    DocumentType(int value) {
        this.value = value;
    }

    public static DocumentType of(int value) {
        for (DocumentType type : DocumentType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid document type value: " + value);
    }
}