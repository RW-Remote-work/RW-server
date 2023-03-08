package com.rwws.rwserver.module.user.domain.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
