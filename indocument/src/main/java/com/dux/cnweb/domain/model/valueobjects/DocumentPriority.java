package com.dux.cnweb.domain.model.valueobjects;

import lombok.Getter;

public enum DocumentPriority {
    NORMAL("Bình thường", 0),
    URGENT("Khẩn", 1),
    VERY_URGENT("Thượng Khẩn", 2),
    IMMEDIATE("Hỏa tốc", 3);

    @Getter
    private final String displayName;

    @Getter
    private final int priorityLevel;

    DocumentPriority(String displayName, int priorityLevel) {
        this.displayName = displayName;
        this.priorityLevel = priorityLevel;
    }

    public static DocumentPriority of(int priorityLevel) {
        for (DocumentPriority priority : DocumentPriority.values()) {
            if (priority.priorityLevel == priorityLevel) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority level: " + priorityLevel);
    }

    public static DocumentPriority fromDisplayName(String displayName) {
        for (DocumentPriority priority : DocumentPriority.values()) {
            if (priority.getDisplayName().equalsIgnoreCase(displayName)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Cannot find priority level with name: " + displayName);
    }

    public boolean isHigherThan(DocumentPriority other) {
        return this.priorityLevel > other.priorityLevel;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}