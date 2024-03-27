package com.swapit.product.service;

import com.swapit.product.api.domain.request.ChangeProductLikeStatusRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;

public interface ProductOperationsService {

    Integer createProduct(CreateProductRequest request);
    void updateProduct(UpdateProductRequest request);
    void changeProductLikeStatus(ChangeProductLikeStatusRequest request);
    String getProductLikeStatus(Integer userId, Integer productId);
}
