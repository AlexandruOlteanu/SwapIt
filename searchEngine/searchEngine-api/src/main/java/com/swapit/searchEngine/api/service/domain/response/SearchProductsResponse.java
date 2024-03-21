package com.swapit.searchEngine.api.service.domain.response;

import com.swapit.product.api.domain.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class SearchProductsResponse {
    List<ProductDTO> searchProducts;
}
