package com.dux.cnweb.domain.model.valueObjects;

import com.dux.cnweb.domain.model.exceptions.InvalidProposalState;

public enum ProposalState {
    PROPOSED,
    APPROVED;

    public ProposalState approve() {
        if (this == ProposalState.APPROVED)
            throw new InvalidProposalState("Already approved");
        return ProposalState.APPROVED;
    }
}
