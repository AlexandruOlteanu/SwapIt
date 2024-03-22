package com.swapit.product.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.domain.response.SearchProductData;
import com.swapit.product.domain.Product;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.GetProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductsServiceImpl implements GetProductsService {

    private final ProductRepository productRepository;

    @Override
    public GetProductsResponse getProductsByUserId(Integer userId) {
        List<Product> products = productRepository.findAllByUserId(userId).orElse(new ArrayList<>());
        products.sort(Comparator.comparing(Product::getCreationDate).reversed());
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request) {
        List<Product> products = productRepository.findAllByProductIdIn(request.getProductIds())
                .orElseThrow(() -> new RuntimeException("No results found"));
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsByIdsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public SearchProductData searchProductData(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product id not found"));
        return SearchProductData.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategoryId())
                .title(product.getTitle())
                .description(product.getDescription())
                .build();
    }

}
