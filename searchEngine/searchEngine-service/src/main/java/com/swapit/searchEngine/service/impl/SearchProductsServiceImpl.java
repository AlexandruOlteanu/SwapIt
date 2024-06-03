package com.swapit.searchEngine.service.impl;

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
import com.swapit.searchEngine.util.SearchPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        SearchPagination productsData = searchDictionaryService.searchMatchingProducts(request.getQuery(), chunkNumber, nrElementsPerChunk, sortCriteria);
        GetProductsByIdsResponse response = Objects.requireNonNull(externalOperationsService.getProductsByIds(GetProductsByIdsRequest.builder()
                .productIds(productsData.getProductIds())
                .build()));
        Map<Integer, ProductDTO> mappedProducts = response.getProducts().stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));
        List<ProductDTO> orderedProducts = productsData.getProductIds().stream()
                .map(mappedProducts::get)
                .toList();

        List<SearchProductDTO> searchProductDTOS = new ArrayList<>(orderedProducts.stream()
                .map(SearchProductMapper::prodDtoToSearchProdDto).toList());

        return SearchProductsResponse.builder()
                .searchProducts(searchProductDTOS)
                .currentPage(chunkNumber)
                .totalPages(productsData.getTotalNumberOfPages())
                .itemsPerPage(nrElementsPerChunk)
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
                .currentPage(response.getCurrentPage())
                .totalPages(response.getTotalPages())
                .totalItems(response.getTotalItems())
                .itemsPerPage(response.getItemsPerPage())
                .hasNextPage(response.getHasNextPage())
                .hasPreviousPage(response.getHasPreviousPage())
                .build();
    }
}
