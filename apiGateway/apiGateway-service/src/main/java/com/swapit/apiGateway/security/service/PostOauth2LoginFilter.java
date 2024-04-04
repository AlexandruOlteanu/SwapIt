package com.swapit.apiGateway.security.service;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.utils.UserStatus;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostOauth2LoginFilter extends GenericFilter {

    private final UserRepository userRepository;
    private static final String OAUTH2_USER_ID = "sub";
    private final ExceptionFactory exceptionFactory;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            String oauth2UserId = oAuth2User.getAttribute(OAUTH2_USER_ID);
            User user = userRepository.findUserByOauth2UserId(oauth2UserId)
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
            if (user.getStatus().equals(UserStatus.INACTIVE)) {
                throw exceptionFactory.create(ExceptionType.USER_BANNED);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
