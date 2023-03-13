package com.rwws.rwserver.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class TestPageController {
    @GetMapping("/login.html")
    public ClassPathResource login() {
        return new ClassPathResource("/static/login.html");
    }
}
