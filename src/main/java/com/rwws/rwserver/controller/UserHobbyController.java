package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddHobbyRequest;
import com.rwws.rwserver.controller.response.ListUserHobbyResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.UserHobbyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-hobbies")
public class UserHobbyController {
    private final UserHobbyService userHobbyService;

    public UserHobbyController(UserHobbyService userHobbyService) {
        this.userHobbyService = userHobbyService;
    }

    @GetMapping
    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public ListUserHobbyResponse listUserHobby(@AuthenticationPrincipal UserPrincipal principal) {
        return userHobbyService.listUserHobby(principal.getId());
    }


    @PostMapping
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public void addUserHobby(@AuthenticationPrincipal UserPrincipal principal,
                             @Validated @RequestBody AddHobbyRequest request) {
        userHobbyService.addUserHobby(principal.getId(), request);
    }

    @DeleteMapping("")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public void deleteUserHobby(@AuthenticationPrincipal UserPrincipal principal,
                                @RequestParam Long hobbyId) {
        userHobbyService.deleteUserHobby(principal.getId(), hobbyId);
    }
}
