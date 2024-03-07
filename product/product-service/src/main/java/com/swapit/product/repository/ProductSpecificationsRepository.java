package com.swapit.product.repository;


import com.swapit.product.domain.ProductSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationsRepository extends JpaRepository<ProductSpecifications, Integer> {

}
