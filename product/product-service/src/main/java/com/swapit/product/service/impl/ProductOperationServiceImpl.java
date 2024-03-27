package com.swapit.product.service.impl;

import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductSpecification;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.repository.ProductSpecificationRepository;
import com.swapit.product.service.ProductOperationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOperationServiceImpl implements ProductOperationService {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository productSpecificationsRepository;
    private final Integer ZERO = 0;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer createProduct(CreateProductRequest request) {
        Integer userId = request.getUserId();
        Product product = Product.builder()
                .title(request.getTitle())
                .userId(userId)
                .description(request.getDescription())
                .price(request.getPrice())
                .categoryId(request.getCategoryId())
                .popularity(ZERO)
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

    @Override
    @Transactional
    public void updateProduct(UpdateProductRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow();
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());

        List<ProductSpecification> newSpecifications = request.getProductSpecifications().stream()
            .filter(specification -> specification.getSpecificationId() == null)
                    .map(specification -> ProductSpecification.builder()
                            .key(specification.getKey())
                            .value(specification.getValue())
                            .product(product)
                            .build()).toList();
        List<ProductSpecification> existingSpecifications = request.getProductSpecifications().stream()
            .filter(specification -> specification.getSpecificationId() != null)
            .map(specification -> ProductSpecification.builder()
                    .specificationId(specification.getSpecificationId())
                    .key(specification.getKey())
                    .value(specification.getValue())
                    .product(product)
                    .build()).toList();

        product.getProductSpecifications().removeIf(specification ->
                existingSpecifications.stream().noneMatch(reqSpec -> reqSpec.getSpecificationId().equals(specification.getSpecificationId())));

        existingSpecifications.forEach(specification -> {
                    ProductSpecification existingSpec = product.getProductSpecifications().stream()
                            .filter(spec -> spec.getSpecificationId().equals(specification.getSpecificationId()))
                            .findFirst()
                            .orElse(null);
                    assert existingSpec != null;
                    existingSpec.setKey(specification.getKey());
                    existingSpec.setValue(specification.getValue());
                    }
                );
        product.getProductSpecifications().addAll(newSpecifications);
    }
}
