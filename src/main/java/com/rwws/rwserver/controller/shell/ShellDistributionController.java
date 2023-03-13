package com.rwws.rwserver.controller.shell;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shell/distribution")
@SecurityRequirement(name = "Bearer Authentication")
public class ShellDistributionController {

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping
    public void addShellDistribution() {

    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping
    public void pagingShellDistribution() {

    }
}
