package com.imooc.seckill.util;

import java.util.UUID;

/**
 * Created by liushichang on 2019/3/20.
 */
public class UUIDUtils {

    /**
     * 生成UUID 用作分布式session的token
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
