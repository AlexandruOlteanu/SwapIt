package com.swapit.bff.service.impl;

import com.swapit.bff.service.UrlGeneratorService;
import org.bouncycastle.asn1.esf.SPuri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.swapit.bff.api.service.BffService.PRODUCT_CREATION;
import static com.swapit.bff.service.impl.UrlGeneratorServiceImpl.UrlIdentifier.USER_LOGIN;
import static com.swapit.bff.service.impl.UrlGeneratorServiceImpl.UrlIdentifier.USER_REGISTER;

@Service
public class UrlGeneratorServiceImpl implements UrlGeneratorService {

    public enum UrlIdentifier {
        USER_LOGIN, USER_REGISTER, PRODUCT_CREATION, SEND_PRIVATE_MESSAGE
    }

    @Value("${user.login.route}")
    private String userLoginUri;
    @Value("${user.register.route}")
    private String userRegisterUri;
    @Value("${product.create.route}")
    private String productCreationUri;
    @Value("${chat.send.private.message.route}")
    private String sendPrivateMessageUri;

    @Override
    public String getServiceURL(UrlIdentifier api) {
        return switch (api) {
            case USER_LOGIN -> userLoginUri;
            case USER_REGISTER -> userRegisterUri;
            case PRODUCT_CREATION -> productCreationUri;
            case SEND_PRIVATE_MESSAGE -> sendPrivateMessageUri;
        };
    }
}
