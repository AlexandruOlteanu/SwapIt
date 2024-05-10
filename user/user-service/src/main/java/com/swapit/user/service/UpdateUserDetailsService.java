package com.swapit.user.service;

import com.swapit.user.api.domain.request.*;

public interface UpdateUserDetailsService {
    void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request);
    void passwordReset(Integer userId, PasswordResetRequest request);
    void emailReset(Integer userId, EmailResetRequest request);
    void usernameReset(Integer userId, UsernameResetRequest request);
}
