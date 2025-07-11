package com.dux.cnweb.infrastructure.persistence.repositories;

import com.dux.cnweb.domain.repositories.DocumentEventRepository;
import com.dux.cnweb.shared.domain.events.DomainEvent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DocumentEventRepositoryImpl implements DocumentEventRepository{
    private DocumentEventJpaRepository repository;

    public DocumentEventRepositoryImpl(DocumentEventJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public DomainEvent save(DomainEvent DomainEvent) {
        return repository.save(DomainEvent);
    }
    
    @Override
    public Optional<DomainEvent> findById(UUID EventId) {
        return repository.findById(EventId);
    }
    
    @Override
    public List<DomainEvent> findAll() {
        return repository.findAll();
    }
    
    @Override
    public List<DomainEvent> findAllByAggregateId(UUID AggregateId) {
        return repository.findAllByAggregateId(AggregateId);
    }

    @Override
    public List<DomainEvent> appendDomainEvents(List<DomainEvent> events) {
        return repository.saveAll(events);
    }

    @Override
    public void deleteById(UUID EventId) {
        repository.deleteById(EventId);
    }
}
