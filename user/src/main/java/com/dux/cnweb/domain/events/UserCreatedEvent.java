package com.dux.cnweb.domain.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class UserCreatedEvent extends DomainEvent {
    private final String userId;
    private final String email;

    public UserCreatedEvent(String userId, String email) {
        super();
        this.userId = userId;
        this.email = email;
    }
}
