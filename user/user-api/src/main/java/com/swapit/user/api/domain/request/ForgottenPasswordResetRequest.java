package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.ForgottenPasswordResetProcessPhase;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ForgottenPasswordResetRequest {
    @NotNull
    private String email;
    private String newPassword;
    private String securityCode;
    private ForgottenPasswordResetProcessPhase processPhase;
}
