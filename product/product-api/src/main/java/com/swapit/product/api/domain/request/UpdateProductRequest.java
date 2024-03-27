package com.swapit.product.api.domain.request;

import com.swapit.product.api.domain.dto.ProductSpecificationDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

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
    List<ProductSpecificationDTO> productSpecifications;
}
