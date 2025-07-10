package com.dux.cnweb.domain.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class UserId {
    private final String value;

    public static UserId newId() {
        return UserId.of(UUID.randomUUID().toString());
    }

    @Override 
    public String toString() { 
        return value; 
    }
}
