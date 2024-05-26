package com.swapit.product.api.domain.response;

import com.swapit.product.api.domain.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductsResponse {
    private List<ProductDTO> products;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalItems;
    private Integer itemsPerPage;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
}
