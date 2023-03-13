package com.rwws.rwserver.controller;

import com.rwws.rwserver.common.util.MybatisPlusUtil;
import com.rwws.rwserver.controller.response.PagingLoginRecordResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.LoginRecordService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login-records")
public class LoginRecordController {
    private final LoginRecordService loginRecordService;

    public LoginRecordController(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @GetMapping("")
    public PagingLoginRecordResponse pagingLoginRecord(@ParameterObject @PageableDefault Pageable pageable,
                                                       @AuthenticationPrincipal UserPrincipal principal) {
        return loginRecordService.pagingLoginRecord(MybatisPlusUtil.toPage(pageable), principal.getId());
    }
}
