package com.swapit.apiGateway.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    void productCreation(ProductCreationRequest request);
    void sendPrivateMessage(PrivateChatMessageRequest request);
    UserDetailsResponse getUserDetails(Integer userId);
    ConversationsPreviewResponse getConversationsPreview(Integer userId);
    SpecificUsersDetailsResponse getSpecificUsersDetails(SpecificUsersDetailsRequest request);
    ConversationResponse getConversation(Integer conversationId);
    void updateBasicUserDetails(UpdateBasicUserDetailsRequest request);
    void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request);
    void addNewProductCategory(AddNewProductCategoryRequest request);
    GetProductCategoriesResponse getAllProductCategories();
}
