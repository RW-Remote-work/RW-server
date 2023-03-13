package com.rwws.rwserver.service;

import com.rwws.rwserver.config.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
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
public class JWTTokenService {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private final JWTProperties properties;

    public JWTTokenService(JWTProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(properties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + properties.getExpiration() * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token    客户端传入的token
     * @param username 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, String username) {
        return getUserNameFromToken(token).equals(username) && !isExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isExpired(String token) {
        return getExpiredDateFromToken(token).before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(String username) {
        var claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}
