package com.rwws.rwserver.controller.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class AddHobbyRequest {
    @NotBlank
    @Max(value = 10)
    private String name;
}
