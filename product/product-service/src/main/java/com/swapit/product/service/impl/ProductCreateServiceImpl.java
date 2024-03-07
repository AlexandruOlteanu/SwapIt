package com.swapit.product.service.impl;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductSpecifications;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.repository.ProductSpecificationsRepository;
import com.swapit.product.service.ExternalOperationsService;
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
    private final ExternalOperationsService externalOperationsService;
    private final ProductSpecificationsRepository productSpecificationsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createProduct(ProductCreationRequest request) throws Exception {
        Integer userId = externalOperationsService.getUserIdByUsernameOrEmail(request.getUsername(), null);
        if (userId == null) {
            throw new Exception("Error: User doesn't exist");
        }
        Product product = Product.builder()
                .title(request.getTitle())
                .userId(userId)
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .subcategory(request.getSubcategory())
                .build();
        productRepository.save(product);
        request.getProductSpecifications()
                .forEach(productSpecification -> productSpecificationsRepository.save(ProductSpecifications.builder()
                                        .product(product)
                                        .key(productSpecification.getKey())
                                        .value(productSpecification.getValue())
                                .build()));
    }
}
