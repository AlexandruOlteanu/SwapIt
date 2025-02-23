package com.swapit.user.api.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PasswordResetRequest {
    @NotNull
    private String password;
    @NotNull
    private String newPassword;
}
