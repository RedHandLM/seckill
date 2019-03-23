package com.imooc.seckill.redis;

/**
 * Created by liushichang on 2019/3/20.
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
