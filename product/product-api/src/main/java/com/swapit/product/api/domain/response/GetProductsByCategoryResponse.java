package com.swapit.product.api.domain.response;

import com.swapit.product.api.domain.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductsByCategoryResponse {
    List<ProductDTO> products;
}
