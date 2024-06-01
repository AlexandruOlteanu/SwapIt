package com.swapit.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "product", indexes = {
        @Index(name = "idx_product_user_id", columnList = "user_id"),
        @Index(name = "idx_product_creation_date", columnList = "created_at"),
        @Index(name = "idx_product_title", columnList = "title"),
        @Index(name = "idx_product_price", columnList = "price"),
        @Index(name = "idx_product_category_id", columnList = "category_id"),
        @Index(name = "idx_product_popularity", columnList = "popularity")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name = "created_at")
    private ZonedDateTime creationDate;

    @PrePersist
    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "popularity")
    private Integer popularity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductSpecification> productSpecifications;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductImage> productImages;

}
