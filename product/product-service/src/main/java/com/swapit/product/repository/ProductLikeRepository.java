package com.swapit.product.repository;

import com.swapit.product.domain.ProductLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Integer> {
    Optional<ProductLike> findProductLikeByUserIdAndProductId(Integer userId, Integer productId);
    Page<ProductLike> findAllByUserIdAndStatus(Integer userId, String status, Pageable pageable);

}
