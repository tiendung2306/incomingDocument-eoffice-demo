package com.dux.cnweb.application.handlers;

import com.dux.cnweb.application.dto.DocumentDto;
import com.dux.cnweb.application.dto.DocumentFileDto;
import com.dux.cnweb.application.queries.GetAllDocumentsQuery;
import com.dux.cnweb.application.queries.GetProductQuery;
import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles;
import com.dux.cnweb.domain.model.valueobjects.DocumentFiles.DocumentFile;
import com.dux.cnweb.domain.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryHandler {

    private final DocumentRepository documentRepository;

    // public ProductDto handle(GetProductQuery query) {
    // Document product = documentRepository.findById(query.getProductId())
    // .orElseThrow(() -> new RuntimeException("Product not found: " +
    // query.getProductId()));

    // return mapToDto(product);
    // }

    public List<DocumentDto> handle(GetAllDocumentsQuery query) {
        return documentRepository.findAll()
                .stream()
                .map(DocumentDto::mapToDto)
                .collect(Collectors.toList());
    }

}
