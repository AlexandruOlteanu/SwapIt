package com.swapit.searchEngine.service.impl;

import com.swapit.commons.utils.Pair;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.searchEngine.api.service.dto.CategoryTreeValueDTO;
import com.swapit.searchEngine.api.service.dto.SearchProductDTO;
import com.swapit.searchEngine.mappers.SearchProductMapper;
import com.swapit.searchEngine.service.ExternalOperationsService;
import com.swapit.searchEngine.service.ProductCategorizeService;
import com.swapit.searchEngine.service.SearchDictionaryService;
import com.swapit.searchEngine.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.swapit.searchEngine.util.SearchSortCriteria.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchProductsServiceImpl implements SearchProductsService {
    private final SearchDictionaryService searchDictionaryService;
    private final ProductCategorizeService productCategorizeService;
    private final ExternalOperationsService externalOperationsService;

    @Override
    public SearchProductsResponse searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request) throws Exception {
        if (sortCriteria == null) {
            sortCriteria = BEST_MATCH.name();
        }
        List<Pair<Integer, Integer>> productsScore = searchDictionaryService.searchMatchingProductsScore(request.getQuery());
        List<Integer> productIds = productsScore.stream()
                .map(Pair::getFirst)
                .toList();
        GetProductsByIdsResponse response = Objects.requireNonNull(externalOperationsService.getProductsByIds(GetProductsByIdsRequest.builder()
                .productIds(productIds)
                .build()));
        Map<Integer, ProductDTO> mappedProducts = response.getProducts().stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));
        List<ProductDTO> orderedProducts = productIds.stream()
                .map(mappedProducts::get)
                .toList();

        List<SearchProductDTO> searchProductDTOS = new ArrayList<>(orderedProducts.stream()
                .map(SearchProductMapper::prodDtoToSearchProdDto).toList());
        if (sortCriteria.equalsIgnoreCase(NEWEST.name())) {
            searchProductDTOS.sort(Comparator.comparing(SearchProductDTO::getCreationDate).reversed());
        }
        if (sortCriteria.equalsIgnoreCase(POPULARITY.name())) {
            searchProductDTOS.sort(Comparator.comparing(SearchProductDTO::getPopularity).reversed());
        }
        if (sortCriteria.equalsIgnoreCase(RANDOM.name())) {
            Collections.shuffle(searchProductDTOS);
        }
        int startIdx = chunkNumber * nrElementsPerChunk;
        int endIdx = startIdx + nrElementsPerChunk;
        int listSize = searchProductDTOS.size();
        if (endIdx > listSize) endIdx = listSize;
        List<SearchProductDTO> finalSearchResults = IntStream.range(startIdx, endIdx)
                .mapToObj(searchProductDTOS::get)
                .toList();
        return SearchProductsResponse.builder()
                .searchProducts(finalSearchResults)
                .build();
    }

    @Override
    public SearchProductsResponse searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        var childCategoryTreeIds = productCategorizeService.getCategoryTree(categoryId).getChildCategories().stream()
                .map(CategoryTreeValueDTO::getCategoryId).toList();
        GetProductsByCategoryResponse response = externalOperationsService.getProductsByCategory(chunkNumber, nrElementsPerChunk, sortCriteria,
                GetProductsByCategoryRequest.builder()
                        .categoriesIds(childCategoryTreeIds)
                        .build());
        assert response != null;
        List<SearchProductDTO> searchProductDTOS = response.getProducts().stream()
                .map(SearchProductMapper::prodDtoToSearchProdDto).toList();
        return SearchProductsResponse.builder()
                .searchProducts(searchProductDTOS)
                .build();
    }
}
