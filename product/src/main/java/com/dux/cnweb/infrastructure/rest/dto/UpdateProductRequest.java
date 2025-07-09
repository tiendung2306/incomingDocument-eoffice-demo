package com.dux.cnweb.infrastructure.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    
    private String name;
    private BigDecimal price;
    private String currency;
}
