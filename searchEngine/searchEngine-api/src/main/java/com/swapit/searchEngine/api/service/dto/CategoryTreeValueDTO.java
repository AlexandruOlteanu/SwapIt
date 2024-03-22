package com.swapit.searchEngine.api.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class CategoryTreeValueDTO {
    private Integer categoryId;
    private String value;
}
