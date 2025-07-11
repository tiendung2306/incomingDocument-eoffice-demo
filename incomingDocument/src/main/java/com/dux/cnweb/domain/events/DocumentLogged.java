package com.dux.cnweb.domain.events;

import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DocumentLogged extends DomainEvent{
    private final UUID documentId ;
    private final String payload;

    public DocumentLogged(UUID documentId , String notebook) {
        super();
        this.documentId  = documentId ;
        this.payload = notebook;
    }
}
