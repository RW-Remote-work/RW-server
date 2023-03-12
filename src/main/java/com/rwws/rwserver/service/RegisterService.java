package com.rwws.rwserver.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.common.constant.ZoneIdConstant;
import com.rwws.rwserver.controller.request.RegisterRequest;
import com.rwws.rwserver.controller.response.RegisterResponse;
import com.rwws.rwserver.domain.security.Authority;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.manager.RedisCacheManager;
import com.rwws.rwserver.mapper.UserAuthorityMapper;
import com.rwws.rwserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import static com.rwws.rwserver.common.enums.RedisKeyPrefix.EMAIL;

@Slf4j
@Service
public class RegisterService {

    private final UserMapper userMapper;

    private final UserAuthorityMapper userAuthorityMapper;

    private final PasswordEncoder passwordEncoder;

    private final JWTTokenService tokenService;

    private final RedisCacheManager redisCacheManager;

    public RegisterService(UserMapper userMapper,
                           UserAuthorityMapper userAuthorityMapper,
                           RedisCacheManager redisCacheManager,
                           PasswordEncoder passwordEncoder,
                           JWTTokenService tokenService) {
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
        this.redisCacheManager = redisCacheManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) {
        // 校验用户已经存在
        boolean exists = this.userMapper.exists(
                new QueryWrapper<User>().eq("email", registerRequest.getEmail())
        );
        if (exists)
            throw new BadRequestProblem("The email exists");

        // 校验邮箱的验证码
        var key = EMAIL.format(registerRequest.getEmail());
        var verifyCode = redisCacheManager.get(key, Object::toString).orElseThrow();
        if (!registerRequest.getCode().equals(verifyCode)) {
            throw new BadRequestProblem("Email verification code error");
        }

        // 新增用户
        String encryptPwd = this.passwordEncoder.encode(registerRequest.getPassword());
        User user = new User();
        user.setLogin(registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setDisplayName(registerRequest.getEmail());
        user.setPassword(encryptPwd);
        user.setActivated(Boolean.TRUE);
        this.userMapper.insert(user);

        // 新增用户权限
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getId());
        userAuthority.setAuthority(Authority.REGULAR_USER);
        this.userAuthorityMapper.insert(userAuthority);

        // 生成用户Token
        UserDetails userDetails = new UserPrincipal(user, Set.of(Authority.REGULAR_USER));
        String token = this.tokenService.generateToken(userDetails.getUsername());
        RegisterResponse.UserVO userVO = new RegisterResponse.UserVO();
        userVO.setEmail(user.getEmail());
        userVO.setLogin(user.getLogin());
        userVO.setCreateDate(LocalDateTime.ofInstant(user.getCreatedDate(), ZoneId.of(ZoneIdConstant.CHINA_STANDARD)));
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserVO(userVO);
        registerResponse.setToken(token);

        return registerResponse;
    }
}
