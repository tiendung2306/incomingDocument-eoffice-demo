// domain/model/valueobjects/ProductName.java
package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductName {
    String value;
}
