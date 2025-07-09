package com.dux.cnweb.infrastructure.persistence.repositories;

import com.dux.cnweb.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, String> {
}
