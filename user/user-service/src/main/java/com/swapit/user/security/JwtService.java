package com.swapit.user.security;

import com.swapit.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${application.security.jwt.secretKey}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${application.security.jwt.refreshToken.expiration}")
    private long refreshExpirationMs;

    @Getter
    private Key signingKey;

    private static final String USER_ROLE_JWT_KEY = "user_role";
    private static final String USER_ID_JWT_KEY = "user_id";

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ROLE_JWT_KEY, user.getUserRole());
        claims.put(USER_ID_JWT_KEY, user.getUserId());
        return createToken(claims, userDetails.getUsername());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), refreshExpirationMs);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return createToken(claims, subject, jwtExpirationMs);
    }

    private String createToken(Map<String, Object> claims, String subject, long expirationMs) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expirationMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
