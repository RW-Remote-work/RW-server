package com.rwws.rwserver.module.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.common.constant.ZoneIdConstant;
import com.rwws.rwserver.common.util.RWRequestUtil;
import com.rwws.rwserver.domain.security.Authority;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.exception.BadRequestProblem;
<<<<<<< HEAD:src/main/java/com/rwws/rwserver/module/system/login/service/RegisterService.java
import com.rwws.rwserver.module.support.service.RedisService;
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.system.user.mapper.UserAuthorityMapper;
import com.rwws.rwserver.module.system.user.mapper.UserMapper;
=======
import com.rwws.rwserver.module.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.user.mapper.UserAuthorityMapper;
import com.rwws.rwserver.module.user.mapper.UserMapper;
import com.rwws.rwserver.service.RedisService;
>>>>>>> 74d04db4d0194091fff91e6653b65ca71fa4afb5:src/main/java/com/rwws/rwserver/module/login/service/RegisterService.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@Slf4j
@Service
public class RegisterService {

    private UserMapper userMapper;

    private UserAuthorityMapper userAuthorityMapper;

    private PasswordEncoder passwordEncoder;

    private JwtTokenService tokenService;

    private RedisService redisService;

    public RegisterService(UserMapper userMapper,
                           UserAuthorityMapper userAuthorityMapper,
                           RedisService redisService,
                           PasswordEncoder passwordEncoder,
                           JwtTokenService tokenService) {
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
        this.redisService = redisService;
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
        String redisKey = this.redisService.generateRedisKey(RedisKeyConstant.Module.EMAIL_CODE, registerRequest.getEmail());
        Object verifyCode = this.redisService.get(redisKey);
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
        if (null != RWRequestUtil.getRequestUser())
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
        userVO.setEmail(user.getEmail());
        userVO.setLogin(user.getLogin());
        userVO.setCreateDate(LocalDateTime.ofInstant(user.getCreatedDate(), ZoneId.of(ZoneIdConstant.CHINA_STANDARD)));
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserVO(userVO);
        registerResponse.setToken(token);

        return registerResponse;
    }
}
