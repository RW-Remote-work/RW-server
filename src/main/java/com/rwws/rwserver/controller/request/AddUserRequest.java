package com.rwws.rwserver.controller.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String email;
    private String password;
}
