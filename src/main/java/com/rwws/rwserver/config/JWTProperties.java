package com.rwws.rwserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.temporal.ChronoUnit;

@Data
@ConfigurationProperties("jwt")
public class JWTProperties {
    private String secret;
    private Expiration expiration;


    @Data
    public static class Expiration {
        private Long duration = 1L;
        private ChronoUnit unit = ChronoUnit.DAYS;
    }
}
