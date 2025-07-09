package com.dux.cnweb.infrastructure.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventStoreEntity {
    
    @Id
    private String eventId;
    
    @Column(nullable = false)
    private String aggregateId;
    
    @Column(nullable = false)
    private String eventType;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String eventData;
    
    @Column(nullable = false)
    private Instant occurredOn;
    
    @Column(nullable = false)
    private Long version;
}
