// domain/model/valueobjects/Price.java
package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Price {
    BigDecimal amount;
    String     currency;   // ISO-4217, ví dụ “USD”, “VND”
}
