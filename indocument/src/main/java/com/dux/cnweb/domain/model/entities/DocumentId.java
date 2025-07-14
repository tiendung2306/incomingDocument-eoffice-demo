// domain/model/entities/ProductId.java
package com.dux.cnweb.domain.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class DocumentId {
    private final String value;

    public static DocumentId newId() {
        return DocumentId.of(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
