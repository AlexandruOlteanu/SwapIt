package com.swapit.product.repository;


import com.swapit.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findAllByCategory(String category);
    @Query("select p.productId from Product p where p.userId = :userId")
    Optional<List<Integer>> findAllProductIdsOfUser(Integer userId);
    Optional<List<Product>> findAllByUserId(Integer userId);
    Optional<List<Product>> findAllBySubcategory(String subcategory);

}
