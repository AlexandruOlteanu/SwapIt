package com.swapit.product.repository;


import com.swapit.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findAllByUserId(Integer userId);
    Optional<List<Product>> findAllByProductIdIn(List<Integer> productIds);
}
