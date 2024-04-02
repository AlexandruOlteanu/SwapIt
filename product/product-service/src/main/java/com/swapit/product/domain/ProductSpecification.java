package com.swapit.product.domain;

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
@Table(name = "product_specification")
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="specification_id")
    private Integer specificationId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="key")
    private String key;

    @Column(name="value")
    private String value;

}
