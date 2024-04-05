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
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.GetUserDetailsResponse;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.swapit.apiGateway.util.AuthenticatedUserPropertyType.CONTEXT_USER_ID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    private static final String OAUTH2_USER_ID = "sub";
    private static final String USER_IMAGE = "picture";
    private static final String NAME = "family_name";
    private static final String SURNAME = "given_name";
    private static final String EMAIL = "email";

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
    public void sendRegistrationCode(SendRegistrationCodeRequest request) {
        externalOperationsService.sendRegistrationCode(request);
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
    public ResponseEntity<String> getProductLikeStatus(Integer productId) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        return ResponseEntity.ok(externalOperationsService.getProductLikeStatus(userId, productId));
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
    public void  updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.updateBasicUserDetails(userId, request);
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        var attributes = authenticatedUserContextService.getUserProperties();
        Integer userId = (Integer) attributes.get(CONTEXT_USER_ID.name());
        externalOperationsService.updateProtectedUserDetails(userId, request);
    }

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        externalOperationsService.addNewProductCategory(request);
    }

    @Override
    public void manualRegistrationCodesExpire() {
        externalOperationsService.manualRegistrationCodesExpire();
    }

    @Override
    public void banUser(Integer userId, Integer banDaysDuration) {
        externalOperationsService.banUser(userId, banDaysDuration);
    }

    @Override
    public void removeUserBan(Integer userId) {
        externalOperationsService.removeUserBan(userId);
    }

    @Override
    public void manualRemoveUsersBan() {
        externalOperationsService.manualRemoveUsersBan();
    }

    @Override
    public ResponseEntity<GetProductCategoriesResponse> getAllProductCategories() {
        return ResponseEntity.ok(externalOperationsService.getAllProductCategories());
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
    public ResponseEntity<GetCategoryTreeResponse> getCategoryTree(Integer categoryId) {
        return ResponseEntity.ok(externalOperationsService.getCategoryTree(categoryId));
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.searchProductsByCategory(categoryId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

}
