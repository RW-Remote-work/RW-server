package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.RegisterRequest;
import com.rwws.rwserver.controller.response.RegisterResponse;
import com.rwws.rwserver.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {
        return this.registerService.register(request);
    }

    @PutMapping
    public RegisterResponse update(@Validated @RequestBody RegisterRequest request) {
        return null;
    }
}
