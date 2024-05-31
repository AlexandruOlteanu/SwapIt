package com.swapit.product.api.domain.response;

import com.swapit.product.api.domain.dto.ProductLikeStatusDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductsLikeStatusResponse {
    private List<ProductLikeStatusDTO> productsLikeStatus;
}
