package com.swapit.user.web;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import com.swapit.user.api.service.UserService;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@EnableCaching
@Slf4j
public class UserController implements UserService {

    private final AuthenticationService authenticationService;
    private final ExternalOperationsService externalOperationsService;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    @Cacheable(value = "getUserDetails", keyGenerator = "cacheKeyGenerator")
    public ResponseEntity<UserDetailsResponse> getUserDetails(String username) throws Exception {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new Exception("User with username " + username + "doesn't exist"));
        GetProductsResponse productsResponse = externalOperationsService.getAllProductsByUserId(user.getUserId());
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
