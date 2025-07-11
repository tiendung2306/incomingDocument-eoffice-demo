package com.dux.cnweb.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

import com.dux.cnweb.shared.domain.events.DomainEvent;

public interface DocumentEventJpaRepository extends JpaRepository<DomainEvent, UUID>{
    List<DomainEvent> findAllByAggregateId(UUID AggregateId);
}
