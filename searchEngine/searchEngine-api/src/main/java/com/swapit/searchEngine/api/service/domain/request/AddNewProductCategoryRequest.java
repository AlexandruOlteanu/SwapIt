package com.swapit.searchEngine.api.service.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class AddNewProductCategoryRequest {
    private String category;
}
