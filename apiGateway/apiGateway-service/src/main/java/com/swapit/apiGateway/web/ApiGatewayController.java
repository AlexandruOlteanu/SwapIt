package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.ApiGatewayService;
import com.swapit.apiGateway.service.AuthenticatedUserContextService;
import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.ChangeProductLikeStatusRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.GetProductsLikeStatusRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.api.domain.response.GetProductsLikeStatusResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.GetProductCategoryIdRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;
import com.swapit.user.api.util.UserBasicDetailType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


import static com.swapit.apiGateway.util.AuthenticatedUserPropertyType.CONTEXT_USER_ID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    private final ExternalOperationsService externalOperationsService;
    private final AuthenticatedUserContextService authenticatedUserContextService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(externalOperationsService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(externalOperationsService.register(request));
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void forgottenPasswordReset(ForgottenPasswordResetRequest request) {
        externalOperationsService.forgottenPasswordReset(request);
    }

    @Override
    public ResponseEntity<Integer> createProduct(CreateProductRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.createProduct(userId, request));
    }

    @Override
    public void updateProduct(UpdateProductRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.updateProduct(userId, request);
    }

    @Override
    public void deleteProduct(Integer productId) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.deleteProduct(userId, productId);
    }

    @Override
    public ResponseEntity<GetProductsResponse> getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.getProductsByUser(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.getRecommendedProducts(chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<GetProductsResponse> getLikedProductsByUser(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getLikedProductsByUser(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public void changeProductLikeStatus(ChangeProductLikeStatusRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.changeProductLikeStatus(userId, request);
    }

    @Override
    public ResponseEntity<GetProductsLikeStatusResponse> getProductsLikeStatus(GetProductsLikeStatusRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getProductsLikeStatus(userId, request));
    }

    @Override
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.sendPrivateMessage(userId, request);
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(externalOperationsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetailsByUsername(String username) {
        return ResponseEntity.ok(externalOperationsService.getUserDetailsByUsername(username));
    }

    @Override
    public ResponseEntity<GetUserAccountStatusResponse> getUserAccountStatus(Integer userId) {
        return ResponseEntity.ok(externalOperationsService.getUserAccountStatus(userId));
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getAuthenticatedUserDetails() {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getConversationsPreview(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public ResponseEntity<ConversationResponse> getConversation(Integer conversationId) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getConversation(userId, conversationId));
    }

    @Override
    public ResponseEntity<Integer> getProductCategoryId(GetProductCategoryIdRequest request) {
        return ResponseEntity.ok(externalOperationsService.getProductCategoryId(request));
    }

    @Override
    public void  updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.updateBasicUserDetails(userId, request);
    }

    @Override
    public void passwordReset(PasswordResetRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.passwordReset(userId, request);
    }

    @Override
    public void emailReset(EmailResetRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.emailReset(userId, request);
    }

    @Override
    public void usernameReset(UsernameResetRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.usernameReset(userId, request);
    }

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        externalOperationsService.addNewProductCategory(request);
    }

    @Override
    public void manualSecurityCodesExpire() {
        externalOperationsService.manualSecurityCodesExpire();
    }

    @Override
    public void banUser(Integer userId, Integer banDaysDuration) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer adminUserId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.banUser(adminUserId, userId, banDaysDuration);
    }

    @Override
    public void removeUserBan(Integer userId) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer adminUserId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.removeUserBan(adminUserId, userId);
    }

    @Override
    public ResponseEntity<GetUserActionsResponse> getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.getUserActions(chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public void manualRemoveUsersBan() {
        externalOperationsService.manualRemoveUsersBan();
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request) {
        return ResponseEntity.ok(externalOperationsService.searchProducts(chunkNumber, nrElementsPerChunk, sortCriteria, request));
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Integer productId) {
        return ResponseEntity.ok(externalOperationsService.getProductById(productId));
    }

    @Override
    public void deleteProductAdmin(Integer productId) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer adminUserId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.deleteProductAdmin(adminUserId, productId);
    }

    @Override
    public ResponseEntity<GetCategoryTreeResponse> getCategoryTree(Integer categoryId) {
        return ResponseEntity.ok(externalOperationsService.getCategoryTree(categoryId));
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.searchProductsByCategory(categoryId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

}
