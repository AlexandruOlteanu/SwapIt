package com.swapit.apiGateway.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SpecificUsersDetailsRequest;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.response.*;

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    void productCreation(ProductCreationRequest request);
    void sendPrivateMessage(PrivateChatMessageRequest request);
    UserDetailsResponse getUserDetails(Integer userId);
    ConversationsPreviewResponse getConversationsPreview(Integer userId);
    SpecificUsersDetailsResponse getSpecificUserDetails(SpecificUsersDetailsRequest request);
    ConversationResponse getConversation(Integer conversationId);
    UpdateBasicUserDetailsResponse updateBasicUserDetails(UpdateBasicUserDetailsRequest request);

}
