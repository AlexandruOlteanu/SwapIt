package com.swapit.product.web;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.*;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.service.ProductService;
import com.swapit.product.service.GetProductsService;
import com.swapit.product.service.ProductOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductService {

    private final ProductOperationsService productOperationsService;
    private final GetProductsService getProductsService;

    @Override
    public ResponseEntity<Integer> createProduct(Integer userId, CreateProductRequest request) {
        return ResponseEntity.ok(productOperationsService.createProduct(userId, request));
    }

    @Override
    public void updateProduct(Integer userId, UpdateProductRequest request) {
        productOperationsService.updateProduct(userId, request);
    }

    @Override
    public void deleteProduct(Integer userId, Integer productId) {
        productOperationsService.deleteProduct(userId, productId);
    }

    @Override
    public void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request) {
        productOperationsService.changeProductLikeStatus(userId, request);
    }

    @Override
    public ResponseEntity<String> getProductLikeStatus(Integer userId, Integer productId) {
        return ResponseEntity.ok(productOperationsService.getProductLikeStatus(userId, productId));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(getProductsService.getProductsByUser(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(getProductsService.getRecommendedProducts(chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(getProductsService.getLikedProductsByUser(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<GetProductsByIdsResponse> getProductsByIds(GetProductsByIdsRequest request) {
        return ResponseEntity.ok(getProductsService.getProductsByIds(request));
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Integer productId) {
        return ResponseEntity.ok(getProductsService.getProductById(productId));
    }

    @Override
    public ResponseEntity<GetProductsByCategoryResponse> getProductsByCategory(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, GetProductsByCategoryRequest request) {
        return ResponseEntity.ok(getProductsService.getProductsByCategory(chunkNumber, nrElementsPerChunk, sortCriteria, request));
    }
}
