package com.swapit.apiGateway.security.service;

import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.user.api.domain.request.Oauth2Request;
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.utils.UserStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final ExternalOperationsService externalOperationsService;
    @Value("${apiGateway.ui.userBanPage.redirect.uri}")
    private String uiUserBanPage;
    private static final String OAUTH2_USER_ID = "sub";
    private static final String USER_IMAGE = "picture";
    private static final String NAME = "family_name";
    private static final String SURNAME = "given_name";
    private static final String EMAIL = "email";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
        Oauth2Request oauth2Request = Oauth2Request.builder()
                .oauth2UserId(oAuth2User.getAttribute(OAUTH2_USER_ID))
                .userImage(oAuth2User.getAttribute(USER_IMAGE))
                .name(oAuth2User.getAttribute(NAME))
                .surname(oAuth2User.getAttribute(SURNAME))
                .email(oAuth2User.getAttribute(EMAIL))
                .build();
        Oauth2Response loginResponse = externalOperationsService.oauth2login(oauth2Request);
        User user = userRepository.findById(loginResponse.getUserId()).orElse(null);
        assert user != null;
        if (user.getStatus().equals(UserStatus.INACTIVE)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(uiUserBanPage);
            return;
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
