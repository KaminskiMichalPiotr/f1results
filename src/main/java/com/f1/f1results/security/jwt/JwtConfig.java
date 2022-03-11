package com.f1.f1results.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;


@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private int tokenExpirationAfterDays;

    public JwtConfig() {
    }

    @Bean
    public SecretKey getSecretKeyForSinging() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
