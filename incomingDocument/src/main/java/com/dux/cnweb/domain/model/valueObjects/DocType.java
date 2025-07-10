package com.dux.cnweb.domain.model.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class DocType {
    private final String value;

    public DocType(String value) {
        this.value = value;
    }
}
