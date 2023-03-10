package com.rwws.rwserver.module.system.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.common.constant.ZoneIdConstant;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.module.system.login.domain.response.LoginResponse;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.user.mapper.UserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Service
public class LoginService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public LoginService(UserDetailsService userDetailsService, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(String email, String password) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        if (Objects.isNull(userDetails))
            throw new BadRequestProblem("Incorrect user or password");
        if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadRequestProblem("Incorrect user or password");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtTokenService.generateToken(userDetails);

        User user = ((UserPrincipal) userDetails).getUser();
        RegisterResponse.UserVO userVO = new RegisterResponse.UserVO();
        userVO.setEmail(user.getEmail());
        userVO.setLogin(user.getLogin());
        userVO.setCreateDate(LocalDateTime.ofInstant(user.getCreatedDate(), ZoneId.of(ZoneIdConstant.CHINA_STANDARD)));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserVO(userVO);
        return loginResponse;
    }

}
