package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.AddUserRequest;
import com.rwws.rwserver.controller.response.GetUserProfileResponse;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addUser(AddUserRequest request) {

    }

    public boolean isActivated(String username) {
        return userMapper.exists(
                Wrappers.<User>lambdaQuery()
                        .eq(User::isActivated, Boolean.TRUE)
                        .or(
                                it -> it.eq(User::getEmail, username)
                                        .or()
                                        .eq(User::getLogin, username)
                        )
        );
    }

    public GetUserProfileResponse getUserProfile(Long userId) {
        //todo
        return null;
    }
}
