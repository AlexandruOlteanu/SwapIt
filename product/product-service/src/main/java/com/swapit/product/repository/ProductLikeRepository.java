package com.swapit.product.repository;

import com.swapit.product.domain.ProductLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Integer> {
    Optional<ProductLike> findProductLikeByUserIdAndProductId(Integer userId, Integer productId);
    Optional<List<ProductLike>> findProductLikeByUserIdAndProductIdIn(Integer userId, List<Integer> productIds);
    Page<ProductLike> findAllByUserIdAndStatus(Integer userId, String status, Pageable pageable);
    void deleteAllByProductId(Integer productId);
}
