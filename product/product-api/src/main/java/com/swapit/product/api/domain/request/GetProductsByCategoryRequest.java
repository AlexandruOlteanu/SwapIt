package com.swapit.product.api.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductsByCategoryRequest {
    List<Integer> categoriesIds;

    // Batch Processing params
    @Builder.Default
    Integer chunkNumber = 0;
    @Builder.Default
    Integer nrElementsPerChunk = Integer.MAX_VALUE;
    String sortCriteria;
}
