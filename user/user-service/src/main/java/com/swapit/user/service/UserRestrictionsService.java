package com.swapit.user.service;

public interface UserRestrictionsService {
    void banUser(Integer userId, Integer banDaysDuration);
    void removeUserBan(Integer userId);
}
