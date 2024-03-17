package com.swapit.apiGateway.service.impl;

import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.commons.urlGenerator.UrlGeneratorService;
import com.swapit.commons.urlGenerator.UrlGeneratorServiceImpl;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;
import com.swapit.user.api.util.UserBasicDetailType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.swapit.user.api.util.UserBasicDetailType.NAME;
import static com.swapit.user.api.util.UserBasicDetailType.SURNAME;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    @Qualifier("externalCallRestTemplate")
    private final RestTemplate restTemplate;
    private final UrlGeneratorService urlGeneratorService;
    private static final String USER_ID_PARAM = "userId";
    private static final String CONVERSATION_ID_PARAM = "conversationId";

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
    public RegisterResponse register(RegisterRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.USER_REGISTER);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), RegisterResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in User Register {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void productCreation(ProductCreationRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.PRODUCT_CREATION);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in Product Creation {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SEND_PRIVATE_MESSAGE);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), Void.class);
        } catch (Exception e) {
            log.error("Exception in Sending Private Message {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public UserDetailsResponse getUserDetails(Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_DETAILS);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, UserDetailsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting user details {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConversationsPreviewResponse getConversationsPreview(Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_CONVERSATIONS_PREVIEW);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USER_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            ConversationsPreviewResponse conversationsPreviewResponse = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, ConversationsPreviewResponse.class).getBody();

            assert conversationsPreviewResponse != null;
            Map<Integer, List<UserBasicDetailType>> requestedUserDetails = new HashMap<>();
            conversationsPreviewResponse.getConversationsPreview()
                    .stream().filter(cp -> cp.getConversationTitle() == null)
                    .forEach(conversationPreview -> {
                            Integer correspondent = conversationPreview.getOtherParticipantsIds().getFirst();
                            requestedUserDetails.put(correspondent, List.of(NAME, SURNAME));
                    });
            SpecificUsersDetailsRequest request = SpecificUsersDetailsRequest.builder()
                    .requestedUserDetails(requestedUserDetails)
                    .build();
            SpecificUsersDetailsResponse response = getSpecificUsersDetails(request);
            conversationsPreviewResponse.getConversationsPreview()
                    .stream().filter(cp -> cp.getConversationTitle() == null)
                    .forEach(cp -> {
                        Integer correspondent = cp.getOtherParticipantsIds().getFirst();
                        var currentUserDetails = response.getRequestedUserDetails().get(correspondent);
                        String userName = (String) currentUserDetails.get(NAME);
                        String userSurname = (String) currentUserDetails.get(SURNAME);
                        cp.setConversationTitle(userName + " " + userSurname);
                    });
            return conversationsPreviewResponse;
        } catch (Exception e) {
            log.error("Exception in getting conversations Preview {}", e.getMessage(), e);
            throw e;
        }
    }

    public SpecificUsersDetailsResponse getSpecificUsersDetails(SpecificUsersDetailsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.SPECIFIC_USERS_DETAILS);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), SpecificUsersDetailsResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting specific user details {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ConversationResponse getConversation(Integer conversationId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_CONVERSATION);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
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
    public void updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.UPDATE_BASIC_USER_DETAILS);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), Void.class).getBody();
        } catch (Exception e) {
            log.error("Exception in Updating Basic User Details: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.UPDATE_PROTECTED_USER_DETAILS);
        log.info(url);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), Void.class).getBody();
        } catch (Exception e) {
            log.error("Exception in Updating Protected User Details: {}", e.getMessage(), e);
            throw e;
        }
    }

}


