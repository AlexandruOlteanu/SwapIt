package com.swapit.searchEngine.api.service.domain.response;

import com.swapit.searchEngine.api.service.dto.SearchProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class SearchProductsResponse {
    List<SearchProductDTO> searchProducts;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalItems;
    private Integer itemsPerPage;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
}
