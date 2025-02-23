package com.swapit.apiGateway.api;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.dto.ProductDTO;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

import static com.swapit.commons.utils.Constants.IntegerMaxValueAsString;


@RestController
@Validated
public interface ApiGatewayService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/apiGateway/";
    String PUBLIC_ACCESS = "publicAccess/";
    String ADMIN_ACTION = "adminAction/";
    String BEARER_AUTH = "bearerAuth";

    // USER URI

    String LOGIN = "auth/login";
    String REGISTER = "auth/register";
    String LOGOUT = "auth/logout";
    String GET_USER_DETAILS = "getUserDetails";
    String GET_USER_DETAILS_BY_USERNAME = "getUserDetailsByUsername";
    String GET_USER_ACCOUNT_STATUS = "getUserAccountStatus";
    String GET_AUTHENTICATED_USER_DETAILS = "getAuthenticatedUserDetails";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";
    String FORGOTTEN_PASSWORD_RESET = "auth/forgottenPasswordReset";
    String PASSWORD_RESET = "passwordReset";
    String USERNAME_RESET = "usernameReset";
    String EMAIL_RESET = "emailReset";

    // USER ADMIN URI
    String BAN_USER = "banUser";
    String REMOVE_USER_BAN = "removeUserBan";
    String GET_USER_ACTIONS = "getUserActions";
    String MANUAL_REMOVE_USERS_BAN = "manualRemoveUsersBan";
    String MANUAL_SECURITY_CODES_EXPIRE = "manualSecurityCodesExpire";

    // PRODUCT URI
    String CREATE_PRODUCT = "createProduct";
    String UPDATE_PRODUCT = "updateProduct";
    String GET_PRODUCT_BY_ID = "getProductById";
    String DELETE_PRODUCT = "deleteProduct";
    String CHANGE_PRODUCT_LIKE_STATUS = "changeProductLikeStatus";
    String GET_PRODUCTS_LIKE_STATUS = "getProductsLikeStatus";
    String GET_PRODUCTS_BY_USER = "getProductsByUser";
    String GET_LIKED_PRODUCTS_BY_USER = "getLikedProductsByUser";
    String GET_RECOMMENDED_PRODUCTS = "getRecommendedProducts";

    // CHAT URI
    String SEND_PRIVATE_MESSAGE = "sendPrivateMessage";
    String GET_CONVERSATIONS_PREVIEW = "getConversationsPreview";
    String GET_CONVERSATION = "getConversation";

    // SEARCH ENGINE URI
    String GET_PRODUCT_CATEGORY_ID = "getProductCategoryId";
    String SEARCH_PRODUCTS = "searchProducts";

    // SEARCH ENGINE ADMIN URI
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";

    String GET_CATEGORY_TREE = "getCategoryTree";
    String SEARCH_PRODUCTS_BY_CATEGORY = "searchProductsByCategory";


    // USER API

    @PostMapping(value = BASE_URL + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PutMapping(value = BASE_URL + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @PostMapping(value = BASE_URL + LOGOUT)
    void logout();

    @PutMapping(value = BASE_URL + FORGOTTEN_PASSWORD_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void forgottenPasswordReset(@Valid @RequestBody ForgottenPasswordResetRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + PASSWORD_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void passwordReset(@Valid @RequestBody PasswordResetRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + EMAIL_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void emailReset(@Valid @RequestBody EmailResetRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + USERNAME_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void usernameReset(@Valid @RequestBody UsernameResetRequest request);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_USER_DETAILS)
    ResponseEntity<GetUserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_USER_DETAILS_BY_USERNAME)
    ResponseEntity<GetUserDetailsResponse> getUserDetailsByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_USER_ACCOUNT_STATUS)
    ResponseEntity<GetUserAccountStatusResponse> getUserAccountStatus(@RequestParam(value = "userId") Integer userId);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @GetMapping(value = BASE_URL + GET_AUTHENTICATED_USER_DETAILS)
    ResponseEntity<GetUserDetailsResponse> getAuthenticatedUserDetails();

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateBasicUserDetails(@Valid @RequestBody UpdateBasicUserDetailsRequest request);


    // USER ADMIN API

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @DeleteMapping(value = BASE_URL + ADMIN_ACTION + MANUAL_SECURITY_CODES_EXPIRE)
    void manualSecurityCodesExpire();

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PostMapping(value = BASE_URL + ADMIN_ACTION + BAN_USER)
    void banUser(@RequestParam("userId") Integer userId, @RequestParam(value = "banDaysDuration", required = false) Integer banDaysDuration);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @DeleteMapping(value = BASE_URL + ADMIN_ACTION + REMOVE_USER_BAN)
    void removeUserBan(@RequestParam("userId") Integer userId);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @GetMapping(value = BASE_URL + ADMIN_ACTION + GET_USER_ACTIONS, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetUserActionsResponse> getUserActions(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                          @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                          @RequestParam(value = "sortCriteria", required = false) String sortCriteria);


    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @DeleteMapping(value = BASE_URL + ADMIN_ACTION + MANUAL_REMOVE_USERS_BAN)
    void manualRemoveUsersBan();


    // PRODUCT API

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Integer> createProduct(@Valid @RequestBody CreateProductRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + UPDATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void updateProduct(@Valid @RequestBody UpdateProductRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @DeleteMapping(value = BASE_URL + DELETE_PRODUCT)
    void deleteProduct(@RequestParam(value = "productId") Integer productId);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getProductsByUser(@RequestParam(value = "userId") Integer userId,
                                                          @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                          @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                          @RequestParam(value = "sortCriteria", required = false) String sortCriteria);


    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_RECOMMENDED_PRODUCTS)
    ResponseEntity<GetProductsResponse> getRecommendedProducts(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                               @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                               @RequestParam(value = "sortCriteria", required = false) String sortCriteria);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @GetMapping(value = BASE_URL + GET_LIKED_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getLikedProductsByUser(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                               @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                               @RequestParam(value = "sortCriteria", required = false) String sortCriteria);


    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + CHANGE_PRODUCT_LIKE_STATUS)
    void changeProductLikeStatus(@RequestParam(value = "productId") Integer productId);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PostMapping(value = BASE_URL + GET_PRODUCTS_LIKE_STATUS)
    ResponseEntity<GetProductsLikeStatusResponse> getProductsLikeStatus(@Valid @RequestBody GetProductsLikeStatusRequest request);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_PRODUCT_BY_ID)
    ResponseEntity<ProductDTO> getProductById(@RequestParam(value = "productId") Integer productId);

    // PRODUCT ADMIN API
    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @DeleteMapping(value = BASE_URL + ADMIN_ACTION + DELETE_PRODUCT)
    void deleteProductAdmin(@RequestParam(value = "productId") Integer productId);


    // CHAT API
    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PostMapping(value = BASE_URL + SEND_PRIVATE_MESSAGE, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@Valid @RequestBody PrivateChatMessageRequest request);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @GetMapping(value = BASE_URL + GET_CONVERSATIONS_PREVIEW)
    ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                                         @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                                         @RequestParam(value = "sortCriteria", required = false) String sortCriteria);

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @GetMapping(value = BASE_URL + GET_CONVERSATION)
    ResponseEntity<ConversationResponse> getConversation(@RequestParam(value = "conversationId") Integer conversationId);


    // SEARCH ENGINE API

    @PostMapping(value = BASE_URL + PUBLIC_ACCESS + GET_PRODUCT_CATEGORY_ID)
    ResponseEntity<Integer> getProductCategoryId(@Valid @RequestBody GetProductCategoryIdRequest request);

    @PostMapping(value = BASE_URL + PUBLIC_ACCESS + SEARCH_PRODUCTS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<SearchProductsResponse> searchProducts(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                          @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                          @RequestParam(value = "sortCriteria", required = false) String sortCriteria,
                                                          @Valid @RequestBody SearchProductsRequest request);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + GET_CATEGORY_TREE, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetCategoryTreeResponse> getCategoryTree(@RequestParam(value = "categoryId") Integer categoryId);

    @GetMapping(value = BASE_URL + PUBLIC_ACCESS + SEARCH_PRODUCTS_BY_CATEGORY)
    ResponseEntity<SearchProductsResponse> searchProductsByCategory(@RequestParam(value = "categoryId") Integer categoryId,
                                                                    @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                                    @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                                    @RequestParam(value = "sortCriteria", required = false) String sortCriteria);

    // SEARCH ENGINE ADMIN API

    @Operation(security = { @SecurityRequirement(name = BEARER_AUTH) })
    @PutMapping(value = BASE_URL + ADMIN_ACTION + ADD_NEW_PRODUCT_CATEGORY)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);
}

