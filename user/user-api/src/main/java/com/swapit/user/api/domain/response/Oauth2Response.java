package com.swapit.user.api.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class Oauth2Response {
    private Integer userId;
    private Boolean registeredNow;
}
