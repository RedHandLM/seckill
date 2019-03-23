package com.imooc.seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liushichang on 2019/3/20.
 */
public class ValidatorUtil {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    /**
     * 校验手机号码
     * @param string
     * @return
     */
    public static boolean isMobile(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        Matcher matcher = MOBILE_PATTERN.matcher(string);
        return matcher.matches();
    }


    public static void main(String[] args) {
        System.out.println(isMobile("186017r9660"));
    }


}
