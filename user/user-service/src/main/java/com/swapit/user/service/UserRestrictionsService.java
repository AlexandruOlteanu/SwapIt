package com.swapit.user.service;

import com.swapit.user.api.domain.response.GetUserAccountStatusResponse;

public interface UserRestrictionsService {
    void banUser(Integer userId, Integer banDaysDuration);
    GetUserAccountStatusResponse getUserAccountStatus(Integer userId);
    void removeUserBan(Integer userId);
}
