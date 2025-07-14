package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

@Value
public class DocumentIdentifier {
    private String documentIdentifierNumber;
    private String documentIdentifierSymbol;

    public static DocumentIdentifier of(String documentIdentifierNumber, String documentIdentifierSymbol) {
        return new DocumentIdentifier(documentIdentifierNumber, documentIdentifierSymbol);
    }

    public static String getDocumentIdentifier(String documentIdentifierNumber, String documentIdentifierSymbol) {
        return documentIdentifierNumber + "-" + documentIdentifierSymbol;
    }

    public static DocumentIdentifier of(String documentIdentifier) {
        String[] parts = documentIdentifier.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid document identifier format: " + documentIdentifier);
        }
        return new DocumentIdentifier(parts[0], parts[1]);
    }

    @Override
    public String toString() {
        return getDocumentIdentifier(documentIdentifierNumber, documentIdentifierSymbol);
    }
}
