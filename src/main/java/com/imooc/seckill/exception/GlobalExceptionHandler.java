package com.imooc.seckill.exception;

import com.imooc.seckill.result.CodeMsg;
import com.imooc.seckill.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理器
 * Created by liushichang on 2019/3/20.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 拦截指定参数
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            CodeMsg msg = globalException.getCodeMsg();
            return Result.error(msg);
        }
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> exceptions = bindException.getAllErrors();
            String defMessage = exceptions.get(0).getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defMessage));
        } else {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
