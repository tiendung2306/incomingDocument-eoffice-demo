package com.dux.cnweb.domain.repositories;

import com.dux.cnweb.domain.model.aggregates.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    Document save(Document product);

    Optional<Document> findById(String documentId);

    List<Document> findAll();

    void deleteById(String documentId);
}
