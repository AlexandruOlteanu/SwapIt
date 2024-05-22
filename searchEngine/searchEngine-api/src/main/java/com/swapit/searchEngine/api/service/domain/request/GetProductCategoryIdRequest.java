package com.swapit.searchEngine.api.service.domain.request;

import com.swapit.searchEngine.api.service.dto.ProductCategoryDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductCategoryIdRequest {
    private String categoryName;
}
