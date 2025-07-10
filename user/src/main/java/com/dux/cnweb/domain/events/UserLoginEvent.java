package com.dux.cnweb.domain.events;

import com.dux.cnweb.shared.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class UserLoginEvent extends DomainEvent {
    private final String userId;
    private final String email;

    public UserLoginEvent(String userId, String email) {
        super();
        this.userId = userId;
        this.email = email;
    }
}
