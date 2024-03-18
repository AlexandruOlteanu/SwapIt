package com.swapit.searchEngine.repository;

import com.swapit.searchEngine.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    Optional<ProductCategory> findFirstByValue(String value);
}
