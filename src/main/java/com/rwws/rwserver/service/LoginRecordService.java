package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.response.PagingLoginRecordResponse;
import com.rwws.rwserver.domain.LoginRecord;
import com.rwws.rwserver.mapper.LoginRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginRecordService {
    private final LoginRecordMapper loginRecordMapper;

    public LoginRecordService(LoginRecordMapper loginRecordMapper) {
        this.loginRecordMapper = loginRecordMapper;
    }

    public void add(LoginRecord loginRecord) {
        loginRecordMapper.insert(loginRecord);
    }


    public PagingLoginRecordResponse pagingLoginRecord(IPage<LoginRecord> IPage, Long userId) {
        var page = loginRecordMapper.selectPage(
                IPage,
                Wrappers.<LoginRecord>lambdaQuery()
                        .eq(LoginRecord::getUserId, userId)
        ).convert(it -> {
            var loginRecord = new PagingLoginRecordResponse.LoginRecord();
            loginRecord.setIp(it.getIp());
            loginRecord.setUserId(it.getUserId());
            loginRecord.setTime(it.getTime());
            return loginRecord;
        });

        var response = new PagingLoginRecordResponse();
        response.setLoginRecords(page.getRecords());
        response.setSize(page.getSize());
        response.setCurrent(page.getCurrent());
        response.setTotal(page.getTotal());
        return response;
    }
}
