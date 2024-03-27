package com.swapit.product.api.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ChangeProductLikeStatusRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer productId;
}
