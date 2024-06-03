package com.swapit.searchEngine.mappers;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.dto.ProductImageDTO;
import com.swapit.searchEngine.api.service.dto.SearchProductDTO;

public class SearchProductMapper {
    public static SearchProductDTO prodDtoToSearchProdDto(ProductDTO productDTO) {
        return SearchProductDTO.builder()
                .productId(productDTO.getProductId())
                .userId(productDTO.getUserId())
                .creationDate(productDTO.getCreationDate())
                .title(productDTO.getTitle())
                .description(productDTO.getDescription())
                .categoryId(productDTO.getCategoryId())
                .popularity(productDTO.getPopularity())
                .price(productDTO.getPrice())
                .productImages(productDTO.getProductImages().stream().map(ProductImageDTO::getImageUrl).toList())
                .build();
    }
}
