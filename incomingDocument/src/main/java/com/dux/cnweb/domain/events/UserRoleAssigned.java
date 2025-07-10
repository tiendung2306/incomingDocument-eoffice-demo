package com.dux.cnweb.domain.events;

import java.util.UUID;

import com.dux.cnweb.shared.domain.events.DomainEvent;

public class UserRoleAssigned extends DomainEvent {
    private final UUID userId;
    private final String role;

    public UserRoleAssigned(UUID userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}
