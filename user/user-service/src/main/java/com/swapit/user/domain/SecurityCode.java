package com.swapit.user.domain;

import com.swapit.user.utils.SecurityCodeType;
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
@Table(name = "security_code")
public class SecurityCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "code_type")
    @Enumerated(EnumType.STRING)
    private SecurityCodeType codeType;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = ZonedDateTime.now();
    }
}
