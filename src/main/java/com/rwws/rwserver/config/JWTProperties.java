package com.rwws.rwserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("jwt")
public class JWTProperties {
    private String tokenHeader;
    private String tokenHead;
    private String secret;
    private Long expiration;
}
