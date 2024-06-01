package com.swapit.product.repository;

import com.swapit.product.domain.Product;
import com.swapit.product.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query("select pi from ProductImage pi where pi.product.productId=:productId")
    Optional<List<ProductImage>> findImagesByProductId(Integer productId);
}
