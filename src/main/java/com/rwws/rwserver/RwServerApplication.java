package com.rwws.rwserver;

import com.rwws.rwserver.config.ApplicationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ApplicationProperties.class)
@MapperScan("com.rwws.rwserver.mapper")
@SpringBootApplication
public class RwServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RwServerApplication.class, args);
    }
}
