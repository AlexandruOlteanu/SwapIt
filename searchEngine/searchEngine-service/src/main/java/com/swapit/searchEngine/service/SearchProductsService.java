package com.swapit.searchEngine.service;

import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;

import java.io.IOException;

public interface SearchProductsService {
    SearchProductsResponse searchProducts(SearchProductsRequest request) throws IOException;
    SearchProductsResponse searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
