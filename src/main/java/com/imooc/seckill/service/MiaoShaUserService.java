package com.imooc.seckill.service;

import com.imooc.seckill.dao.MiaoshaUserDao;
import com.imooc.seckill.domain.MiaoshaUser;
import com.imooc.seckill.exception.GlobalException;
import com.imooc.seckill.redis.MiaoShaUserKey;
import com.imooc.seckill.redis.RedisService;
import com.imooc.seckill.result.CodeMsg;
import com.imooc.seckill.util.MD5Utils;
import com.imooc.seckill.util.UUIDUtils;
import com.imooc.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liushichang on 2019/3/20.
 */
@Service
public class MiaoShaUserService {


    private static final Logger log = LoggerFactory.getLogger(MiaoShaUserService.class);

    public static final String COOKIE_NAME_TOKEN = "token";


    @Autowired
    private MiaoshaUserDao miaoshaUserDao;
    @Autowired
    private RedisService redisService;


    /**
     * 通过用户ID获取用户信息
     *
     * @param id
     * @return
     */
    public MiaoshaUser getUserById(Long id) {
        return miaoshaUserDao.getById(id);
    }


    /**
     * 登陆功能
     *
     * @param loginVo
     * @return
     */
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.LOING_EMPTY);
        }
        MiaoshaUser user = getUserById(Long.valueOf(loginVo.getMobile()));
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        //验证密码
        String formPassToDbPass = MD5Utils.formPassToDbPass(loginVo.getPassword(), user.getSalt());
        log.info("formPassToDbPass is {},  Db pass is {}", formPassToDbPass, user.getPassword());
        if (!user.getPassword().equals(formPassToDbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtils.uuid();
        //设置用户token
        addCokieExpire(response, token, user);
        return true;
    }


    /**
     * 通过token获取用户
     *
     * @param token
     * @return
     */
    public MiaoshaUser getUserByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoShaUserKey.token, token, MiaoshaUser.class);
        if (user == null) {
            return null;
        }
        addCokieExpire(response, token, user);
        return user;
    }


    /**
     * 延长cokie使用期限
     *
     * @param response
     * @param user
     */
    private void addCokieExpire(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoShaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
