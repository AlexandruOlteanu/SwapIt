package com.swapit.user.web;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import com.swapit.user.api.service.UserService;
import com.swapit.user.domain.User;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.service.CacheService;
import com.swapit.user.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@EnableCaching
@Slf4j
public class UserController implements UserService {

    private final AuthenticationService authenticationService;
    private final ExternalOperationsService externalOperationsService;
    private final CacheService cacheService;
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    public ResponseEntity<UserDetailsResponse> getUserDetails(Integer userId) {
        var userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return cacheService.getUserDetailsFromDb(userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        var productsResponseFuture = CompletableFuture.supplyAsync(() -> externalOperationsService.getAllProductsByUserId(userId));
        User user = userFuture.join();
        GetProductsResponse productsResponse = productsResponseFuture.join();
        var products = productsResponse.getProducts();
        UserDetailsResponse userDetailsResponse = UserDetailsResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .joinDate(user.getJoinDate())
                .products(products)
                .build();
        return ResponseEntity.ok(userDetailsResponse);
    }
}
