package com.dux.cnweb.shared.infrastructure.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import com.dux.cnweb.shared.infrastructure.persistence.entities.EventStoreEntity;
import com.dux.cnweb.shared.infrastructure.persistence.repositories.EventStoreRepository;
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
                    event.getOccurredAt(),
                    1L
            );
            
            eventStoreRepository.save(entity);
            log.info("Stored event: {} for aggregate: {}", event.getClass().getSimpleName(), extractAggregateId(event));
            
        } catch (Exception e) {
            log.error("Failed to store event: {}", event, e);
        }
    }

    private String extractAggregateId(DomainEvent event) {
        // Extract aggregate ID based on event type using reflection
        try {
            // Try to get productId field first
            try {
                java.lang.reflect.Method getProductId = event.getClass().getMethod("getProductId");
                return (String) getProductId.invoke(event);
            } catch (NoSuchMethodException e) {
                // If productId doesn't exist, try userId
                try {
                    java.lang.reflect.Method getUserId = event.getClass().getMethod("getUserId");
                    return (String) getUserId.invoke(event);
                } catch (NoSuchMethodException ex) {
                    // If neither exists, return unknown
                    return "unknown";
                }
            }
        } catch (Exception e) {
            log.warn("Could not extract aggregate ID from event: {}", event.getClass().getSimpleName(), e);
            return "unknown";
        }
    }
}
