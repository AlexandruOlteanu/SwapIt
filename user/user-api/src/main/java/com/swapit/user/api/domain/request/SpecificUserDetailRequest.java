package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.UserDetailType;
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
    private UserDetailType userDetailType;
}
