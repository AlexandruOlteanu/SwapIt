package com.swapit.apiGateway.api;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.request.UpdateProtectedUserDetailsRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public interface ApiGatewayService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/apiGateway/";
    String LOGIN = "auth/login";
    String REGISTER = "auth/register";
    String PRODUCT_CREATION = "productCreation";
    String SEND_PRIVATE_MESSAGE = "sendPrivateMessage";
    String USER_DETAILS = "getUserDetails";
    String GET_CONVERSATIONS_PREVIEW = "getConversationsPreview";
    String GET_CONVERSATION = "getConversation";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";
    String UPDATE_PROTECTED_USER_DETAILS = "updateProtectedUserDetails";
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";
    String GET_ALL_PRODUCT_CATEGORIES = "getAllProductCategories";

    @PostMapping(value = BASE_URL + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PutMapping(value = BASE_URL + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @PutMapping(value = BASE_URL + PRODUCT_CREATION, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void productCreation(@Valid @RequestBody ProductCreationRequest request);

    @PostMapping(value = BASE_URL + SEND_PRIVATE_MESSAGE, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@Valid @RequestBody PrivateChatMessageRequest request);

    @GetMapping(value = BASE_URL + USER_DETAILS)
    ResponseEntity<UserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_CONVERSATIONS_PREVIEW)
    ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_CONVERSATION)
    ResponseEntity<ConversationResponse> getConversation(@RequestParam(value = "conversationId") Integer conversationId);

    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateBasicUserDetails(@Valid @RequestBody UpdateBasicUserDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PROTECTED_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateProtectedUserDetails(@Valid @RequestBody UpdateProtectedUserDetailsRequest request);

    @PutMapping(value = BASE_URL + ADD_NEW_PRODUCT_CATEGORY)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCT_CATEGORIES)
    ResponseEntity<GetProductCategoriesResponse> getAllProductCategories();
}
