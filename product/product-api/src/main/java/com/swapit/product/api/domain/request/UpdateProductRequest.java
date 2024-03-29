package com.swapit.product.api.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

@Data
@Jacksonized
@Builder
public class UpdateProductRequest {

    @NotNull
    private Integer productId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Double price;
    @NotNull
    private Integer categoryId;
    Map<String, String> productSpecifications;
    List<String> productImages;
}
