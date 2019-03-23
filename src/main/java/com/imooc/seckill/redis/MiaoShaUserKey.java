package com.imooc.seckill.redis;

/**
 * Created by liushichang on 2019/3/20.
 */
public class MiaoShaUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;


    private MiaoShaUserKey(int tokenExpire, String prefix) {
        super(tokenExpire, prefix);
    }

    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE, "tk");

}
