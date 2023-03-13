package com.rwws.rwserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserHobby {
    private Long hobbyId;
    private Long userId;
}
