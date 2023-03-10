package com.rwws.rwserver.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwws.rwserver.domain.security.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthorityMapper extends BaseMapper<UserAuthority> {
}
