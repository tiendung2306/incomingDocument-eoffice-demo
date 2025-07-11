package com.dux.cnweb.domain.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;

import java.util.UUID;

import lombok.Getter;

@Getter
public class DistributionApproved extends DomainEvent {
    private final UUID proposalId;
    private final UUID userId;

    public DistributionApproved(UUID proposalId, UUID userId) {
        super();
        this.proposalId = proposalId;
        this.userId = userId;
    }
}
