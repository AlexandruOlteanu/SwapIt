package com.swapit.user.service;

import com.swapit.user.api.domain.request.SpecificUsersDetailsRequest;
import com.swapit.user.api.domain.response.SpecificUsersDetailsResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;

public interface GetUserDetailsService {
    UserDetailsResponse getCompleteUserDetails(Integer userId);
    SpecificUsersDetailsResponse getSpecificUsersDetails(SpecificUsersDetailsRequest request);

}
