package com.swapit.product.repository;

import com.swapit.product.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Integer> {
    Optional<ProductLike> findProductLikeByUserIdAndProductId(Integer userId, Integer productId);
    Optional<List<ProductLike>> findAllByUserId(Integer userId);

}
