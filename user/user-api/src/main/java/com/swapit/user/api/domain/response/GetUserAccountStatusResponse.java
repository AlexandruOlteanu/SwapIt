package com.swapit.user.api.domain.response;

import com.swapit.user.api.util.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class GetUserAccountStatusResponse {
    private UserStatus userStatus;
    private ZonedDateTime banExpiryTime;
}
