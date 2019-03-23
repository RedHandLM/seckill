package com.imooc.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liushichang on 2019/3/20.
 */
@Service
public class RedisPoolFacotry {

    @Autowired
    private RedisConfig redisConfig;


    @Bean
    public JedisPool JedisPoolFeactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxTotal(redisConfig.getPoolMaxTotal());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(config,
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getTimeOut() * 1000,
                redisConfig.getPassword(), 0);
        return jedisPool;
    }


}
