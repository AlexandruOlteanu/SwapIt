package com.swapit.commons.service.impl;

import com.swapit.commons.service.UrlGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlGeneratorServiceImpl implements UrlGeneratorService {

    public enum UrlIdentifier {
        USER_LOGIN, USER_REGISTER, PRODUCT_CREATION, SEND_PRIVATE_MESSAGE, GET_USER_ID_BY_USERNAME_EMAIL
    }

    // BFF URI
    @Value("${user.login.route}")
    private String userLoginUri;
    @Value("${user.register.route}")
    private String userRegisterUri;
    @Value("${product.create.route}")
    private String productCreationUri;
    @Value("${chat.send.private.message.route}")
    private String sendPrivateMessageUri;

    // USER URI
    @Value("${user.getUserIdByUsername.route}")
    private String getUserIdByUsernameOrEmailUri;

    @Override
    public String getServiceURL(UrlIdentifier api) {
        return switch (api) {
            case USER_LOGIN -> userLoginUri;
            case USER_REGISTER -> userRegisterUri;
            case PRODUCT_CREATION -> productCreationUri;
            case SEND_PRIVATE_MESSAGE -> sendPrivateMessageUri;
            case GET_USER_ID_BY_USERNAME_EMAIL -> getUserIdByUsernameOrEmailUri;
        };
    }
}
