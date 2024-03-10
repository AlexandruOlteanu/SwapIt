package com.swapit.product.web;

import com.swapit.apiGateway.api.dto.response.ProductDTO;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.service.ProductService;
import com.swapit.product.service.InternalRequestService;
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
    private final InternalRequestService internalRequestService;


    @Override
    public void createProduct(ProductCreationRequest request) throws Exception {
        productCreateService.createProduct(request);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProductsByUserId(Integer userId) {
        return ResponseEntity.ok(internalRequestService.getAllProductsByUserId(userId));
    }
}
