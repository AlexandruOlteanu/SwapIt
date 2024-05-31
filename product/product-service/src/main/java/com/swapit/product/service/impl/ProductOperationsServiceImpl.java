package com.swapit.product.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.product.api.domain.dto.ProductLikeStatusDTO;
import com.swapit.product.api.domain.request.ChangeProductLikeStatusRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.GetProductsLikeStatusRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.api.domain.response.GetProductsLikeStatusResponse;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductImage;
import com.swapit.product.domain.ProductLike;
import com.swapit.product.domain.ProductSpecification;
import com.swapit.product.repository.ProductImageRepository;
import com.swapit.product.repository.ProductLikeRepository;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.repository.ProductSpecificationRepository;
import com.swapit.product.service.ProductOperationsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.swapit.product.util.ProductLikeStatus.ACTIVE;
import static com.swapit.product.util.ProductLikeStatus.INACTIVE;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOperationsServiceImpl implements ProductOperationsService {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository productSpecificationsRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductImageRepository productImageRepository;
    private final ExceptionFactory exceptionFactory;
    private final Integer ZERO = 0;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer createProduct(Integer userId, CreateProductRequest request) {
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
                .forEach((key, value) -> productSpecificationsRepository.save(ProductSpecification.builder()
                                        .product(product)
                                        .key(key)
                                        .value(value)
                .build()));
        request.getProductImages()
                .forEach(productImageUrl -> productImageRepository.save(ProductImage.builder()
                                        .product(product)
                                        .imageUrl(productImageUrl)
                                .build()));
        return productId;
    }

    @Override
    @Transactional
    public void updateProduct(Integer userId, UpdateProductRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_NOT_FOUND));
        if (!product.getUserId().equals(userId)) {
            throw exceptionFactory.create(ExceptionType.UNAUTHORIZED_ACTION);
        }
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());

        product.getProductSpecifications().removeIf(specification -> !request.getProductSpecifications().containsKey(specification.getKey()));
        product.getProductImages().removeIf(productImage -> !request.getProductImages().contains(productImage.getImageUrl()));
        List<String> existingSpecs = product.getProductSpecifications().stream()
                        .map(ProductSpecification::getKey).toList();
        product.getProductSpecifications().forEach(productSpecification -> {
            if (request.getProductSpecifications().containsKey(productSpecification.getKey())) {
                productSpecification.setValue(request.getProductSpecifications().get(productSpecification.getKey()));
            }
        });
        request.getProductSpecifications().forEach((key, value) -> {
            if (!existingSpecs.contains(key)) {
                product.getProductSpecifications().add(ProductSpecification.builder()
                                .key(key)
                                .value(value)
                                .product(product)
                        .build());
            }
        });
        List<String> existingImagesUrls = product.getProductImages().stream()
                .map(ProductImage::getImageUrl).toList();
        request.getProductImages().forEach(productImage -> {
            if (!existingImagesUrls.contains(productImage)) {
                product.getProductImages().add(ProductImage.builder()
                                .imageUrl(productImage)
                                .product(product)
                        .build());
            }
        });
    }

    @Override
    @Transactional
    public void deleteProduct(Integer userId, Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_NOT_FOUND));
        if (!product.getUserId().equals(userId)) {
            throw exceptionFactory.create(ExceptionType.UNAUTHORIZED_ACTION);
        }
        productLikeRepository.deleteAllByProductId(productId);
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public void deleteProductAdmin(Integer productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_NOT_FOUND));
        productLikeRepository.deleteAllByProductId(productId);
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_NOT_FOUND));
        ProductLike productLike = productLikeRepository.findProductLikeByUserIdAndProductId(userId, request.getProductId())
                .orElse(null);
        if (productLike == null) {
            ProductLike newProductLike = ProductLike.builder()
                    .userId(userId)
                    .productId(request.getProductId())
                    .status(ACTIVE.name())
                    .build();
            product.setPopularity(product.getPopularity() + 1);
            productLikeRepository.save(newProductLike);
            return;
        }
        productLike.setLastUpdateAction(ZonedDateTime.now());
        if (productLike.getStatus().equals(ACTIVE.name())) {
            product.setPopularity(product.getPopularity() - 1);
            productLike.setStatus(INACTIVE.name());
            return;
        }
        product.setPopularity(product.getPopularity() + 1);
        productLike.setStatus(ACTIVE.name());
    }

    @Override
    public GetProductsLikeStatusResponse getProductsLikeStatus(Integer userId, GetProductsLikeStatusRequest request) {
        List<ProductLike> productLike = productLikeRepository.findProductLikeByUserIdAndProductIdIn(userId, request.getProductIds())
                .orElse(new ArrayList<>());
        List<ProductLikeStatusDTO> productLikeStatusDTOList = request.getProductIds().stream()
                .map(productId -> {
                    ProductLike productLike1 = productLike.stream().filter(productLike2 -> productLike2.getProductId().equals(productId)).findFirst()
                            .orElse(null);
                    if (productLike1 == null || !productLike1.getStatus().equals(ACTIVE.name())) {
                        return ProductLikeStatusDTO.builder()
                                .productId(productId)
                                .likeStatus(INACTIVE.name())
                                .build();
                    }
                    return ProductLikeStatusDTO.builder()
                            .productId(productId)
                            .likeStatus(ACTIVE.name())
                            .build();
                })
                .toList();
        return GetProductsLikeStatusResponse.builder()
                .productsLikeStatus(productLikeStatusDTOList)
                .build();
    }
}
