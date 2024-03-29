package com.swapit.searchEngine.api.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Jacksonized
@Builder
public class SearchProductDTO {
    private Integer productId;
    private Integer userId;
    private ZonedDateTime creationDate;
    private String title;
    private String description;
    private Integer categoryId;
    private Integer popularity;
    private List<String> productImages;
}
