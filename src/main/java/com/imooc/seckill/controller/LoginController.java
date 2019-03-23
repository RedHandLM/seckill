package com.imooc.seckill.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.seckill.redis.RedisService;
import com.imooc.seckill.result.CodeMsg;
import com.imooc.seckill.result.Result;
import com.imooc.seckill.service.UserService;
import com.imooc.seckill.util.ValidatorUtil;
import com.imooc.seckill.vo.LoginVo;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登陆控制
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    private static final Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private com.imooc.seckill.service.MiaoShaUserService miaoShaUserService;


    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }


    @RequestMapping(value = "/do_login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info("login info json is {}", loginVo != null ? JSON.toJSONString(loginVo) : "empty");
        miaoShaUserService.login(response,loginVo);
        return Result.success(true);

    }


}
