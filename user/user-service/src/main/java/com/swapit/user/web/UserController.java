package com.swapit.user.web;

import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;
import com.swapit.user.api.service.UserService;
import com.swapit.user.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserService {

    private final AuthenticationService authenticationService;
    private final UpdateUserDetailsService updateUserDetailsService;
    private final GetUserDetailsService getUserDetailsService;
    private final UserRestrictionsService userRestrictionsService;
    private final ScheduledOperationsService scheduledOperationsService;
    private final UserActionService userActionService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Override
    public ResponseEntity<Oauth2Response> oauth2login(Oauth2Request request) {
        return ResponseEntity.ok(authenticationService.oauth2login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    public void auditUserAction(AuditUserActionRequest request) {
        userActionService.auditUserAction(request);
    }

    @Override
    public ResponseEntity<GetUserActionsResponse> getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(userActionService.getUserActions(chunkNumber, nrElementsPerChunk, sortCriteria));
    }

    @Override
    public void forgottenPasswordReset(ForgottenPasswordResetRequest request) {
        authenticationService.forgottenPasswordReset(request);
    }

    @Override
    public void passwordReset(Integer userId, PasswordResetRequest request) {
        updateUserDetailsService.passwordReset(userId, request);
    }

    @Override
    public void emailReset(Integer userId, EmailResetRequest request) {
        updateUserDetailsService.emailReset(userId, request);
    }

    @Override
    public void usernameReset(Integer userId, UsernameResetRequest request) {
        updateUserDetailsService.usernameReset(userId, request);
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(getUserDetailsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetailsByUsername(String username) {
        return ResponseEntity.ok(getUserDetailsService.getUserDetailsByUsername(username));
    }

    @Override
    public ResponseEntity<GetSpecificUsersDetailsResponse> getSpecificUsersDetails(GetSpecificUsersDetailsRequest request) {
        return ResponseEntity.ok(getUserDetailsService.getSpecificUsersDetails(request));
    }

    @Override
    public void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request) {
        updateUserDetailsService.updateBasicUserDetails(userId, request);
    }

    @Override
    public void banUser(Integer userId, Integer banDaysDuration) {
        userRestrictionsService.banUser(userId, banDaysDuration);
    }

    @Override
    public ResponseEntity<GetUserAccountStatusResponse> getUserAccountStatus(Integer userId) {
        return ResponseEntity.ok(userRestrictionsService.getUserAccountStatus(userId));
    }

    @Override
    public void removeUserBan(Integer userId) {
        userRestrictionsService.removeUserBan(userId);
    }

    @Override
    public void manualSecurityCodesExpire() {
        scheduledOperationsService.securityCodesExpire();
    }

    @Override
    public void manualRemoveUsersBan() {
        scheduledOperationsService.removeUsersBan();
    }
}
