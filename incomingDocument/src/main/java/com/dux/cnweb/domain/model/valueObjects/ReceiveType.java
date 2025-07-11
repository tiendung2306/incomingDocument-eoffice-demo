package com.dux.cnweb.domain.model.valueObjects;

public enum ReceiveType {
    PAPER("paper"),
    INTERNAL_DIGITAL("internal_digital"),
    EXTERNAL_DIGITAL("external_digital");

    private final String value;

    ReceiveType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReceiveType fromValue(String value) {
        for (ReceiveType level : ReceiveType.values()) {
            if (level.value.equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown emergency level: " + value);
    }
}
