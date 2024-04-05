package com.swapit.searchEngine.repository;

import com.swapit.searchEngine.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    Optional<ProductCategory> findFirstByValue(String value);

    @Query("select pc from ProductCategory pc where pc.parent is null")
    Optional<List<ProductCategory>> findAllRootCategories();
}
