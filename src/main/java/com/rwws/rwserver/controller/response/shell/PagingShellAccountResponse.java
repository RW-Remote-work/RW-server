package com.rwws.rwserver.controller.response.shell;

import com.rwws.rwserver.controller.response.PagingResponse;
import lombok.Data;

import java.util.List;

@Data
public class PagingShellAccountResponse extends PagingResponse {

    private List<ShellAccount> shellAccounts = List.of();

    @Data
    public static class ShellAccount {
        private String email;
        private String displayName;

        private Long total;


        private Long earnedAmount;


        private Long purchasedAmount;
    }
}
