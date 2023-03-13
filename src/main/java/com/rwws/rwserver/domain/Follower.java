package com.rwws.rwserver.domain;

import lombok.Data;

/**
 * 关注者
 */
@Data
public class Follower {
    private Long id;
    private Long followerId;
    private Long userId;
}
