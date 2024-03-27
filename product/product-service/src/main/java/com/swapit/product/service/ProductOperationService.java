package com.swapit.product.service;

import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;

public interface ProductOperationService {

    Integer createProduct(CreateProductRequest request);
    void updateProduct(UpdateProductRequest request);

}
