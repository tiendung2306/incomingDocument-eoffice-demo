package com.dux.cnweb.domain.model.entities;

import lombok.Value;

@Value(staticConstructor = "of")
public class NotebookId {
    String id;

    public static NotebookId newId() {
        return NotebookId.of(java.util.UUID.randomUUID().toString());
    }
}
