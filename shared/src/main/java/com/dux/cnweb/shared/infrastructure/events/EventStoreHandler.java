package com.dux.cnweb.shared.infrastructure.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import com.dux.cnweb.shared.infrastructure.persistence.entities.EventStoreEntity;
import com.dux.cnweb.shared.infrastructure.persistence.repositories.EventStoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        // Try with reflection for common ID patterns
        String[] commonMethodPrefixes = { "get", "is" };
        String[] commonIdSuffixes = { "Id", "ID" };

        for (String prefix : commonMethodPrefixes) {
            for (String suffix : commonIdSuffixes) {
                for (String entity : new String[] { "product", "user", "document", "proposal" }) {
                    String methodName = prefix + capitalize(entity) + suffix;
                    try {
                        Method method = event.getClass().getMethod(methodName);
                        Object result = method.invoke(event);
                        if (result != null) {
                            return result.toString();
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        // Continue trying other methods
                    }
                }
            }
        }

        return "unknown";
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
