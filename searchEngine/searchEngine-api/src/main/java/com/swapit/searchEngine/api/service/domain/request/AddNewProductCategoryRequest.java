package com.swapit.searchEngine.api.service.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class AddNewProductCategoryRequest {
    private Integer parentId;
    @NotNull
    private String category;
}
