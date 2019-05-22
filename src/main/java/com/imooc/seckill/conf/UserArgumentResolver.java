package com.imooc.seckill.conf;

import com.alibaba.fastjson.JSON;
import com.imooc.seckill.domain.MiaoshaUser;
import com.imooc.seckill.service.MiaoShaUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于设置统一的Controller参数
 * Created by liushichang on 2019/3/20.
 */
@Service("userArgumentResolver")
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    Logger logger= LoggerFactory.getLogger(UserArgumentResolver.class);

    @Autowired
    private MiaoShaUserService miaoShaUserService;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        logger.info("http request url :{}",request.getRequestURI());
        logger.info("http request parameter :{}", JSON.toJSONString(request.getParameterMap()));
        String parameterToken = request.getParameter(MiaoShaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoShaUserService.COOKIE_NAME_TOKEN);


        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(parameterToken)) {
            return "login";
        }
        String token = StringUtils.isEmpty(cookieToken) ? parameterToken : cookieToken;
        MiaoshaUser miaoshaUser = miaoShaUserService.getUserByToken(response, token);
        return miaoshaUser;
    }

    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null||cookies.length<=0){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookieNameToken.equals(cookie.getName())) {
                logger.info("http request token     :   [{}]", cookie.getValue());
                return cookie.getValue();
            }
        }
        return null;
    }
}
