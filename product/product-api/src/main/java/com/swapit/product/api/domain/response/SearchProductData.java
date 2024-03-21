package com.swapit.product.api.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class SearchProductData {
    private Integer productId;
    private String title;
    private String description;
    private Integer categoryId;
    private List<String> categories;
}
