package com.imooc.seckill.controller;

import com.imooc.seckill.domain.User;
import com.imooc.seckill.redis.RedisService;
import com.imooc.seckill.redis.UserKey;
import com.imooc.seckill.result.CodeMsg;
import com.imooc.seckill.result.Result;
import com.imooc.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;


    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("****hello world****");
    }


    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "liushi");
        System.out.println("请求成功");
        return "hello";
    }


    /**
     * 测试获取数据
     * @return
     */
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getUserById(1);
        return Result.success(user);
    }


    /**
     * 测试DB事务
     * @return
     */
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }


    /**
     * 测试RedisGet
     * @return
     */
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User v1=redisService.get(UserKey.getById,"1",User.class);
        return Result.success(v1);
    }

    /**
     * 测试RedisSet
     * @return
     */
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user=new User();
        user.setId(1);
        user.setName("11111");
        boolean result=redisService.set(UserKey.getById,"1",user);
        return Result.success(result);
    }




}
