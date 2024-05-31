package com.swapit.product.api.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ProductLikeStatusDTO {
    private Integer productId;
    private String likeStatus;
}
