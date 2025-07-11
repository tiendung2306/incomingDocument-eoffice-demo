package com.dux.cnweb.domain.events;

import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DocumentLogged extends DomainEvent{
    private final UUID docId;
    private final String payload;

    public DocumentLogged(UUID docId, String notebook) {
        super();
        this.docId = docId;
        this.payload = notebook;
    }
}
