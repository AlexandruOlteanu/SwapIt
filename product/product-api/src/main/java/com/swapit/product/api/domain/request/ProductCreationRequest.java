package com.swapit.product.api.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class ProductCreationRequest {

    @NotNull
    private Integer userId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Double price;
    @NotNull
    private String category;
    @NotNull
    private String subcategory;
    Map<String, String> productSpecifications;
}
