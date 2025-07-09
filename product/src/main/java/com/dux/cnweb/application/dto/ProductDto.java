package com.dux.cnweb.application.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductDto {
    String productId;
    String name;
    BigDecimal price;
    String currency;
}
