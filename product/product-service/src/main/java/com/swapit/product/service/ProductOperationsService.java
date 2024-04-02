package com.swapit.product.service;

import com.swapit.product.api.domain.request.ChangeProductLikeStatusRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;

public interface ProductOperationsService {

    Integer createProduct(Integer userId, CreateProductRequest request);
    void updateProduct(Integer userId, UpdateProductRequest request);
    void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request);
    String getProductLikeStatus(Integer userId, Integer productId);
}
