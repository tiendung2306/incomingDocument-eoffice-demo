package com.dux.cnweb.infrastructure.persistence.entities;

import java.time.Instant;
import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_event_store")
public class DocumentEventEntity {
    @Id
    private UUID id;

    private UUID aggregateId;

    private Instant occurredAt;

    private String eventType; 

    @Lob
    private String payload; 

    public DocumentEventEntity(UUID aggregateId, DomainEvent event, Object payloadObject) {
        this.aggregateId = aggregateId;
        this.eventType = event.getClass().getSimpleName();
        this.occurredAt = event.getOccurredAt(); 
        this.payload = serialize(payloadObject);
    }

    private String serialize(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event payload", e);
        }
    }
}
