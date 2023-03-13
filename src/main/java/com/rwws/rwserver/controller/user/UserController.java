package com.rwws.rwserver.controller.user;

import com.rwws.rwserver.controller.response.GetUserProfileResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping
//    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
//    public PagingUserResponse pagingUser() {
//
//    }

    @GetMapping("/profile")
    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public GetUserProfileResponse getUserProfile(@AuthenticationPrincipal UserPrincipal principal) {
        return userService.getUserProfile(principal.getId());
    }
}
