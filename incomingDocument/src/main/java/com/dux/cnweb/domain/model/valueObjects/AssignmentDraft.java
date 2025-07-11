package com.dux.cnweb.domain.model.valueObjects;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class AssignmentDraft {
    private final UUID docId;
    private final UUID userId;
    private final String role;

    public AssignmentDraft(UUID docId, UUID userId, String role) {
        this.docId = docId;
        this.userId = userId;
        this.role = role;
    }
}
