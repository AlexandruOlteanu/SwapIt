package com.swapit.product.web;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.service.ProductService;
import com.swapit.product.service.GetProductsService;
import com.swapit.product.service.ProductCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductService {

    private final ProductCreateService productCreateService;
    private final GetProductsService getProductsService;

    @Override
    public void createProduct(ProductCreationRequest request) throws Exception {
        productCreateService.createProduct(request);
    }

    @Override
    public ResponseEntity<GetProductsResponse> getAllProductsByUserId(Integer userId) {
        return ResponseEntity.ok(getProductsService.getAllProductsByUserId(userId));
    }
}
