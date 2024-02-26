package com.swapit.product.api.domain.request;

import com.swapit.product.api.domain.dto.ProductSpecificationsDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class ProductCreationRequest {

    private String username;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Double price;
    @NotNull
    private String category;
    @NotNull
    private String subcategory;
    List<ProductSpecificationsDTO> productSpecifications;
}
