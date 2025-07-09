package com.dux.cnweb.infrastructure.events;

import com.dux.cnweb.domain.events.DomainEvent;
import com.dux.cnweb.infrastructure.persistence.entities.EventStoreEntity;
import com.dux.cnweb.infrastructure.persistence.repositories.EventStoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventStoreHandler {

    private final EventStoreRepository eventStoreRepository;
    private final ObjectMapper objectMapper;

    @EventListener
    public void handle(DomainEvent event) {
        try {
            String eventData = objectMapper.writeValueAsString(event);
            
            EventStoreEntity entity = new EventStoreEntity(
                    event.getEventId(),
                    extractAggregateId(event),
                    event.getClass().getSimpleName(),
                    eventData,
                    event.getOccurredOn(),
                    1L
            );
            
            eventStoreRepository.save(entity);
            log.info("Stored event: {} for aggregate: {}", event.getClass().getSimpleName(), extractAggregateId(event));
            
        } catch (Exception e) {
            log.error("Failed to store event: {}", event, e);
        }
    }

    private String extractAggregateId(DomainEvent event) {
        // Extract aggregate ID based on event type
        if (event instanceof com.dux.cnweb.domain.events.ProductCreatedEvent) {
            return ((com.dux.cnweb.domain.events.ProductCreatedEvent) event).getProductId();
        }
        if (event instanceof com.dux.cnweb.domain.events.ProductUpdatedEvent) {
            return ((com.dux.cnweb.domain.events.ProductUpdatedEvent) event).getProductId();
        }
        return "unknown";
    }
}
