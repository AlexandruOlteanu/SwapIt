package com.swapit.product.api.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@Jacksonized
public class ProductDTO {
    private Integer productId;
    private Integer userId;
    private ZonedDateTime creationDate;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String subcategory;
    List<ProductSpecificationDTO> productSpecifications;
}
