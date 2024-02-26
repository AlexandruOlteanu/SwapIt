package com.swapit.product.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ProductSpecificationsDTO {
    @NotNull
    private String key;
    @NotNull
    private String value;
}
