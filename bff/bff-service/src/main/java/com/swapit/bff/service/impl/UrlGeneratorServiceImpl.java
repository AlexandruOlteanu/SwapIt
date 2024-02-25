package com.swapit.bff.service.impl;

import com.swapit.bff.service.UrlGeneratorService;
import org.bouncycastle.asn1.esf.SPuri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlGeneratorServiceImpl implements UrlGeneratorService {

    public static final String USER_LOGIN = "userLogin";
    public static final String USER_REGISTER = "userRegister";


    @Value("${user.login.route}")
    private String userLoginUri;
    @Value("${user.register.route}")
    private String userRegisterUri;

    @Override
    public String getServiceURL(String api) {
        return switch (api) {
            case USER_LOGIN -> userLoginUri;
            case USER_REGISTER -> userRegisterUri;
            default -> null;
        };
    }
}
