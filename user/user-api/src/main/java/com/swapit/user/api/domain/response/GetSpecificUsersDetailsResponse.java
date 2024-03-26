package com.swapit.user.api.domain.response;

import com.swapit.user.api.util.UserBasicDetailType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class GetSpecificUsersDetailsResponse {
    Map<Integer, Map<UserBasicDetailType, Object>> requestedUserDetails;
}
