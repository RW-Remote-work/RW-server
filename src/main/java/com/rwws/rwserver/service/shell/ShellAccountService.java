package com.rwws.rwserver.service.shell;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.shell.PagingShellAccountRequest;
import com.rwws.rwserver.controller.response.shell.GetCurrentUserAccountResponse;
import com.rwws.rwserver.controller.response.shell.PagingShellAccountResponse;
import com.rwws.rwserver.domain.shell.ShellAccount;
import com.rwws.rwserver.mapper.UserMapper;
import com.rwws.rwserver.mapper.shell.ShellAccountMapper;
import org.springframework.stereotype.Service;

@Service
public class ShellAccountService {
    private final ShellAccountMapper shellAccountMapper;
    private final UserMapper userMapper;

    public ShellAccountService(ShellAccountMapper shellAccountMapper,
                               UserMapper userMapper) {
        this.shellAccountMapper = shellAccountMapper;
        this.userMapper = userMapper;
    }

    public GetCurrentUserAccountResponse getCurrentUserAccount(Long userId) {
        ShellAccount shellAccount = shellAccountMapper.selectOne(
                Wrappers.<ShellAccount>lambdaQuery()
                        .eq(ShellAccount::getUserId, userId)
        );
        var response = new GetCurrentUserAccountResponse();
        response.setTotal(shellAccount.getTotal());
        response.setEarnedAmount(shellAccount.getEarnedAmount());
        response.setPurchasedAmount(shellAccount.getPurchasedAmount());
        return response;
    }

    public PagingShellAccountResponse pagingShellAccount(IPage<ShellAccount> iPage, PagingShellAccountRequest request) {
        var pageResult = shellAccountMapper.selectByEmailAndDisplayName(iPage, request.getEmail(), request.getDisplayName())
                .convert(it -> {
                    var shellAccount = new PagingShellAccountResponse.ShellAccount();
                    shellAccount.setTotal(it.getTotal());
                    shellAccount.setEarnedAmount(it.getEarnedAmount());
                    shellAccount.setPurchasedAmount(it.getPurchasedAmount());
                    var user = userMapper.selectById(it.getUserId());
                    shellAccount.setEmail(user.getEmail());
                    shellAccount.setDisplayName(user.getDisplayName());
                    return shellAccount;
                });
        var response = new PagingShellAccountResponse();
        response.setShellAccounts(pageResult.getRecords());
        response.setTotal(pageResult.getTotal());
        response.setSize(pageResult.getSize());
        response.setCurrent(pageResult.getCurrent());
        return response;
    }

}
