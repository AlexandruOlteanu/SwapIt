package com.swapit.product.service;

import com.swapit.product.api.domain.request.ProductCreationRequest;

public interface ProductCreateService {

    Integer createProduct(ProductCreationRequest request);

}
