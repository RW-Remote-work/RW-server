package com.rwws.rwserver.module.system.user.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
