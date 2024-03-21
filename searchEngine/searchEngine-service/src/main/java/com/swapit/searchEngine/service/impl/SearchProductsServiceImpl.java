package com.swapit.searchEngine.service.impl;

import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.searchEngine.service.SearchIndexService;
import com.swapit.searchEngine.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchProductsServiceImpl implements SearchProductsService {
    private final SearchIndexService searchIndexService;

    @Override
    public SearchProductsResponse searchProducts(SearchProductsRequest request) throws IOException {
        Set<Integer> productIds = searchIndexService.searchMatchingProductsIds(request.getQuery());

        return SearchProductsResponse.builder()
                .build();
    }
}
