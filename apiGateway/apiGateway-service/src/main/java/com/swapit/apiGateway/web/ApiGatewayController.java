package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.ApiGatewayService;
import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductSubcategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.request.UpdateProtectedUserDetailsRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    private final ExternalOperationsService externalOperationsService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(externalOperationsService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(externalOperationsService.register(request));
    }

    @Override
    public void productCreation(ProductCreationRequest request) {
        externalOperationsService.productCreation(request);
    }

    @Override
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        externalOperationsService.sendPrivateMessage(request);
    }

    @Override
    public ResponseEntity<UserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(externalOperationsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(Integer userId) {
        return ResponseEntity.ok(externalOperationsService.getConversationsPreview(userId));
    }

    @Override
    public ResponseEntity<ConversationResponse> getConversation(Integer conversationId) {
        return ResponseEntity.ok(externalOperationsService.getConversation(conversationId));
    }

    @Override
    public void  updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        externalOperationsService.updateBasicUserDetails(request);
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        externalOperationsService.updateProtectedUserDetails(request);
    }

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        externalOperationsService.addNewProductCategory(request);
    }

    @Override
    public void addNewProductSubcategory(AddNewProductSubcategoryRequest request) {
        externalOperationsService.addNewProductSubcategory(request);
    }

    @Override
    public ResponseEntity<GetProductCategoriesResponse> getAllProductCategories() {
        return ResponseEntity.ok(externalOperationsService.getAllProductCategories());
    }

}
