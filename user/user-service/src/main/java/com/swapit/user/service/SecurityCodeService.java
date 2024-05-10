package com.swapit.user.service;

import com.swapit.user.api.domain.request.SendSecurityCodeRequest;

public interface SecurityCodeService {
    void sendSecurityCode(SendSecurityCodeRequest request);
}
