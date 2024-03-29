package com.swapit.product.repository;


import com.swapit.product.domain.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.userId =:userId order by random()")
    Page<Product> findAllRandomByUserId(Integer userId, Pageable pageable);
    Page<Product> findAllByUserId(Integer userId, Pageable pageable);
    Optional<List<Product>> findAllByProductIdIn(List<Integer> productIds);
    @Query("select p from Product p where p.categoryId in :categoriesIds order by random()")
    Page<Product> findAllRandomByCategoryIdIn(List<Integer> categoriesIds, Pageable pageable);
    Page<Product> findAllByCategoryIdIn(List<Integer> categoriesIds, Pageable pageable);
    @NotNull Page<Product> findAll(@NotNull Pageable pageable);
    @Query("select p from Product p order by random()")
    @NotNull Page<Product> findAllRandom(Pageable pageable);

}
