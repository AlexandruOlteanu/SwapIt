package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.ApiGatewayService;
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
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.api.domain.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(externalOperationsService.login(request));
    }

    @Override
    public ResponseEntity<Oauth2Response> oauth2login(OAuth2AuthenticationToken auth2AuthenticationToken) {
        Map<String, Object> attributes = auth2AuthenticationToken.getPrincipal().getAttributes();
        Oauth2Request oauth2Request = Oauth2Request.builder()
                                                .oauth2UserId((String) attributes.get(OAUTH2_USER_ID))
                                                .userImage((String) attributes.get(USER_IMAGE))
                                                .name((String) attributes.get(NAME))
                                                .surname((String) attributes.get(SURNAME))
                                                .email((String) attributes.get(EMAIL))
                                        .build();
        return ResponseEntity.ok(externalOperationsService.oauth2login(oauth2Request));
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
        return ResponseEntity.ok(externalOperationsService.createProduct(request));
    }

    @Override
    public void updateProduct(UpdateProductRequest request) {
        externalOperationsService.updateProduct(request);
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
    public ResponseEntity<GetProductsResponse> getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.getLikedProductsByUser(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public void changeProductLikeStatus(ChangeProductLikeStatusRequest request) {
        externalOperationsService.changeProductLikeStatus(request);
    }

    @Override
    public ResponseEntity<String> getProductLikeStatus(Integer userId, Integer productId) {
        return ResponseEntity.ok(externalOperationsService.getProductLikeStatus(userId, productId));
    }

    @Override
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        externalOperationsService.sendPrivateMessage(request);
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(externalOperationsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(externalOperationsService.getConversationsPreview(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
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
    public ResponseEntity<GetProductCategoriesResponse> getAllProductCategories() {
        return ResponseEntity.ok(externalOperationsService.getAllProductCategories());
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProducts(SearchProductsRequest request) {
        return ResponseEntity.ok(externalOperationsService.searchProducts(request));
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
