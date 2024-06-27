package com.swapit.product.projections;


public interface ProductProjection {
    Integer getProductId();
    Integer getUserId();
    String getTitle();
    Double getPrice();
    Integer getPopularity();
}
