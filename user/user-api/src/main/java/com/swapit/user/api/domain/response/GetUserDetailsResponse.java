package com.swapit.user.api.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class GetUserDetailsResponse {
    private Integer userId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String userImage;
    private ZonedDateTime joinDate;
    private String userRole;
    private String address;
    private String phoneNumber;
    private String country;
    private String stateRegion;
}
