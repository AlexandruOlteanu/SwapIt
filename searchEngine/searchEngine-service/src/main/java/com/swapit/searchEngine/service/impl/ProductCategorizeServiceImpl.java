package com.swapit.searchEngine.service.impl;

import com.swapit.commons.cache.CacheInvalidateService;
import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.GetProductCategoryIdRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.dto.CategoryTreeValueDTO;
import com.swapit.searchEngine.api.service.dto.ProductCategoryDTO;
import com.swapit.searchEngine.domain.ProductCategory;
import com.swapit.searchEngine.repository.ProductCategoryRepository;
import com.swapit.searchEngine.service.ProductCategorizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.swapit.commons.cache.CacheConstants.CACHE_PRODUCT_CATEGORIES;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategorizeServiceImpl implements ProductCategorizeService {

    private final ProductCategoryRepository productCategoryRepository;
    private final CacheInvalidateService cacheInvalidateService;
    private final ExceptionFactory exceptionFactory;

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {

        CompletableFuture<ProductCategory> parentFuture = CompletableFuture.supplyAsync(() ->
                request.getParentId() == null ? null :
                        productCategoryRepository.findById(request.getParentId())
                                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PARENT_CATEGORY_NOT_FOUND))
        );

        CompletableFuture<Optional<ProductCategory>> existingCategoryFuture = CompletableFuture.supplyAsync(() ->
                productCategoryRepository.findFirstByValue(request.getCategory())
        );

        ProductCategory parentCategory = parentFuture.join();
        Optional<ProductCategory> existingCategory = existingCategoryFuture.join();

        if (existingCategory.isPresent()) {
            throw exceptionFactory.create(ExceptionType.PRODUCT_CATEGORY_ALREADY_EXISTS);
        }

        ProductCategory newProductCategory = ProductCategory.builder()
                .value(request.getCategory())
                .parent(parentCategory)
                .build();
        productCategoryRepository.save(newProductCategory);
        cacheInvalidateService.invalidateAllCacheWithValue(CACHE_PRODUCT_CATEGORIES);
    }

    @Override
    public Integer getProductCategoryId(GetProductCategoryIdRequest request) {
        ProductCategory productCategory = productCategoryRepository.findProductCategoryByValue(request.getCategoryName())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_CATEGORY_NOT_FOUND));
        return productCategory.getProductCategoryId();
    }


    @Override
    public GetCategoryTreeResponse getCategoryTree(Integer categoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_CATEGORY_NOT_FOUND));

        return GetCategoryTreeResponse.builder()
                .parentCategories(findParentCategories(productCategory))
                .childCategories(findChildCategories(productCategory))
                .build();
    }

    private List<CategoryTreeValueDTO> findParentCategories(ProductCategory productCategory) {
        List<CategoryTreeValueDTO> categoryTree = new ArrayList<>();
        while (productCategory != null) {
            categoryTree.addFirst(CategoryTreeValueDTO.builder()
                            .categoryId(productCategory.getProductCategoryId())
                            .value(productCategory.getValue())
                    .build());
            productCategory = productCategory.getParent();
        }
        return categoryTree;
    }

    private List<CategoryTreeValueDTO> findChildCategories(ProductCategory productCategory) {
        List<CategoryTreeValueDTO> categoryTree = new ArrayList<>();
        categoryTree.addFirst(CategoryTreeValueDTO.builder()
                .categoryId(productCategory.getProductCategoryId())
                .value(productCategory.getValue())
                .build());
        productCategory.getSubcategories()
                .forEach(subcategory -> {
                    List<CategoryTreeValueDTO> subCategoryTree = findChildCategories(subcategory);
                    categoryTree.addAll(subCategoryTree);
                });
        return categoryTree;
    }


    private List<ProductCategoryDTO> processCategories(List<ProductCategory> productCategories) {
        return productCategories.stream()
                .map(productCategory -> ProductCategoryDTO.builder()
                        .categoryId(productCategory.getProductCategoryId())
                        .value(productCategory.getValue())
                        .subcategories(null == productCategory.getSubcategories() ?
                                null : processCategories(productCategory.getSubcategories()))
                        .build()).toList();
    }
}
