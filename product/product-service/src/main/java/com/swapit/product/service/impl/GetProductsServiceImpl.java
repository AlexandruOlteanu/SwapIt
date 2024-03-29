package com.swapit.product.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductLike;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.repository.ProductLikeRepository;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.GetProductsService;
import com.swapit.product.util.ProductLikeSortCriteria;
import com.swapit.product.util.ProductSortCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.swapit.product.util.ProductLikeStatus.ACTIVE;
import static com.swapit.product.util.ProductSortCriteria.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductsServiceImpl implements GetProductsService {

    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;

    @Override
    public GetProductsResponse getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = NEWEST.name();
        }
        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductSortingCriteria(sortCriteria)).descending());
        Page<Product> data = productRepository.findAllByUserId(userId, pageable);
        List<Product> products = data.getContent();
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public GetProductsResponse getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = NEWEST.name();
        }
        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductLikeSortingCriteria(sortCriteria)).descending());
        Page<ProductLike> data = productLikeRepository.findAllByUserIdAndStatus(userId, ACTIVE.name(), pageable);
        List<ProductLike> productsLiked = data.getContent();
        List<Integer> productsLikedByUserIds = productsLiked.stream()
                .map(ProductLike::getProductId)
                .toList();
        List<Product> products = productsLikedByUserIds.stream()
                        .map(id -> productRepository.findById(id).orElse(null)).toList();
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request) {
        List<Product> products = productRepository.findAllByProductIdIn(request.getProductIds())
                .orElseThrow(() -> new RuntimeException("No results found"));
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsByIdsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        return ProductMapper.productToProductDto(product);
    }

    @Override
    public GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request) {
        String sortCriteria = request.getSortCriteria();
        if (sortCriteria == null) {
            sortCriteria = POPULARITY.name();
        }
        Pageable pageable;
        Page<Product> data;
        if (sortCriteria.equalsIgnoreCase(RANDOM.name())) {
            pageable = PageRequest.of(request.getChunkNumber(), request.getNrElementsPerChunk());
            data = productRepository.findAllRandomByCategoryIdIn(request.getCategoriesIds(), pageable);
        }
        else {
            pageable = PageRequest.of(request.getChunkNumber(), request.getNrElementsPerChunk(), Sort.by(getProductSortingCriteria(sortCriteria)).descending());
            data = productRepository.findAllByCategoryIdIn(request.getCategoriesIds(), pageable);
        }
        List<Product> products = data.getContent();
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsByCategoryResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public GetProductsResponse getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = POPULARITY.name();
        }
        Pageable pageable;
        Page<Product> data;
        if (sortCriteria.equalsIgnoreCase(RANDOM.name())) {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk);
            data = productRepository.findAllRandom(pageable);
        }
        else {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductSortingCriteria(sortCriteria)).descending());
            data = productRepository.findAll(pageable);
        }
        List<Product> products = data.getContent();
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsResponse.builder()
                .products(productDTOS)
                .build();
    }

    private String getProductSortingCriteria(String criteria) {
        ProductSortCriteria productSortCriteria = ProductSortCriteria.valueOf(criteria.toUpperCase());
        return switch (productSortCriteria) {
            case NEWEST -> "creationDate";
            case POPULARITY -> "popularity";
            case RANDOM -> "random";
        };
    }

    private String getProductLikeSortingCriteria(String criteria) {
        ProductLikeSortCriteria productLikeSortCriteria = ProductLikeSortCriteria.valueOf(criteria.toUpperCase());
        return switch (productLikeSortCriteria) {
            case NEWEST -> "lastUpdateAction";
        };
    }

}
