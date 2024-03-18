package com.swapit.searchEngine.api.service.domain.response;

import com.swapit.searchEngine.api.service.dto.ProductCategoryDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductCategoriesResponse {
    List<ProductCategoryDTO> productCategories;
}
