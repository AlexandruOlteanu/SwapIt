package com.swapit.product.repository;


import com.swapit.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductByProductId(Integer productId);
    Optional<List<Product>> findAllByCategory(String category);
    Optional<List<Product>> findAllByUserId(Integer userId);
    Optional<List<Product>> findAllBySubcategory(String subcategory);

}
