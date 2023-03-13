package com.rwws.rwserver.mapper.shell;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rwws.rwserver.domain.shell.ShellAccount;
import org.apache.ibatis.annotations.Param;

public interface ShellAccountMapper extends BaseMapper<ShellAccount> {

    IPage<ShellAccount> selectByEmailAndDisplayName(@Param("page") IPage<ShellAccount> iPage,
                                                    @Param("email") String email,
                                                    @Param("displayName") String displayName);

}
