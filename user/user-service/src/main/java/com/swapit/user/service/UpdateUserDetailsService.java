package com.swapit.user.service;

import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.request.UpdateProtectedUserDetailsRequest;

public interface UpdateUserDetailsService {
    void updateBasicUserDetails(UpdateBasicUserDetailsRequest request);
    void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request);
}
