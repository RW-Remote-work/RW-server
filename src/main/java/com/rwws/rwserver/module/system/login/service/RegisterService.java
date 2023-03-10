package com.rwws.rwserver.module.system.login.service;

import cn.hutool.core.util.StrUtil;
import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.common.util.RWRequestUtil;
import com.rwws.rwserver.domain.security.Authority;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.module.support.service.RedisService;
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.user.mapper.UserAuthorityMapper;
import com.rwws.rwserver.module.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class RegisterService {

    private UserMapper userMapper;

    private UserAuthorityMapper userAuthorityMapper;

    private PasswordService passwordService;

    private JwtTokenService tokenService;

    private RedisService redisService;

    public RegisterService(UserMapper userMapper,
                           RedisService redisService,
                           PasswordService passwordService,
                           JwtTokenService tokenService) {
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
    }

    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse register(RegisterRequest registerRequest) {
        // 校验邮箱的验证码
        String redisKey = this.redisService.generateRedisKey(RedisKeyConstant.Module.EMAIL_CODE, registerRequest.getEmail());
        Object verifyCode = this.redisService.get(redisKey);
        if (Objects.isNull(verifyCode) || !StrUtil.equals((String) verifyCode, registerRequest.getCode())) {
            throw new BadRequestProblem("Email verification code error");
        }

        // 新增用户
        String password = this.passwordService.getEncryptPwd(registerRequest.getPassword());
        User user = new User();
        user.setLogin(registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(password);
        user.setActivated(Boolean.TRUE);
        user.setCreatedBy(String.valueOf(RWRequestUtil.getRequestUser().getUserId()));
        user.setCreatedDate(Instant.now());
        this.userMapper.insert(user);

        // 新增用户权限
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getId());
        userAuthority.setAuthority(Authority.REGULAR_USER);
        this.userAuthorityMapper.insert(userAuthority);

        // 生成用户Token
        UserDetails userDetails = new UserPrincipal(user, Set.of(Authority.REGULAR_USER));
        String token = this.tokenService.generateToken(userDetails);
        RegisterResponse.UserVO userVO = new RegisterResponse.UserVO();
        userVO.setToken(token);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserVO(userVO);

        return registerResponse;
    }
}
