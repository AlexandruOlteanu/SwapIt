package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.UserProtectedDetailType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class UpdateProtectedUserDetailsRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private String password;
    @NotNull
    Map<UserProtectedDetailType, String> userDetails;
}
