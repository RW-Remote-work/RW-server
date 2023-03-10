package com.rwws.rwserver.module.support.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.common.core.domain.SystemEnvironment;
import com.rwws.rwserver.common.enumer.SystemEnvironmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private StringRedisTemplate stringRedisTemplate;

    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> redisValueOperations;

    private HashOperations<String, String, Object> redisHashOperations;

    private ListOperations<String, Object> redisListOperations;

    private SetOperations<String, Object> redisSetOperations;

    private SystemEnvironment systemEnvironment;


    public RedisService(StringRedisTemplate stringRedisTemplate,
                        RedisTemplate<String, Object> redisTemplate,
                        ValueOperations<String, Object> redisValueOperations,
                        HashOperations<String, String, Object> redisHashOperations,
                        ListOperations<String, Object> redisListOperations,
                        SetOperations<String, Object> redisSetOperations,
                        SystemEnvironment systemEnvironment) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
        this.redisValueOperations = redisValueOperations;
        this.redisHashOperations = redisHashOperations;
        this.redisListOperations = redisListOperations;
        this.redisSetOperations = redisSetOperations;
        this.systemEnvironment = systemEnvironment;
    }

    /**
     * 生成redis key
     * @param prefix
     * @param key
     * @return
     */
    public String generateRedisKey(String prefix, String key) {
        SystemEnvironmentEnum currentEnvironment = systemEnvironment.getCurrentEnvironment();
        return systemEnvironment.getProjectName() + RedisKeyConstant.SEPARATOR + currentEnvironment.getValue() +  RedisKeyConstant.SEPARATOR + prefix + key;
    }

    /**
     * redis key 解析成真实的内容
     * @param redisKey
     * @return
     */
    public static String redisKeyParse(String redisKey) {
        if(StrUtil.isBlank(redisKey)){
            return "";
        }
        int index = redisKey.lastIndexOf(RedisKeyConstant.SEPARATOR);
        if(index < 1){
            return redisKey;
        }
        return redisKey.substring(index);
    }

    public boolean getLock(String key, long expire) {
        return redisValueOperations.setIfAbsent(key, String.valueOf(System.currentTimeMillis()), expire, TimeUnit.MILLISECONDS);
    }

    public void unLock(String key) {
        redisValueOperations.getOperations().delete(key);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取当天剩余的秒数
     *
     * @return
     */
    public static long currentDaySecond() {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除缓存
     *
     * @param keyList
     */
    public void delete(List<String> keyList) {
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }
        redisTemplate.delete(keyList);
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisValueOperations.get(key);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        Object json = this.get(key);
        if (json == null) {
            return null;
        }
        T obj = JSON.parseObject(json.toString(), clazz);
        return obj;
    }


    /**
     * 普通缓存放入
     */
    public void set(String key, String value) {
        redisValueOperations.set(key, value);
    }

    public void set(Object key, Object value) {
        String jsonString = JSON.toJSONString(value);
        redisValueOperations.set(key.toString(), jsonString);
    }

    /**
     * 普通缓存放入
     */
    public void set(String key, Object value, long second) {
        redisValueOperations.set(key, value, second, TimeUnit.SECONDS);
    }

    /**
     * 普通缓存放入并设置时间
     */
    public void set(Object key, Object value, long time) {
        String jsonString = JSON.toJSONString(value);
        if (time > 0) {
            redisValueOperations.set(key.toString(), jsonString, time, TimeUnit.SECONDS);
        } else {
            set(key.toString(), jsonString);
        }
    }

    //============================ map =============================
    public void mset(String key, String hashKey, Object value) {
        redisHashOperations.put(key, hashKey, value);
    }

    public Object mget(String key, String hashKey) {
        return redisHashOperations.get(key, hashKey);
    }
}
