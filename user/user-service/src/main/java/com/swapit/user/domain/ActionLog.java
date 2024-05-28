package com.swapit.user.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.swapit.user.api.util.ActionType;
import com.swapit.user.converter.JsonNodeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Convert(attributeName = "action_details", converter = JsonNodeConverter.class)
@Table(name = "action_log")
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "action_details")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode actionDetails;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = ZonedDateTime.now();
    }
}
