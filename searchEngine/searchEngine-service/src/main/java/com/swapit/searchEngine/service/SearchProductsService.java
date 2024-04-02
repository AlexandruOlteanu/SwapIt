package com.swapit.searchEngine.service;

import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;

import java.io.IOException;

public interface SearchProductsService {
    SearchProductsResponse searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request) throws Exception;
    SearchProductsResponse searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
