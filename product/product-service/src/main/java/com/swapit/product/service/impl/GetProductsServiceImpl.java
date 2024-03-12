package com.swapit.product.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.domain.Product;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.GetProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.swapit.commons.cache.ConfigConstants.CACHE_PRODUCTS_FOR_USERS;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductsServiceImpl implements GetProductsService {

    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = CACHE_PRODUCTS_FOR_USERS, key = "#userId")
    public GetProductsResponse getAllProductsByUserId(Integer userId) {
        List<Product> products = productRepository.findAllByUserIdOrderByCreationDateDesc(userId)
                .orElse(new ArrayList<>());
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::toDTO).toList();
        return GetProductsResponse.builder()
                .products(productDTOS)
                .build();
    }
}
