package com.dux.cnweb.domain.events;

import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DocumentSigned extends DomainEvent{
    private final UUID docId;

    public DocumentSigned(UUID docId) {
        super();
        this.docId = docId;
    }
}
