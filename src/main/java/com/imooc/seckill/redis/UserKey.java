package com.imooc.seckill.redis;

/**
 * Created by liushichang on 2019/3/20.
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById=new UserKey("id");
    public static UserKey getByName=new UserKey("name");

}
