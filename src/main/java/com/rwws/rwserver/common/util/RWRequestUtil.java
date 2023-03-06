package com.rwws.rwserver.common.util;

import com.rwws.rwserver.common.core.domain.RequestUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求用户  工具类
 *
 */
@Slf4j
public class RWRequestUtil {

    private static final ThreadLocal<RequestUser> requestThreadLocal = new ThreadLocal<>();

    public static void setRequestUser(RequestUser requestUser) {
        requestThreadLocal.set(requestUser);
    }

    public static RequestUser getRequestUser() {
        return requestThreadLocal.get();
    }

    public static Long getRequestUserId() {
        RequestUser requestUser = getRequestUser();
        return null == requestUser ? null : requestUser.getUserId();
    }

    public static void remove() {
        requestThreadLocal.remove();
    }


}
