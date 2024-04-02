package com.swapit.apiGateway.service.impl;

import com.swapit.apiGateway.service.AuthenticatedUserContextService;
import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.swapit.apiGateway.util.AuthenticatedUserPropertyType.CONTEXT_USER_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticatedUserContextServiceImpl implements AuthenticatedUserContextService {
    private final ExceptionFactory exceptionFactory;
    private final UserRepository userRepository;
    private static final String OAUTH2_USER_ID = "sub";

    @Override
    public Map<String, Object> getUserProperties() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user;
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                user = (User) authentication.getPrincipal();
            } else {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                Map<String, Object> attributes = oAuth2User.getAttributes();
                String oauth2UserId = (String) attributes.get(OAUTH2_USER_ID);
                user = userRepository.findUserByOauth2UserId(oauth2UserId)
                        .orElse(null);
            }
            assert user != null;
            if (user.getUserId() == null) {
                throw exceptionFactory.create(ExceptionType.UNAUTHORIZED_ACTION);
            }
            Map<String, Object> userAttributes = new HashMap<>();
            userAttributes.put(CONTEXT_USER_ID.name(), user.getUserId());
            return userAttributes;
        } catch (Exception e) {
            throw exceptionFactory.create(ExceptionType.UNAUTHORIZED_ACTION);
        }
    }
}
