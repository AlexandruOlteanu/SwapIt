package com.swapit.user.service;

import com.swapit.user.api.domain.request.GetSpecificUsersDetailsRequest;
import com.swapit.user.api.domain.response.GetSpecificUsersDetailsResponse;
import com.swapit.user.api.domain.response.GetUserDetailsResponse;

public interface GetUserDetailsService {
    GetUserDetailsResponse getUserDetails(Integer userId);
    GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request);
    GetUserDetailsResponse getUserDetailsByUsername(String username);
}
