package com.dux.cnweb.application.handlers;

import com.dux.cnweb.application.dto.ProductDto;
import com.dux.cnweb.application.queries.GetAllProductsQuery;
import com.dux.cnweb.application.queries.GetProductQuery;
import com.dux.cnweb.domain.model.aggregates.Product;
import com.dux.cnweb.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductDto handle(GetProductQuery query) {
        Product product = productRepository.findById(query.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + query.getProductId()));
        
        return mapToDto(product);
    }

    public List<ProductDto> handle(GetAllProductsQuery query) {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getProductId().getValue(),
                product.getName().getValue(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }
}
