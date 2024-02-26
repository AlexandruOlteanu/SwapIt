package com.swapit.product.service;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Propagation;

public interface ProductCreateService {


    void createProduct(ProductCreationRequest request) throws Exception;

}
