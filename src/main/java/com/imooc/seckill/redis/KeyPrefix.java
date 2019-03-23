package com.imooc.seckill.redis;

/**
 * Created by liushichang on 2019/3/20.
 */
public interface KeyPrefix {

    String getPrefix();

    int expireSeconds();


}
