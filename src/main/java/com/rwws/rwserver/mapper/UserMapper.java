package com.rwws.rwserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwws.rwserver.domain.security.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
}
