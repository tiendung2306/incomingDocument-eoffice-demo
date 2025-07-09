package com.dux.cnweb.infrastructure.events;

import com.dux.cnweb.domain.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainEventPublisher {
    
    private final ApplicationEventPublisher applicationEventPublisher;
    
    public void publishEvents(List<DomainEvent> events) {
        events.forEach(applicationEventPublisher::publishEvent);
    }
}
