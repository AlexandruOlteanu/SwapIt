package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.SecurityCodeType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class SendSecurityCodeRequest {
    @NotNull
    private String email;
    @NotNull
    private SecurityCodeType securityCodeType;
}
