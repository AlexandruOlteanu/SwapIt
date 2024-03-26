package com.swapit.user.api.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class UserProductDTO {
    private Integer productId;
    private ZonedDateTime creationDate;
    private String title;
    private String description;
    private Integer categoryId;
    private Integer popularity;
}
