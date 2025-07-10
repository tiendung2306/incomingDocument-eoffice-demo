package com.dux.cnweb.domain.events;

import java.util.UUID;

import com.dux.cnweb.domain.events.dto.DocumentDTO;
import com.dux.cnweb.domain.model.Document;
import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DocumentCreated extends DomainEvent {
    private final UUID docId;
    private final DocumentDTO payload;

    public DocumentCreated(Document doc) {
        super();
        this.docId = doc.getId();
        this.payload = new DocumentDTO(doc);
    }
}
