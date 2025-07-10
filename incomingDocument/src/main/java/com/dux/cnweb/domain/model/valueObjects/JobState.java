package com.dux.cnweb.domain.model.valueObjects;

public enum JobState {
    NOT_PROCESSED("not_processed"),
    PROCESSING("processing"),
    PROCESSED("processed");

    private final String value;

    JobState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public JobState startProcessing() {
        if (this != NOT_PROCESSED) 
            throw new IllegalStateException("Cannot start processing when in state: " + this);
        return JobState.PROCESSING;
    }

    public JobState finishProcessing() {
        if (this != PROCESSING) 
            throw new IllegalStateException("Cannot finish processing when in state: " + this);
        return JobState.PROCESSED;
    }

    public static JobState fromValue(String value) {
        for (JobState state : JobState.values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown state: " + value);
    }
}
