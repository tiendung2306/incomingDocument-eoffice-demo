package com.dux.cnweb.shared.infrastructure.persistence.repositories;

import com.dux.cnweb.shared.infrastructure.persistence.entities.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    List<EventStoreEntity> findByAggregateIdOrderByVersionAsc(String aggregateId);
}
