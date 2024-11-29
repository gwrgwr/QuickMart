package com.example.quickmart.service;

import com.example.quickmart.domain.user.UserEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;

    }

    public String generateLoginToken(UserEntity user) {
        var now = Instant.now();
        var expirationTime = now.plusSeconds(60 * 60);

        var claims = JwtClaimsSet.builder()
                .subject(user.getId())
                .claim("scope", user.getRole().name())
                .issuer("quickmart")
                .expiresAt(expirationTime)
                .issuedAt(now)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
