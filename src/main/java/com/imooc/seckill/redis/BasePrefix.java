package com.imooc.seckill.redis;

/**
 * Created by liushichang on 2019/3/20.
 */
public abstract class BasePrefix implements   KeyPrefix {

    private int expireSeconds;
    private String prefix;



    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    @Override
    public String getPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+prefix;
    }

    @Override
    public int expireSeconds() {//默认永不过期
        return expireSeconds;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
}
