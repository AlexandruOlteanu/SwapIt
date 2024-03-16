package com.swapit.commons.urlGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlGeneratorServiceImpl implements UrlGeneratorService {

    public enum UrlIdentifier {
        USER_LOGIN, USER_REGISTER, PRODUCT_CREATION, SEND_PRIVATE_MESSAGE, GET_USER_DETAILS,
        GET_CONVERSATIONS_PREVIEW, SPECIFIC_USERS_DETAILS, GET_CONVERSATION, UPDATE_BASIC_USER_DETAILS,
        UPDATE_ADVANCED_USER_DETAILS
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
    @Value("${user.getUserDetails.route}")
    private String getUserDetailsUri;
    @Value("${chat.getConversationsPreview.route}")
    private String getConversationsPreviewUri;
    @Value("${user.getSpecificUsersDetails.route}")
    private String getSpecificUsersDetailsUri;
    @Value("${chat.getConversation.route}")
    private String getConversationUri;
    @Value("${user.updateBasicUserDetails.route}")
    private String updateBasicUserDetailsUri;
    @Value("${user.updateAdvancedUserDetails.route}")
    private String updateAdvancedUserDetailsUri;
    @Override
    public String getServiceURL(UrlIdentifier api) {
        return switch (api) {
            case USER_LOGIN -> userLoginUri;
            case USER_REGISTER -> userRegisterUri;
            case PRODUCT_CREATION -> productCreationUri;
            case SEND_PRIVATE_MESSAGE -> sendPrivateMessageUri;
            case GET_USER_DETAILS -> getUserDetailsUri;
            case GET_CONVERSATIONS_PREVIEW -> getConversationsPreviewUri;
            case SPECIFIC_USERS_DETAILS -> getSpecificUsersDetailsUri;
            case GET_CONVERSATION -> getConversationUri;
            case UPDATE_BASIC_USER_DETAILS -> updateBasicUserDetailsUri;
            case UPDATE_ADVANCED_USER_DETAILS -> updateAdvancedUserDetailsUri;
        };
    }
}
