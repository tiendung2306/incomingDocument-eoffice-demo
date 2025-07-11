package com.dux.cnweb.domain.model;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import com.dux.cnweb.domain.model.valueObjects.Role;
import com.dux.cnweb.domain.model.valueObjects.JobState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class User {
    private UUID docId;
    private UUID userId;
    private Role role;
    private JobState state;

    public User(UUID docId, UUID userId, String role) {
        this.docId = docId;
        this.userId = userId;
        this.role = Role.fromValue(role);
        this.state = JobState.NOT_PROCESSED;
    }

    public void startProcess(){
        this.state = this.state.startProcessing();
    }

    public void finishProcess() {
        this.state = this.state.finishProcessing();
    }
}
