package com.rwws.rwserver.module.user.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
