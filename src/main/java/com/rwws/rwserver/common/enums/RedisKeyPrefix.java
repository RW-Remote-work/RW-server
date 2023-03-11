package com.rwws.rwserver.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RedisKeyPrefix {
    EMAIL("EMAIL_CODE#%s");

    private final String prefix;

    public String format(String key) {
        return String.format(prefix, key);
    }
}
