package com.swapit.apiGateway.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    void createProduct(ProductCreationRequest request);
    void sendPrivateMessage(PrivateChatMessageRequest request);
    GetUserDetailsResponse getUserDetails(Integer userId);
    ConversationsPreviewResponse getConversationsPreview(Integer userId);
    GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request);
    ConversationResponse getConversation(Integer conversationId);
    void updateBasicUserDetails(UpdateBasicUserDetailsRequest request);
    void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request);
    void addNewProductCategory(AddNewProductCategoryRequest request);
    GetProductCategoriesResponse getAllProductCategories();
    SearchProductsResponse searchProducts(SearchProductsRequest request);
    ProductDTO getProductById(Integer productId);
    GetCategoryTreeResponse getCategoryTree(Integer categoryId);
    SearchProductsResponse searchProductsByCategory(Integer categoryId);
}
