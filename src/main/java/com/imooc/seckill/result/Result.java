package com.imooc.seckill.result;

import java.io.Serializable;

/**
 * Created by liushichang on 2019/3/12.
 */
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private T data;


    /**
     * 成功的时候调用的方法
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败的时候调用的方法
     *
     * @param codeMessage
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMessage) {
        return new Result<T>(codeMessage);
    }


    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMessage();
    }


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public T getData() {
        return data;
    }

}
