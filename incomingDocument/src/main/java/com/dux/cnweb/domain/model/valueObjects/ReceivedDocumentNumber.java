package com.dux.cnweb.domain.model.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class ReceivedDocumentNumber {
    private final int value;

    public ReceivedDocumentNumber(int number) {
        // logic
        this.value = number;
    }

    // public ReceivedDocumentNumber() {
        
    // }
}
