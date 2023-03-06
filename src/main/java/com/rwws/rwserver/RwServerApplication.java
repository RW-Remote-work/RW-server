package com.rwws.rwserver;

import com.rwws.rwserver.config.ApplicationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ApplicationProperties.class)
@MapperScan("com.rwws.rwserver")
@SpringBootApplication
public class RwServerApplication {
    public static void main(String[] args) {
        // 设置Log4j2全异步启动方式
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(RwServerApplication.class, args);
    }
}
