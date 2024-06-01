package com.swapit.product.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.dto.ProductImageDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductLike;
import com.swapit.product.mappers.ProductMapper;
import com.swapit.product.projections.ProductProjection;
import com.swapit.product.repository.ProductLikeRepository;
import com.swapit.product.repository.ProductRepository;
import com.swapit.product.service.GetProductsService;
import com.swapit.product.util.ProductLikeSortCriteria;
import com.swapit.product.util.ProductSortCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.swapit.product.util.ProductLikeStatus.ACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductsServiceImpl implements GetProductsService {

    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    public GetProductsResponse getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = ProductSortCriteria.NEWEST.name();
        }
        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductSortingCriteria(sortCriteria)).descending());
        Page<ProductProjection> data = productRepository.findAllByUserId(userId, pageable);
        List<ProductDTO> productDTOS = data.getContent().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        return createGetProductsResponse(data, productDTOS);
    }

    @Override
    public GetProductsResponse getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = ProductLikeSortCriteria.NEWEST.name();
        }
        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductLikeSortingCriteria(sortCriteria)).descending());
        Page<ProductLike> data = productLikeRepository.findAllByUserIdAndStatus(userId, ACTIVE.name(), pageable);
        List<Integer> productsLikedByUserIds = data.getContent().stream()
                .map(ProductLike::getProductId)
                .collect(Collectors.toList());
        List<Product> products = productRepository.findAllByProductIdIn(productsLikedByUserIds)
                .orElse(List.of());
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .collect(Collectors.toList());
        return createGetProductsResponse(data, productDTOS);
    }

    @Override
    public GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request) {
        List<Product> products = productRepository.findAllByProductIdIn(request.getProductIds())
                .orElse(List.of());
        List<ProductDTO> productDTOS = products.stream()
                .map(ProductMapper::productToProductDto)
                .collect(Collectors.toList());
        return GetProductsByIdsResponse.builder()
                .products(productDTOS)
                .build();
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.PRODUCT_NOT_FOUND));
        return ProductMapper.productToProductDto(product);
    }

    @Override
    public GetProductsByCategoryResponse getProductsByCategory(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, GetProductsByCategoryRequest request) {
        if (sortCriteria == null) {
            sortCriteria = ProductSortCriteria.POPULARITY.name();
        }
        Pageable pageable;
        Page<ProductProjection> data;
        if (sortCriteria.equalsIgnoreCase(ProductSortCriteria.RANDOM.name())) {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk);
            data = productRepository.findAllRandomByCategoryIdIn(request.getCategoriesIds(), pageable);
        } else {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductSortingCriteria(sortCriteria)).descending());
            data = productRepository.findAllByCategoryIdIn(request.getCategoriesIds(), pageable);
        }
        List<ProductDTO> productDTOS = data.getContent().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        return createGetProductsByCategoryResponse(data, productDTOS);
    }

    @Override
    public GetProductsResponse getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = ProductSortCriteria.POPULARITY.name();
        }
        Pageable pageable;
        Page<ProductProjection> data;
        if (sortCriteria.equalsIgnoreCase(ProductSortCriteria.RANDOM.name())) {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk);
            data = productRepository.findAllRandom(pageable);
        } else {
            pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getProductSortingCriteria(sortCriteria)).descending());
            data = productRepository.findAllProd(pageable);
        }
        List<ProductDTO> productDTOS = data.getContent().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        return createGetProductsResponse(data, productDTOS);
    }

    private ProductDTO convertToProductDTO(ProductProjection projection) {
        List<ProductImageDTO> images = projection.getProductImages().stream()
                .map(imageProjection -> ProductImageDTO.builder()
                        .imageUrl(imageProjection.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        return ProductDTO.builder()
                .productId(projection.getProductId())
                .title(projection.getTitle())
                .price(projection.getPrice())
                .popularity(projection.getPopularity())
                .productImages(images)
                .build();
    }

    private GetProductsResponse createGetProductsResponse(Page<?> data, List<ProductDTO> productDTOS) {
        return GetProductsResponse.builder()
                .products(productDTOS)
                .currentPage(data.getNumber())
                .totalPages(data.getTotalPages())
                .totalItems((int) data.getTotalElements())
                .itemsPerPage(data.getSize())
                .hasNextPage(data.hasNext())
                .hasPreviousPage(data.hasPrevious())
                .build();
    }

    private GetProductsByCategoryResponse createGetProductsByCategoryResponse(Page<?> data, List<ProductDTO> productDTOS) {
        return GetProductsByCategoryResponse.builder()
                .products(productDTOS)
                .currentPage(data.getNumber())
                .totalPages(data.getTotalPages())
                .totalItems((int) data.getTotalElements())
                .itemsPerPage(data.getSize())
                .hasNextPage(data.hasNext())
                .hasPreviousPage(data.hasPrevious())
                .build();
    }

    private String getProductSortingCriteria(String criteria) {
        ProductSortCriteria productSortCriteria = ProductSortCriteria.valueOf(criteria.toUpperCase());
        return switch (productSortCriteria) {
            case NEWEST -> "creationDate";
            case POPULARITY -> "popularity";
            case RANDOM -> "random";
        };
    }

    private String getProductLikeSortingCriteria(String criteria) {
        ProductLikeSortCriteria productLikeSortCriteria = ProductLikeSortCriteria.valueOf(criteria.toUpperCase());
        return switch (productLikeSortCriteria) {
            case NEWEST -> "lastUpdateAction";
        };
    }
}
