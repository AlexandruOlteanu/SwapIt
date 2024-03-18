package com.swapit.searchEngine.service.impl;

import com.swapit.commons.cache.CacheInvalidateService;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductSubcategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.dto.ProductCategoryDTO;
import com.swapit.searchEngine.api.service.dto.ProductSubcategoryDTO;
import com.swapit.searchEngine.domain.ProductCategory;
import com.swapit.searchEngine.domain.ProductSubcategory;
import com.swapit.searchEngine.repository.ProductCategoryRepository;
import com.swapit.searchEngine.repository.ProductSubcategoryRepository;
import com.swapit.searchEngine.service.ProductCategorizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.swapit.commons.cache.CacheConstants.CACHE_PRODUCT_CATEGORIES;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategorizeServiceImpl implements ProductCategorizeService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductSubcategoryRepository productSubcategoryRepository;
    private final CacheInvalidateService cacheInvalidateService;

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findFirstByValue(request.getCategory());
        if (productCategory.isPresent()) {
            throw new RuntimeException("Product Category already exists!");
        }
        ProductCategory newProductCategory = ProductCategory.builder()
                .value(request.getCategory())
                .build();
        productCategoryRepository.save(newProductCategory);
        cacheInvalidateService.invalidateAllCacheWithValue(CACHE_PRODUCT_CATEGORIES);
    }

    @Override
    public void addNewProductSubcategory(AddNewProductSubcategoryRequest request) {
        ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Product Category doesn't exist"));
        Optional<ProductSubcategory> productSubcategory = productSubcategoryRepository.findFirstByProductCategory(productCategory);
        if (productSubcategory.isPresent()) {
            throw new RuntimeException("Product Subcategory already exists!");
        }
        ProductSubcategory newProductSubcategory = ProductSubcategory.builder()
                .productCategory(productCategory)
                .value(request.getSubcategory())
                .build();
        productSubcategoryRepository.save(newProductSubcategory);
        cacheInvalidateService.invalidateAllCacheWithValue(CACHE_PRODUCT_CATEGORIES);
    }

    @Override
    @Cacheable(value = CACHE_PRODUCT_CATEGORIES)
    public GetProductCategoriesResponse getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<ProductCategoryDTO> productCategoryDTOS = productCategories.stream()
                .map(productCategory -> {
                    List<ProductSubcategoryDTO> productSubcategoryDTOS = productCategory.getProductSubcategories()
                            .stream().map(productSubcategory -> ProductSubcategoryDTO.builder()
                                    .subcategoryId(productSubcategory.getProductSubcategoryId())
                                    .value(productSubcategory.getValue())
                                    .build()).toList();
                    return ProductCategoryDTO.builder()
                            .categoryId(productCategory.getProductCategoryId())
                            .value(productCategory.getValue())
                            .productSubcategories(productSubcategoryDTOS)
                            .build();
                }).toList();
        return GetProductCategoriesResponse.builder()
                .productCategories(productCategoryDTOS)
                .build();
    }
}
