package com.rwws.rwserver.module.user.controller;

import com.rwws.rwserver.module.user.domain.request.AddUserRequest;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.module.user.service.UserService;
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

    @PreAuthorize("hasAuthority(T(com.rwws.rwserver.domain.security.Authority).SUPER_ADMIN)")
    @PostMapping("")
    public void addUser(@AuthenticationPrincipal UserPrincipal _principal,
                        @RequestBody AddUserRequest request) {
        userService.addUser(request);
    }
}
