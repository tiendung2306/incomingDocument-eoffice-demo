package com.dux.cnweb.infrastructure.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    
    @Id
    private String productId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;
    
    @Column(nullable = false)
    private String currency;
}
