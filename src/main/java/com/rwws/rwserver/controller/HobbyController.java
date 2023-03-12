package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddHobbyRequest;
import com.rwws.rwserver.controller.response.ListHobbyResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.service.HobbyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {
    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    @GetMapping
    public ListHobbyResponse listHobby(@AuthenticationPrincipal UserPrincipal _principal) {
        return hobbyService.listHobby(_principal.getId());
    }


    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    @PostMapping
    public void addHobby(@AuthenticationPrincipal UserPrincipal _principal,
                         @Validated @RequestBody AddHobbyRequest request) {
        if (hobbyService.exist(_principal.getId(), request.getName())) {
            throw new BadRequestProblem("The hobby has been existed");
        }
        hobbyService.addHobby(_principal.getId(), request);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    @DeleteMapping("")
    public void deleteHobby(@AuthenticationPrincipal UserPrincipal _principal,
                            @RequestParam List<Long> ids) {
        hobbyService.deleteHobby(_principal.getId(), ids);
    }
}
