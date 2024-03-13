package com.swapit.apiGateway.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SpecificUserDetailRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import org.springframework.http.ResponseEntity;

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    void productCreation(ProductCreationRequest request);
    void sendPrivateMessage(PrivateChatMessageRequest request);
    UserDetailsResponse getUserDetails(Integer userId);
    ConversationsPreviewResponse getConversationsPreview(Integer userId);
    <T> T getSpecificUserDetail(SpecificUserDetailRequest request, Class<T> objType);
    ConversationResponse getConversation(Integer conversationId);

}
