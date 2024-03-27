package com.swapit.apiGateway.api;

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
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public interface ApiGatewayService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/apiGateway/";
    String ADMIN_ACTION = "adminAction/";
    String LOGIN = "auth/login";
    String REGISTER = "auth/register";
    String OAUTH2_LOGIN = "auth/oauth2login";
    String CREATE_PRODUCT = "createProduct";
    String UPDATE_PRODUCT = "updateProduct";
    String SEND_PRIVATE_MESSAGE = "sendPrivateMessage";
    String USER_DETAILS = "getUserDetails";
    String GET_CONVERSATIONS_PREVIEW = "getConversationsPreview";
    String GET_CONVERSATION = "getConversation";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";
    String UPDATE_PROTECTED_USER_DETAILS = "updateProtectedUserDetails";
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";
    String GET_ALL_PRODUCT_CATEGORIES = "getAllProductCategories";
    String SEARCH_PRODUCTS = "searchProducts";
    String GET_PRODUCT_BY_ID = "getProductById";
    String GET_CATEGORY_TREE = "getCategoryTree";
    String SEARCH_PRODUCTS_BY_CATEGORY = "searchProductsByCategory";
    String CHANGE_PRODUCT_LIKE_STATUS = "changeProductLikeStatus";
    String GET_PRODUCT_LIKE_STATUS = "getProductLikeStatus";
    String GET_PRODUCTS_BY_USER = "getProductsByUser";
    String GET_LIKED_PRODUCTS_BY_USER = "getLikedProductsByUser";


    @PostMapping(value = BASE_URL + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @GetMapping(value = BASE_URL + OAUTH2_LOGIN, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Oauth2Response> oauth2login(OAuth2AuthenticationToken auth2AuthenticationToken);

    @PutMapping(value = BASE_URL + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void createProduct(@Valid @RequestBody CreateProductRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void updateProduct(@Valid @RequestBody UpdateProductRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getProductsByUser(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_LIKED_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getLikedProductsByUser(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = BASE_URL + CHANGE_PRODUCT_LIKE_STATUS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void changeProductLikeStatus(@Valid @RequestBody ChangeProductLikeStatusRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCT_LIKE_STATUS)
    ResponseEntity<String> getProductLikeStatus(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "productId") Integer productId);

    @PostMapping(value = BASE_URL + SEND_PRIVATE_MESSAGE, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@Valid @RequestBody PrivateChatMessageRequest request);

    @GetMapping(value = BASE_URL + USER_DETAILS)
    ResponseEntity<GetUserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_CONVERSATIONS_PREVIEW)
    ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_CONVERSATION)
    ResponseEntity<ConversationResponse> getConversation(@RequestParam(value = "conversationId") Integer conversationId);

    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateBasicUserDetails(@Valid @RequestBody UpdateBasicUserDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PROTECTED_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateProtectedUserDetails(@Valid @RequestBody UpdateProtectedUserDetailsRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCT_CATEGORIES)
    ResponseEntity<GetProductCategoriesResponse> getAllProductCategories();

    @PostMapping(value = BASE_URL + SEARCH_PRODUCTS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<SearchProductsResponse> searchProducts(@Valid @RequestBody SearchProductsRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCT_BY_ID)
    ResponseEntity<ProductDTO> getProductById(@RequestParam(value = "productId") Integer productId);

    @GetMapping(value = BASE_URL + GET_CATEGORY_TREE, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetCategoryTreeResponse> getCategoryTree(@RequestParam(value = "categoryId") Integer categoryId);

    @GetMapping(value = BASE_URL + SEARCH_PRODUCTS_BY_CATEGORY)
    ResponseEntity<SearchProductsResponse> searchProductsByCategory(@RequestParam(value = "categoryId") Integer categoryId);

    // ADMIN API
    @PutMapping(value = BASE_URL + ADMIN_ACTION + ADD_NEW_PRODUCT_CATEGORY)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);


}

