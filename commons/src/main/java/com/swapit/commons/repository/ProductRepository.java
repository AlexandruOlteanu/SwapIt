package com.swapit.commons.repository;

import com.swapit.commons.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductByProductId(Integer productId);
    Optional<List<Product>> findAllByCategory(String category);
    Optional<List<Product>> findAllBySubcategory(String subcategory);

}
