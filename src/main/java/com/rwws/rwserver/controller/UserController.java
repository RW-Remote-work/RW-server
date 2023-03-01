package com.rwws.rwserver.controller;

import com.rwws.rwserver.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void addUser(@RequestBody AddUserRequest request) {
        userService.addUser(request);
    }
}
