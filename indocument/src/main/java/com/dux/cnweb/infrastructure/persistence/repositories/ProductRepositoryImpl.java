package com.dux.cnweb.infrastructure.persistence.repositories;

import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.domain.model.entities.DocumentId;
import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import com.dux.cnweb.domain.repositories.DocumentRepository;
import com.dux.cnweb.infrastructure.persistence.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements DocumentRepository {

    private final JpaProductRepository jpaRepository;

    @Override
    public Document save(Document product) {
        ProductEntity entity = mapToEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Document> findById(String productId) {
        return jpaRepository.findById(productId)
                .map(this::mapToDomain);
    }

    @Override
    public List<Document> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String productId) {
        jpaRepository.deleteById(productId);
    }

    private ProductEntity mapToEntity(Document product) {
        return new ProductEntity(
                product.getProductId().getValue(),
                product.getName().getValue(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency());
    }

    private Document mapToDomain(ProductEntity entity) {
        return new Document(
                DocumentId.of(entity.getProductId()),
                Notebook.of(entity.getName()),
                DocumentPriority.of(entity.getPrice(), entity.getCurrency()));
    }
}
