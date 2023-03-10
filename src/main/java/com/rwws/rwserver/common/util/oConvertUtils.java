package com.rwws.rwserver.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * 对象工具类
 * @Author ko
 * @Date 2023/3/10 17:04
 * @Version 1.0
 */
public class oConvertUtils {
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }
    /**
     * 获取本机IP
     */
    public static String getIp() {
        String ip = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 判断 list 是否为空
     *
     * @param list
     * @return true or false
     * list == null		: true
     * list.size() == 0	: true
     */
    public static boolean listIsEmpty(Collection list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断 list 是否不为空
     *
     * @param list
     * @return true or false
     * list == null		: false
     * list.size() == 0	: false
     */
    public static boolean listIsNotEmpty(Collection list) {
        return !listIsEmpty(list);
    }



}
