package com.dux.cnweb.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;

public interface DocumentEventRepository {
    DomainEvent save(DomainEvent DomainEvent);
    Optional<DomainEvent> findById(UUID DomainEventId);
    List<DomainEvent> findAll();
    List<DomainEvent> findAllByAggregateId(UUID AggregateId);
    List<DomainEvent> appendDomainEvents(List<DomainEvent> DomainEvents);
    void deleteById(UUID DomainEventId);
}
