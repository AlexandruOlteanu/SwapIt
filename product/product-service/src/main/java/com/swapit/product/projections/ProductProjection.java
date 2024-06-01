package com.swapit.product.projections;

import java.util.List;

public interface ProductProjection {
    Integer getProductId();
    String getTitle();
    Double getPrice();
    Integer getPopularity();
    List<ProductImageProjection> getProductImages();
}
