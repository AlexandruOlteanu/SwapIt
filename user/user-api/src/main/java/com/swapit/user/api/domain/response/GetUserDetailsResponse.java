package com.swapit.user.api.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class GetUserDetailsResponse {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String userImage;
    private ZonedDateTime joinDate;
}
