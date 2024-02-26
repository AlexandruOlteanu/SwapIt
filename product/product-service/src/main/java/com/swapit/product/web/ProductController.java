package com.swapit.product.web;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.service.ProductService;
import com.swapit.product.service.ProductCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductService {

    private final ProductCreateService productCreateService;

    @Override
    public void createProduct(ProductCreationRequest request) throws Exception {
        productCreateService.createProduct(request);
    }
}
