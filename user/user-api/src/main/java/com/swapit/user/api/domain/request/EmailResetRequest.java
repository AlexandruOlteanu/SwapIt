package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.EmailResetProcessPhase;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class EmailResetRequest {
    @NotNull
    private String newEmail;
    @NotNull
    private String password;
    private String securityCode;
    private final EmailResetProcessPhase processPhase;
}
