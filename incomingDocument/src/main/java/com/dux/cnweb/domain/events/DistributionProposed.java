package com.dux.cnweb.domain.events;

import java.util.UUID;
import java.util.List;

import com.dux.cnweb.domain.model.valueObjects.AssignmentDraft;
import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DistributionProposed extends DomainEvent{
    private final UUID proposalId;
    private final UUID userId;
    private final List<AssignmentDraft> assignments;
    
    public DistributionProposed(UUID proposalId, UUID userId, List<AssignmentDraft> assignments) {
        super();
        this.proposalId = proposalId;
        this.userId = userId;
        this.assignments = assignments;
    }
}
