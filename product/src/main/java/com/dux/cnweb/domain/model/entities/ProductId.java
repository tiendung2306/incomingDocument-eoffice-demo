// domain/model/entities/ProductId.java
package com.dux.cnweb.domain.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class ProductId {
    private final String value;

    public static ProductId newId() {
        return ProductId.of(UUID.randomUUID().toString());
    }

    @Override 
    public String toString() { 
        return value; 
    }
}
