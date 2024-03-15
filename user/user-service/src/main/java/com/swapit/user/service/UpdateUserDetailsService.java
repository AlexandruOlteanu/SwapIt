package com.swapit.user.service;

import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.response.UpdateBasicUserDetailsResponse;

public interface UpdateUserDetailsService {
    UpdateBasicUserDetailsResponse updateBasicUserDetails(UpdateBasicUserDetailsRequest request);
}
