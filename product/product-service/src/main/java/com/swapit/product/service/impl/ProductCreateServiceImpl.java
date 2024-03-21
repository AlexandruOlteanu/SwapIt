package com.swapit.product.service.impl;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductSpecification;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.repository.ProductSpecificationRepository;
import com.swapit.product.service.ProductCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository productSpecificationsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer createProduct(ProductCreationRequest request) {
        Integer userId = request.getUserId();
        Product product = Product.builder()
                .title(request.getTitle())
                .userId(userId)
                .description(request.getDescription())
                .price(request.getPrice())
                .categoryId(request.getCategoryId())
                .build();
        Integer productId = productRepository.save(product).getProductId();
        request.getProductSpecifications()
                .forEach((key, value) ->
                        productSpecificationsRepository.save(ProductSpecification.builder()
                                        .product(product)
                                        .key(key)
                                        .value(value)
                .build()));
        return productId;
    }
}
