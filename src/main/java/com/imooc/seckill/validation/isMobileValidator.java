package com.imooc.seckill.validation;

import com.imooc.seckill.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.*;
import java.lang.annotation.*;

/**
 * 校验电话号码
 * Created by liushichang on 2019/3/20.
 */
public class isMobileValidator implements ConstraintValidator<isMobile, String> {


    private boolean required;

    @Override
    public void initialize(isMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
