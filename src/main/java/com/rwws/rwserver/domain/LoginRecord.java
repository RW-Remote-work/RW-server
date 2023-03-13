package com.rwws.rwserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class LoginRecord {
    private Long userId;
    private String ip;
    private Instant time;

}
