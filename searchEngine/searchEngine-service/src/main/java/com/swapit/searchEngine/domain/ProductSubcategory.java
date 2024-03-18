package com.swapit.searchEngine.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "product_subcategory")
public class ProductSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_subcategory_id")
    private Integer productSubcategoryId;

    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
}
