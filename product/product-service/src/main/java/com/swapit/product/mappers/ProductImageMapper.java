package com.swapit.product.mappers;

import com.swapit.product.api.domain.dto.ProductImageDTO;
import com.swapit.product.domain.ProductImage;

public class ProductImageMapper {
    public static ProductImage prodImageDtoToProdImage(ProductImageDTO productImageDTO) {
        return ProductImage.builder()
                .imageUrl(productImageDTO.getImageUrl())
                .build();
    }

    public static ProductImageDTO prodImageToProdImageDto(ProductImage productImage) {
        return ProductImageDTO.builder()
                .imageUrl(productImage.getImageUrl())
                .build();
    }
}
