package com.rwws.rwserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("thread")
public class ThreadPoolProperties {
    private Integer corePoolSize;
    private Integer queueCapacity;
}
