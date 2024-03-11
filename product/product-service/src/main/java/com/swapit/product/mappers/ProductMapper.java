package com.swapit.product.mappers;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.domain.Product;

public class ProductMapper {
    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .productId(productDTO.getProductId())
                .userId(productDTO.getUserId())
                .title(productDTO.getTitle())
                .creationDate(productDTO.getCreationDate())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .subcategory(productDTO.getSubcategory())
                .productSpecifications(productDTO.getProductSpecifications()
                        .stream().map(ProductSpecificationMapper::toEntity).toList())
                .build();
    }
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .userId(product.getUserId())
                .title(product.getTitle())
                .creationDate(product.getCreationDate())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .subcategory(product.getSubcategory())
                .productSpecifications(product.getProductSpecifications()
                        .stream().map(ProductSpecificationMapper::toDTO).toList())
                .build();
    }
}