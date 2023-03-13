package com.rwws.rwserver.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.RegisterRequest;
import com.rwws.rwserver.domain.security.Authority;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.manager.RedisCacheManager;
import com.rwws.rwserver.mapper.UserAuthorityMapper;
import com.rwws.rwserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rwws.rwserver.common.enums.RedisKeyPrefix.EMAIL;

@Slf4j
@Service
public class RegisterService {

    private final UserMapper userMapper;
    private final UserAuthorityMapper userAuthorityMapper;
    private final PasswordEncoder passwordEncoder;
    private final RedisCacheManager redisCacheManager;

    public RegisterService(UserMapper userMapper,
                           UserAuthorityMapper userAuthorityMapper,
                           RedisCacheManager redisCacheManager,
                           PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
        this.redisCacheManager = redisCacheManager;
        this.passwordEncoder = passwordEncoder;
    }


    public void register(RegisterRequest request) {
        // 校验用户已经存在
        var exists = this.userMapper.exists(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getEmail, request.getEmail())
        );
        if (exists)
            throw new BadRequestProblem("The email exists");

        // 校验邮箱的验证码
        var key = EMAIL.format(request.getEmail());
        var verifyCode = redisCacheManager.get(key, Object::toString)
                .orElseThrow(() -> new BadRequestProblem("Email verification code don't exist"));
        if (!request.getCode().equals(verifyCode)) {
            throw new BadRequestProblem("Email verification code error");
        }

        // 新增用户
        var encryptPwd = this.passwordEncoder.encode(request.getPassword());
        var user = new User();
        user.setLogin(request.getEmail());
        user.setEmail(request.getEmail());
        user.setDisplayName(request.getEmail());
        user.setPassword(encryptPwd);
        user.setActivated(Boolean.TRUE);
        this.userMapper.insert(user);

        // 新增用户权限
        var userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getId());
        userAuthority.setAuthority(Authority.REGULAR_USER);
        this.userAuthorityMapper.insert(userAuthority);

        // todo 每个新用户都应该创建一个贝壳账户
    }
}
