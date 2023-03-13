package com.rwws.rwserver.controller.response.shell;

import lombok.Data;

@Data
public class GetCurrentUserAccountResponse {
    private Long total;
    private Long earnedAmount;
    private Long purchasedAmount;
}
