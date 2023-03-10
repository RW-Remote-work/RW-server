package com.rwws.rwserver.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.rwws.rwserver.config.JWTProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
@Slf4j
@Component
public class JWTManager {
    private static final String BEARER_PREFIX = "Bearer ";
    private final JWTProperties properties;

    public JWTManager(JWTProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据负责生成JWT的token
     */
    public String generateByUser(String username) {
        var expiration = properties.getExpiration();
        return BEARER_PREFIX + JWT.create().withSubject(username)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(expiration.getDuration(), expiration.getUnit()))
                .sign(Algorithm.HMAC512(properties.getSecret()));
    }

    /**
     * 从token中获取JWT中的负载
     *
     * @param token
     * @return
     */
    public Map<String, Claim> getClaims(String token) {
        token = token.startsWith(BEARER_PREFIX) ? token.substring(BEARER_PREFIX.length()) : token;// The part after "Bearer "
        return JWT.require(Algorithm.HMAC512(properties.getSecret()))
                .build()
                .verify(token)
                .getClaims();
    }

}
