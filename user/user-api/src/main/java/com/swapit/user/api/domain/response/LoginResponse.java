package com.swapit.user.api.domain.response;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class LoginResponse {

    private Integer userId;
    private String jwtToken;

}
