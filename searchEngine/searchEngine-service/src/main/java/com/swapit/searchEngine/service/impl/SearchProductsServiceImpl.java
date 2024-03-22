package com.swapit.searchEngine.service.impl;

import com.swapit.commons.utils.Pair;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.service.ProductPublicService;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.searchEngine.api.service.dto.SearchProductDTO;
import com.swapit.searchEngine.service.SearchIndexService;
import com.swapit.searchEngine.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchProductsServiceImpl implements SearchProductsService {
    private final SearchIndexService searchIndexService;
    private final ProductPublicService productPublicService;

    @Override
    public SearchProductsResponse searchProducts(SearchProductsRequest request) throws IOException {
        List<Pair<Integer, Integer>> productsScore = searchIndexService.searchMatchingProductsScore(request.getQuery());
        List<Integer> productIds = productsScore.stream()
                .map(Pair::getFirst)
                .toList();
        GetProductsByIdsResponse response = Objects.requireNonNull(productPublicService.getProductsByIds(GetProductsByIdsRequest.builder()
                        .productIds(productIds)
                .build()).getBody());
        Map<Integer, ProductDTO> mappedProducts = response.getProducts().stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));
        List<ProductDTO> orderedProducts = productIds.stream()
                .map(mappedProducts::get)
                .toList();

        List<SearchProductDTO> searchProductDTOS = orderedProducts.stream()
                .map(productDTO -> SearchProductDTO.builder()
                        .productId(productDTO.getProductId())
                        .userId(productDTO.getUserId())
                        .creationDate(productDTO.getCreationDate())
                        .title(productDTO.getTitle())
                        .description(productDTO.getDescription())
                        .categoryTree(searchIndexService.getCategoryTree(productDTO.getCategoryId()))
                        .popularity(productDTO.getPopularity())
                        .build())
                .toList();
        return SearchProductsResponse.builder()
                .searchProducts(searchProductDTOS)
                .build();
    }
}
