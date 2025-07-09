// domain/model/aggregates/Product.java
package com.dux.cnweb.domain.model.aggregates;

import com.dux.cnweb.domain.model.AggregateRoot;
import com.dux.cnweb.domain.model.entities.ProductId;
import com.dux.cnweb.domain.events.ProductCreatedEvent;
import com.dux.cnweb.domain.events.ProductUpdatedEvent;
import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Product extends AggregateRoot {

    private ProductId productId;
    private ProductName name;
    private Price price;

    // Factory method để tạo Product mới
    public static Product create(ProductId productId, ProductName name, Price price) {
        Product product = new Product();
        product.productId = productId;
        product.name = name;
        product.price = price;
        
        // Phát sinh domain event
        product.addDomainEvent(new ProductCreatedEvent(productId.getValue(), name, price));
        
        return product;
    }

    // Business method để update product
    public void update(ProductName name, Price price) {
        this.name = name;
        this.price = price;
        
        // Phát sinh domain event
        addDomainEvent(new ProductUpdatedEvent(this.productId.getValue(), name, price));
    }
    
    // Constructor cho việc khôi phục từ database
    public Product(ProductId productId, ProductName name, Price price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
