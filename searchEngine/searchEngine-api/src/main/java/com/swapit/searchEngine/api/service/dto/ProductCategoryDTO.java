package com.swapit.searchEngine.api.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class ProductCategoryDTO {
    private Integer categoryId;
    private String value;
    List<ProductSubcategoryDTO> productSubcategories;
}
