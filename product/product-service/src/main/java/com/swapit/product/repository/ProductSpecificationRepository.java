package com.swapit.product.repository;


import com.swapit.product.domain.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Integer> {

}
