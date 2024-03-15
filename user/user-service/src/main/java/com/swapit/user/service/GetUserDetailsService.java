package com.swapit.user.service;

import com.swapit.user.api.domain.request.SpecificUserDetailRequest;
import com.swapit.user.api.domain.response.UserDetailsResponse;

public interface GetUserDetailsService {
    UserDetailsResponse getCompleteUserDetails(Integer userId);
    Object getSpecificUserDetail(SpecificUserDetailRequest request);

}
