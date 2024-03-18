package com.swapit.searchEngine.repository;

import com.swapit.searchEngine.domain.ProductCategory;
import com.swapit.searchEngine.domain.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, Integer> {
    Optional<ProductSubcategory> findFirstByProductCategory(ProductCategory productCategory);
}
