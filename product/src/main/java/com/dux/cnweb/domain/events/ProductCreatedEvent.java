package com.dux.cnweb.domain.events;

import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import lombok.Getter;

@Getter
public class ProductCreatedEvent extends DomainEvent {
    private final String productId;
    private final ProductName name;
    private final Price price;

    public ProductCreatedEvent(String productId, ProductName name, Price price) {
        super();
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
