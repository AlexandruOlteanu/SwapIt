package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.UserBasicDetailType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

@Data
@Jacksonized
@Builder
public class GetSpecificUsersDetailsRequest {
    Map<Integer, List<UserBasicDetailType>> requestedUserDetails;
}
