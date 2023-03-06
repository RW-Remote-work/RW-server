package com.rwws.rwserver.controller.request;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class AddHobbyRequest {
    @Max(value = 10)
    private String name;
}
