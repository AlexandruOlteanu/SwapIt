package com.swapit.user.api.domain.response;

import com.swapit.user.api.util.UserDetailType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class SpecificUsersDetailsResponse {
    Map<Integer, Map<UserDetailType, Object>> requestedUserDetails;
}
