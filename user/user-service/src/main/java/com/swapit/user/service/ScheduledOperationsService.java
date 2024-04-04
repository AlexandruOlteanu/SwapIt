package com.swapit.user.service;

public interface ScheduledOperationsService {
    void registrationCodesExpire();
    void removeUsersBan();
}
