package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import lombok.Value;

@Value
public class UpdateProductCommand {
    String productId;
    ProductName name;
    Price price;
}
