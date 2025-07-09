package com.dux.cnweb.domain.repositories;

import com.dux.cnweb.domain.model.aggregates.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(String productId);
    List<Product> findAll();
    void deleteById(String productId);
}
