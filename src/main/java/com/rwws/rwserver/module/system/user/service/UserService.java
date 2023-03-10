package com.rwws.rwserver.module.system.user.service;

import com.rwws.rwserver.module.system.user.mapper.UserMapper;
import com.rwws.rwserver.module.system.user.request.AddUserRequest;
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
}
