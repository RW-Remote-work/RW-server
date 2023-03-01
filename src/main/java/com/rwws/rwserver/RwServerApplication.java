package com.rwws.rwserver;

import com.rwws.rwserver.exception.BadRequestProblem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rwws.rwserver.mapper")
@SpringBootApplication
public class RwServerApplication {
    public static void main(String[] args) {
        new BadRequestProblem(null, null);
        SpringApplication.run(RwServerApplication.class, args);
    }
}
