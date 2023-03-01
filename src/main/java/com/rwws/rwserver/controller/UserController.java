package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddUserRequest;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping("")
    public void addUser(@AuthenticationPrincipal UserPrincipal _principal,
                        @RequestBody AddUserRequest request) {
        userService.addUser(request);
    }
}
