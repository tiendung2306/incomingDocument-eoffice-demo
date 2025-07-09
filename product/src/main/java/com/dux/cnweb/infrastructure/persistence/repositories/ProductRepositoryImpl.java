package com.dux.cnweb.infrastructure.persistence.repositories;

import com.dux.cnweb.domain.model.aggregates.Product;
import com.dux.cnweb.domain.model.entities.ProductId;
import com.dux.cnweb.domain.model.valueobjects.Price;
import com.dux.cnweb.domain.model.valueobjects.ProductName;
import com.dux.cnweb.domain.repositories.ProductRepository;
import com.dux.cnweb.infrastructure.persistence.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapToEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Product> findById(String productId) {
        return jpaRepository.findById(productId)
                .map(this::mapToDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String productId) {
        jpaRepository.deleteById(productId);
    }

    private ProductEntity mapToEntity(Product product) {
        return new ProductEntity(
                product.getProductId().getValue(),
                product.getName().getValue(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }

    private Product mapToDomain(ProductEntity entity) {
        return new Product(
                ProductId.of(entity.getProductId()),
                ProductName.of(entity.getName()),
                Price.of(entity.getPrice(), entity.getCurrency())
        );
    }
}
