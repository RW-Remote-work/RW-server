package com.rwws.rwserver.module.system.login.service;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private static final String PASSWORD_SALT_FORMAT = "remote_%s_work_$^&*";

    public String getEncryptPwd(String password) {
        return SecureUtil.md5().digestHex(String.format(PASSWORD_SALT_FORMAT, password));
    }
}
