package com.swapit.searchEngine.api.service.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class SearchProductsRequest {
    private String query;

    // Batch Processing params
    @Builder.Default
    private Integer chunkNumber = 0;
    @Builder.Default
    private Integer nrElementsPerChunk = Integer.MAX_VALUE;
    private String sortCriteria;

}
