package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateProductCommand {
    String productId;
    ProductName name;
    Price price;
    
    public static CreateProductCommand of(String name, BigDecimal amount, String currency) {
        return new CreateProductCommand(
            null, // sẽ được generate
            ProductName.of(name),
            Price.of(amount, currency)
        );
    }
}
