package com.imooc.seckill.result;

import com.sun.javafx.binding.StringFormatter;

import java.io.Serializable;

/**
 * Created by liushichang on 2019/3/12.
 */
public class CodeMsg implements Serializable {
    private int code;
    private String message;


    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常： %s");
    //登陆模块5002xx
    public static CodeMsg LOING_EMPTY = new CodeMsg(500200, "登陆信息不能为空");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500201, "密码不能为空");
    public static CodeMsg PHONE_EMPTY = new CodeMsg(500202, "手机不能为空");
    public static CodeMsg PHONE_ERROR = new CodeMsg(500203, "手机号码有误");
    public static CodeMsg USER_NOT_EXIST = new CodeMsg(500204, "用户不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500205, "密码错误");
    //商品模块5003xx

    //订单模块5004xx

    //秒杀模块5005xx


    public CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMsg fillArgs(Object...args) {
        int code = this.code;
        String msg=String.format(this.message,args);
        return new CodeMsg(code,msg);
    }




    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }




}
