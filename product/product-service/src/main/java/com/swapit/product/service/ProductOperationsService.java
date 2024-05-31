package com.swapit.product.service;

import com.swapit.product.api.domain.request.ChangeProductLikeStatusRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.GetProductsLikeStatusRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.api.domain.response.GetProductsLikeStatusResponse;

public interface ProductOperationsService {

    Integer createProduct(Integer userId, CreateProductRequest request);
    void updateProduct(Integer userId, UpdateProductRequest request);
    void deleteProduct(Integer userId, Integer productId);
    void deleteProductAdmin(Integer productId);
    void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request);
    GetProductsLikeStatusResponse getProductsLikeStatus(Integer userId, GetProductsLikeStatusRequest request);
}
