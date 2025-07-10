package com.dux.cnweb.domain.model.valueObjects;

public enum ReceiveState {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    RECALLED("recalled");

    private final String value;

    ReceiveState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public ReceiveState accept() {
        if (this != ReceiveState.PENDING) 
            throw new IllegalStateException("Cannot accept when in state: " + this);
        return ReceiveState.ACCEPTED;
    }

    public ReceiveState reject() {
        if (this != ReceiveState.PENDING) 
            throw new IllegalStateException("Cannot reject when in state: " + this);
        return ReceiveState.REJECTED;
    }

    public ReceiveState recall() {
        if (this != ReceiveState.ACCEPTED) 
            throw new IllegalStateException("Cannot reject when in state: " + this);
        return ReceiveState.RECALLED;
    }

    public static ReceiveState fromValue(String value) {
        for (ReceiveState level : ReceiveState.values()) {
            if (level.value.equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown emergency level: " + value);
    }
}
