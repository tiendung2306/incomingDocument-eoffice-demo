package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

@Value
public class Password {
    String value;

    private Password(String value) {
        if (value == null || value.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }

    public static Password createValid(String value) {
        return new Password(value);
    }    
}
