package com.rwws.rwserver.controller.shell;

import com.rwws.rwserver.common.util.MybatisPlusUtil;
import com.rwws.rwserver.controller.request.shell.PagingShellAccountRequest;
import com.rwws.rwserver.controller.response.shell.GetCurrentUserAccountResponse;
import com.rwws.rwserver.controller.response.shell.PagingShellAccountResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.shell.ShellAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shell")
@SecurityRequirement(name = "Bearer Authentication")
public class ShellAccountController {
    private final ShellAccountService shellAccountService;

    public ShellAccountController(ShellAccountService shellAccountService) {
        this.shellAccountService = shellAccountService;
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    @PostMapping("/accounts")
    public PagingShellAccountResponse pagingShellAccount(@ParameterObject @PageableDefault Pageable pageable,
                                                         @RequestBody PagingShellAccountRequest request) {
        return shellAccountService.pagingShellAccount(MybatisPlusUtil.toPage(pageable), request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/accounts/current-user")
    public GetCurrentUserAccountResponse getCurrentUserAccount(@AuthenticationPrincipal UserPrincipal principal) {
        return shellAccountService.getCurrentUserAccount(principal.getId());
    }
}
