package com.swapit.user.service;

public interface InternalRequestService {
    Integer getUserIdByUsernameOrEmail(String username, String email) throws Exception;
}
