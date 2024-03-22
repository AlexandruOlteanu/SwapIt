package com.swapit.product.mappers;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.domain.Product;

public class ProductMapper {
    public static Product productDtoToProduct(ProductDTO productDTO) {
        return Product.builder()
                .productId(productDTO.getProductId())
                .userId(productDTO.getUserId())
                .title(productDTO.getTitle())
                .creationDate(productDTO.getCreationDate())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .categoryId(productDTO.getCategoryId())
                .popularity(productDTO.getPopularity())
                .productSpecifications(productDTO.getProductSpecifications()
                        .stream().map(ProductSpecificationMapper::prodSpecDtoToProdSpec).toList())
                .build();
    }

    public static ProductDTO productToProductDto(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .userId(product.getUserId())
                .title(product.getTitle())
                .creationDate(product.getCreationDate())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .popularity(product.getPopularity())
                .productSpecifications(product.getProductSpecifications()
                        .stream().map(ProductSpecificationMapper::prodSpecToProdSpecDto).toList())
                .build();
    }
}