package com.swapit.user.service;

public interface ScheduledOperationsService {
    void securityCodesExpire();
    void removeUsersBan();
}
