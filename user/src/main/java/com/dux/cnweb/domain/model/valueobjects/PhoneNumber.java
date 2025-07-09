package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

@Value
public class PhoneNumber {
    String number;

    private PhoneNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        if (!number.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Phone number contains invalid characters");
        }
        this.number = number.trim();
    }

    public static PhoneNumber of(String number) {
        return new PhoneNumber(number);
    }
    public static PhoneNumber createValid(String number) {
        return new PhoneNumber(number);
    }
}
