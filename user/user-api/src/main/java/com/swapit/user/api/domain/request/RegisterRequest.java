package com.swapit.user.api.domain.request;


import com.swapit.user.api.util.RegisterProcessPhase;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class RegisterRequest {

    @NotNull
    private String username;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String userImage;
    private String securityCode;
    private RegisterProcessPhase processPhase;
}
