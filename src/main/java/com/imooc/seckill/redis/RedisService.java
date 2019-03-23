package com.imooc.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by liushichang on 2019/3/20.
 */
@Service
public class RedisService {

    @Autowired
    private RedisPoolFacotry redisPoolFacotry;

    /**
     * 获取单个对象
     *
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFacotry.JedisPoolFeactory().getResource();
            String realKey = prefix.getPrefix() + key;
            String valueStr = jedis.get(realKey);
            T t = stringToBean(valueStr, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 判断key是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    public <T> boolean exist(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFacotry.JedisPoolFeactory().getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 增加值  原子操作
     *
     * @param prefix
     * @param key
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFacotry.JedisPoolFeactory().getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 减少值 原子操作
     *
     * @param prefix
     * @param key
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFacotry.JedisPoolFeactory().getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 设置值
     *
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = redisPoolFacotry.JedisPoolFeactory().getResource();
            String valueStr = beanToString(value);
            if (valueStr == null || valueStr.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int expireSeconds = prefix.expireSeconds();
            if (expireSeconds > 0) {
                jedis.setex(realKey, expireSeconds, valueStr);
            } else {
                jedis.set(realKey, valueStr);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == double.class || clazz == Double.class || clazz == int.class || clazz == Integer.class || clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }

    }


    private <T> T stringToBean(String valueStr, Class<T> clazz) {
        if (valueStr == null || valueStr.length() <= 0) {
            return null;
        }
        if (clazz == double.class || clazz == Double.class) {
            return (T) Double.valueOf(valueStr);
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(valueStr);
        }
        if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(valueStr);
        } else if (clazz == String.class) {
            return (T) valueStr;
        } else {
            return JSON.toJavaObject(JSON.parseObject(valueStr), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
