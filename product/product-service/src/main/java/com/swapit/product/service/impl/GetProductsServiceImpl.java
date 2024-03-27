package com.swapit.product.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductLike;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.repository.ProductLikeRepository;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.GetProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.swapit.product.util.ProductLikeStatus.ACTIVE;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductsServiceImpl implements GetProductsService {

    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;

    @Override
    public GetProductsResponse getProductsByUser(Integer userId) {
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
    public GetProductsResponse getLikedProductsByUser(Integer userId) {
        List<Integer> productsLikedByUserIds = productLikeRepository.findAllByUserId(userId).orElse(new ArrayList<>())
                .stream().filter(productLike -> productLike.getStatus().equals(ACTIVE.name()))
                .map(ProductLike::getProductId)
                .toList();
        List<Product> products = productRepository.findAllByProductIdIn(productsLikedByUserIds).orElse(new ArrayList<>());
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
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        return ProductMapper.productToProductDto(product);
    }

    @Override
    public GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request) {
        List<Product> products = productRepository.findAllByCategoryIdIn(request.getCategoriesIds())
                .orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .toList();
        return GetProductsByCategoryResponse.builder()
                .products(productDTOS)
                .build();
    }

}
