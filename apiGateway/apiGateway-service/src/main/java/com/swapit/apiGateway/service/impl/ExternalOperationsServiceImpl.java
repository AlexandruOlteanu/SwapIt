package com.swapit.apiGateway.service.impl;

import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.commons.generator.UrlGeneratorService;
import com.swapit.commons.generator.impl.UrlGeneratorServiceImpl;
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
import com.swapit.user.api.dto.userActions.AddProductActionDTO;
import com.swapit.user.api.dto.userActions.AdminBanUserActionDTO;
import com.swapit.user.api.dto.userActions.AdminDeleteProductDTO;
import com.swapit.user.api.dto.userActions.AdminRemoveUserBanDTO;
import com.swapit.user.api.util.ActionType;
import com.swapit.user.api.util.RegisterProcessPhase;
import com.swapit.user.api.util.UserBasicDetailType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.swapit.user.api.util.UserBasicDetailType.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    @Qualifier("externalCallRestTemplate")
    private final RestTemplate restTemplate;
    private final UrlGeneratorService urlGeneratorService;
    private static final String USER_ID_PARAM = "userId";
    private static final String USERNAME_PARAM = "username";
    private static final String CONVERSATION_ID_PARAM = "conversationId";
    private static final String PRODUCT_ID_PARAM = "productId";
    private static final String CATEGORY_ID_PARAM = "categoryId";
    private static final String CHUNK_NUMBER_PARAM = "chunkNumber";
    private static final String NR_ELEMENTS_PER_CHUNK_PARAM = "nrElementsPerChunk";
    private static final String SORT_CRITERIA_PARAM = "sortCriteria";
    private static final String BAN_DAYS_DURATION_PARAM = "banDaysDuration";


    @Override
    public LoginResponse login(LoginRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.USER_LOGIN);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), LoginResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in User Login {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Oauth2Response oauth2login(Oauth2Request request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.USER_OAUTH2_LOGIN);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), Oauth2Response.class).getBody();
        } catch (Exception e) {
            log.error("Exception in User Oauth2 Login {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.USER_REGISTER);
        log.info(url);
        try {
            RegisterResponse registerResponse = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), RegisterResponse.class).getBody();
            assert registerResponse != null;
            if (request.getProcessPhase().equals(RegisterProcessPhase.FINALIZE)) {
                postUserAction(PostUserActionRequest.builder()
                        .actionType(ActionType.USER_REGISTER)
                        .userId(registerResponse.getUserId())
                        .build());
            }
            return registerResponse;
        } catch (Exception e) {
            log.error("Exception in User Register {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Integer createProduct(Integer userId, CreateProductRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.CREATE_PRODUCT);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            Integer productId = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Integer.class).getBody();
            addProductInSearchDictionary(productId);
            ProductDTO productDTO = getProductById(productId);
            PostUserActionRequest postUserActionRequest = PostUserActionRequest.builder()
                    .userId(userId)
                    .actionType(ActionType.USER_ADD_PRODUCT)
                    .addProductAction(AddProductActionDTO.builder()
                            .productId(productId)
                            .productTitle(productDTO.getTitle())
                            .build())
                    .build();
            postUserAction(postUserActionRequest);
            return productId;
        } catch (Exception e) {
            log.error("Exception in Product Creation {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateProduct(Integer userId, UpdateProductRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.UPDATE_PRODUCT);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Integer.class);
            updateProductInSearchDictionary(request.getProductId());
        } catch (Exception e) {
            log.error("Exception in Product Update {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteProduct(Integer userId, Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.DELETE_PRODUCT);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, Integer.class);
            deleteProductFromSearchDictionary(productId);
        } catch (Exception e) {
            log.error("Exception in deleting product {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetProductsResponse getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_PRODUCTS_BY_USER);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetProductsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting products by user id {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetProductsResponse getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_LIKED_PRODUCTS_BY_USER);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetProductsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting liked products of user {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void sendPrivateMessage(Integer userId, PrivateChatMessageRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SEND_PRIVATE_MESSAGE);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in Sending Private Message {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetUserDetailsResponse getUserDetails(Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_DETAILS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetUserDetailsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting user details {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetUserDetailsResponse getUserDetailsByUsername(String username) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_DETAILS_BY_USERNAME);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USERNAME_PARAM, Optional.ofNullable(username));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetUserDetailsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting user details by username {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConversationsPreviewResponse getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_CONVERSATIONS_PREVIEW);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            ConversationsPreviewResponse conversationsPreviewResponse = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, ConversationsPreviewResponse.class).getBody();

            assert conversationsPreviewResponse != null;
            Map<Integer, List<UserBasicDetailType>> requestedUserDetails = new HashMap<>();
            conversationsPreviewResponse.getConversationsPreview()
                    .stream().filter(cp -> cp.getConversationTitle() == null)
                    .forEach(conversationPreview -> {
                            Integer correspondent = conversationPreview.getOtherParticipantsIds().getFirst();
                            requestedUserDetails.put(correspondent, List.of(NAME, SURNAME, IMAGE));
                    });
            GetSpecificUsersDetailsRequest request = GetSpecificUsersDetailsRequest.builder()
                    .requestedUserDetails(requestedUserDetails)
                    .build();
            GetSpecificUsersDetailsResponse response = getSpecificUsersDetails(request);
            conversationsPreviewResponse.getConversationsPreview()
                    .stream().filter(cp -> cp.getConversationTitle() == null)
                    .forEach(cp -> {
                        Integer correspondent = cp.getOtherParticipantsIds().getFirst();
                        var currentUserDetails = response.getRequestedUserDetails().get(correspondent);
                        String userName = (String) currentUserDetails.get(NAME);
                        String userSurname = (String) currentUserDetails.get(SURNAME);
                        String userImage = (String) currentUserDetails.get(IMAGE);
                        cp.setConversationImage(userImage);
                        cp.setConversationTitle(Stream.of(userSurname, userName).filter(Objects::nonNull).collect(Collectors.joining(" ")));
                    });
            return conversationsPreviewResponse;
        } catch (Exception e) {
            log.error("Exception in getting conversations Preview {}", e.getMessage(), e);
            throw e;
        }
    }

    public GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SPECIFIC_USERS_DETAILS);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), GetSpecificUsersDetailsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting specific user details {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConversationResponse getConversation(Integer userId, Integer conversationId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_CONVERSATION);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(CONVERSATION_ID_PARAM, Optional.ofNullable(conversationId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, ConversationResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting conversation {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.UPDATE_BASIC_USER_DETAILS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Void.class).getBody();
        } catch (Exception e) {
            log.error("Exception in Updating Basic User Details: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.ADD_NEW_PRODUCT_CATEGORY);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), Void.class).getBody();
        } catch (Exception e) {
            log.error("Exception in adding product category {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Integer getProductCategoryId(GetProductCategoryIdRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_PRODUCT_CATEGORY_ID);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), Integer.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting product category id {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public SearchProductsResponse searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SEARCH_PRODUCTS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(request), SearchProductsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in searching products {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_PRODUCT_BY_ID);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, ProductDTO.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting product by product id {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetCategoryTreeResponse getCategoryTree(Integer categoryId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_CATEGORY_TREE);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(CATEGORY_ID_PARAM, Optional.ofNullable(categoryId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetCategoryTreeResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting category tree {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public SearchProductsResponse searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SEARCH_PRODUCTS_BY_CATEGORY);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(CATEGORY_ID_PARAM, Optional.ofNullable(categoryId))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, SearchProductsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in searching Products by category {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void changeProductLikeStatus(Integer userId, ChangeProductLikeStatusRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.CHANGE_PRODUCT_LIKE_STATUS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in changing product like status {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetProductsLikeStatusResponse getProductsLikeStatus(Integer userId, GetProductsLikeStatusRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_PRODUCTS_LIKE_STATUS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(request), GetProductsLikeStatusResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting products like status {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetProductsResponse getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_RECOMMENDED_PRODUCTS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetProductsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting recommended products {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void manualSecurityCodesExpire() {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.MANUAL_SECURITY_CODES_EXPIRE);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        } catch (Exception e) {
            log.error("Exception in Manual Security Codes Expire code {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void banUser(Integer adminUserId, Integer userId, Integer banDaysDuration) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.BAN_USER);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId))
                .queryParamIfPresent(BAN_DAYS_DURATION_PARAM, Optional.ofNullable(banDaysDuration));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null, Void.class);
            postUserAction(PostUserActionRequest.builder()
                    .userId(adminUserId)
                    .actionType(ActionType.ADMIN_BAN_USER)
                    .adminBanUserAction(AdminBanUserActionDTO.builder()
                            .bannedUserId(userId)
                            .banDurationInDays(banDaysDuration)
                            .build())
                    .build());
        } catch (Exception e) {
            log.error("Exception in banning user {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetUserAccountStatusResponse getUserAccountStatus(Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_ACCOUNT_STATUS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetUserAccountStatusResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting user account status {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void removeUserBan(Integer adminUserId, Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.REMOVE_USER_BAN);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, Void.class);
            postUserAction(PostUserActionRequest.builder()
                    .actionType(ActionType.ADMIN_REMOVE_USER_BAN)
                    .userId(adminUserId)
                    .adminRemoveUserBan(AdminRemoveUserBanDTO.builder()
                            .unbannedUserId(userId)
                            .build())
                    .build());
        } catch (Exception e) {
            log.error("Exception in removing user ban {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void manualRemoveUsersBan() {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.MANUAL_REMOVE_USERS_BAN);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        } catch (Exception e) {
            log.error("Exception in manual removing users ban {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void forgottenPasswordReset(ForgottenPasswordResetRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.FORGOTTEN_PASSWORD_RESET);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), Void.class).getBody();
        } catch (Exception e) {
            log.error("Exception in forgottenPasswordReset {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void passwordReset(Integer userId, PasswordResetRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.PASSWORD_RESET);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in password reset {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void emailReset(Integer userId, EmailResetRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.EMAIL_RESET);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in email reset {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void usernameReset(Integer userId, UsernameResetRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.USERNAME_RESET);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in username reset {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteProductAdmin(Integer adminUserId, Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.DELETE_PRODUCT_ADMIN);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            ProductDTO productDTO = getProductById(productId);
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, Void.class);
            deleteProductFromSearchDictionary(productId);
            postUserAction(PostUserActionRequest.builder()
                    .actionType(ActionType.ADMIN_DELETE_PRODUCT)
                    .userId(adminUserId)
                    .adminDeleteProduct(AdminDeleteProductDTO.builder()
                            .productTitle(productDTO.getTitle())
                            .build())
                    .build());
        } catch (Exception e) {
            log.error("Exception in deleting product {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void postUserAction(PostUserActionRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.POST_USER_ACTION);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in posting user action reset {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public GetUserActionsResponse getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_ACTIONS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(CHUNK_NUMBER_PARAM, Optional.ofNullable(chunkNumber))
                .queryParamIfPresent(NR_ELEMENTS_PER_CHUNK_PARAM, Optional.ofNullable(nrElementsPerChunk))
                .queryParamIfPresent(SORT_CRITERIA_PARAM, Optional.ofNullable(sortCriteria));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, GetUserActionsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting user actions {}", e.getMessage(), e);
            throw e;
        }
    }


    public void addProductInSearchDictionary(Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.ADD_PRODUCT_IN_SEARCH_DICTIONARY);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, null, Void.class);
        } catch (Exception e) {
            log.error("Exception in adding product in Dictionary {}", e.getMessage(), e);
            throw e;
        }
    }

    public void updateProductInSearchDictionary(Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.UPDATE_PRODUCT_IN_SEARCH_DICTIONARY);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, null, Void.class);
        } catch (Exception e) {
            log.error("Exception in updating product in Dictionary {}", e.getMessage(), e);
            throw e;
        }
    }

    public void deleteProductFromSearchDictionary(Integer productId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.DELETE_PRODUCT_FROM_SEARCH_DICTIONARY);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(PRODUCT_ID_PARAM, Optional.ofNullable(productId));
        log.info(uriBuilder.toUriString());
        try {
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.DELETE, null, Void.class);
        } catch (Exception e) {
            log.error("Exception in deleting product from search dictionary {}", e.getMessage(), e);
            throw e;
        }
    }

}


