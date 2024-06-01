package com.swapit.product.repository;

import com.swapit.product.domain.Product;
import com.swapit.product.projections.ProductProjection;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p.productId as productId, p.title as title, p.price as price, p.popularity as popularity FROM Product p WHERE p.categoryId IN :categoriesIds ORDER BY RANDOM()")
    Page<ProductProjection> findAllRandomByCategoryIdIn(List<Integer> categoriesIds, Pageable pageable);

    @Query("SELECT p.productId as productId, p.title as title, p.price as price, p.popularity as popularity FROM Product p ORDER BY RANDOM()")
    @NotNull Page<ProductProjection> findAllRandom(Pageable pageable);

    @Query("SELECT p.productId as productId, p.title as title, p.price as price, p.popularity as popularity FROM Product p WHERE p.userId = :userId")
    Page<ProductProjection> findAllByUserId(Integer userId, Pageable pageable);

    @Query("SELECT p.productId as productId, p.title as title, p.price as price, p.popularity as popularity FROM Product p WHERE p.categoryId IN :categoriesIds")
    Page<ProductProjection> findAllByCategoryIdIn(List<Integer> categoriesIds, Pageable pageable);

    Optional<List<Product>> findAllByProductIdIn(List<Integer> productIds);

    @Query(value = "SELECT p.productId as productId, p.title as title, p.price as price, p.popularity as popularity FROM Product p")
    @NotNull Page<ProductProjection> findAllProd(@NotNull Pageable pageable);

}
