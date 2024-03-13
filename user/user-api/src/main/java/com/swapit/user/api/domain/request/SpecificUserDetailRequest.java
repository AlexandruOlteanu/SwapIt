package com.swapit.user.api.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class SpecificUserDetailRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private String actionType;
}
