package com.swapit.user.api.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class Oauth2Request {
    private String oauth2UserId;
    private String picture;
    private String name;
    private String surname;
    private String email;
}
