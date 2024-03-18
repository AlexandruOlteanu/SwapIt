package com.swapit.searchEngine.api.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ProductSubcategoryDTO {
    private Integer subcategoryId;
    private String value;
}
