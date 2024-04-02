package com.swapit.user.service;

import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.request.UpdateProtectedUserDetailsRequest;

public interface UpdateUserDetailsService {
    void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request);
    void updateProtectedUserDetails(Integer userId, UpdateProtectedUserDetailsRequest request);
}
