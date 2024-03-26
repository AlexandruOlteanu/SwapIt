package com.swapit.product.web;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.service.ProductService;
import com.swapit.product.service.GetProductsService;
import com.swapit.product.service.ProductCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductService {

    private final ProductCreateService productCreateService;
    private final GetProductsService getProductsService;

    @Override
    public ResponseEntity<Integer> createProduct(ProductCreationRequest request) {
        return ResponseEntity.ok(productCreateService.createProduct(request));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getProductsByUserId(Integer userId) {
        return ResponseEntity.ok(getProductsService.getProductsByUserId(userId));
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
    public ResponseEntity<GetProductsByCategoryResponse> getProductsByCategory(GetProductsByCategoryRequest request) {
        return ResponseEntity.ok(getProductsService.getProductsByCategory(request));
    }
}
