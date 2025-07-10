package com.dux.cnweb.domain.model.valueObjects;

public enum EmergencyLevel {
    URGENT("urgent"),
    HIGH("high"),
    MEDIUM("medium"), 
    LOW("low");

    private final String value;

    EmergencyLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EmergencyLevel fromValue(String value) {
        for (EmergencyLevel level : EmergencyLevel.values()) {
            if (level.value.equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown emergency level: " + value);
    }
}
