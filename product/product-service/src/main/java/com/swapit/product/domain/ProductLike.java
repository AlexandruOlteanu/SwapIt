package com.swapit.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "product_like")
public class ProductLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="status")
    private String status;

    @Column(name = "last_update_action")
    private ZonedDateTime lastUpdateAction;

    @PrePersist
    public void setLastUpdateAction() {
        this.lastUpdateAction = ZonedDateTime.now();
    }
}
