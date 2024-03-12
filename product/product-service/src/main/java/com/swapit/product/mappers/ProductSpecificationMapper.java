package com.swapit.product.mappers;

import com.swapit.product.api.domain.dto.ProductSpecificationDTO;
import com.swapit.product.domain.ProductSpecification;

public class ProductSpecificationMapper {
    static ProductSpecification toEntity(ProductSpecificationDTO productSpecificationDTO) {
        return ProductSpecification.builder()
                .specificationId(productSpecificationDTO.getSpecificationId())
                .key(productSpecificationDTO.getKey())
                .value(productSpecificationDTO.getValue())
                .build();
    }
    
    static ProductSpecificationDTO toDTO(ProductSpecification productSpecification) {
        return ProductSpecificationDTO.builder()
                .specificationId(productSpecification.getSpecificationId())
                .key(productSpecification.getKey())
                .value(productSpecification.getValue())
                .build();
    }
}