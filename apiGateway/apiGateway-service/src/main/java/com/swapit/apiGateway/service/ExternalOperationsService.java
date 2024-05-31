package com.swapit.apiGateway.service;

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

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    Oauth2Response oauth2login(Oauth2Request request);
    RegisterResponse register(RegisterRequest request);
    Integer createProduct(Integer userId, CreateProductRequest request);
    void updateProduct(Integer userId, UpdateProductRequest request);
    void deleteProduct(Integer userId, Integer productId);
    GetProductsResponse getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    GetProductsResponse getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    void sendPrivateMessage(Integer userId, PrivateChatMessageRequest request);
    GetUserDetailsResponse getUserDetails(Integer userId);
    GetUserDetailsResponse getUserDetailsByUsername(String username);
    ConversationsPreviewResponse getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request);
    ConversationResponse getConversation(Integer userId, Integer conversationId);
    void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request);
    void addNewProductCategory(AddNewProductCategoryRequest request);
    Integer getProductCategoryId(GetProductCategoryIdRequest request);
    SearchProductsResponse searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request);
    ProductDTO getProductById(Integer productId);
    GetCategoryTreeResponse getCategoryTree(Integer categoryId);
    SearchProductsResponse searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request);
    GetProductsLikeStatusResponse getProductsLikeStatus(Integer userId, GetProductsLikeStatusRequest request);
    GetProductsResponse getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    void manualSecurityCodesExpire();
    void banUser(Integer adminUserId, Integer userId, Integer banDaysDuration);
    GetUserAccountStatusResponse getUserAccountStatus(Integer userId);
    void removeUserBan(Integer adminUserId, Integer userId);
    void manualRemoveUsersBan();
    void forgottenPasswordReset(ForgottenPasswordResetRequest request);
    void passwordReset(Integer userId, PasswordResetRequest request);
    void emailReset(Integer userId, EmailResetRequest request);
    void usernameReset(Integer userId, UsernameResetRequest request);
    void deleteProductAdmin(Integer adminUserId, Integer productId);
    void postUserAction(PostUserActionRequest request);
    GetUserActionsResponse getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
