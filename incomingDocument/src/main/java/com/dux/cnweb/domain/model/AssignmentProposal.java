package com.dux.cnweb.domain.model;

import java.util.UUID;

import com.dux.cnweb.domain.events.DistributionApproved;
import com.dux.cnweb.domain.events.DistributionProposed;
import com.dux.cnweb.domain.model.valueObjects.AssignmentDraft;
import com.dux.cnweb.domain.model.valueObjects.ProposalState;
import com.dux.cnweb.shared.domain.model.AggregateRoot;

import java.util.List;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class AssignmentProposal extends AggregateRoot{
    private UUID id;
    private UUID documentID;
    private List<AssignmentDraft> assignments; 
    private ProposalState state;

    public AssignmentProposal(UUID userId, UUID documentID, List<AssignmentDraft> assignments) {
        this.id = UUID.randomUUID();
        this.documentID = documentID;
        this.assignments = assignments;
        this.addDomainEvent(new DistributionProposed(id, userId, documentID, assignments));
    }

    public void approve(UUID userId) {
        this.setState(this.state.approve());
        this.addDomainEvent(new DistributionApproved(id, userId, documentID));
    }
}
