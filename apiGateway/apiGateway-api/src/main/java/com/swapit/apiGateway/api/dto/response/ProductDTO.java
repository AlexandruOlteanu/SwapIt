package com.swapit.apiGateway.api.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Jacksonized
@Builder
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
