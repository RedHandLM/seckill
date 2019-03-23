package com.imooc.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码生成工具类
 * Created by liushichang on 2019/3/20.
 */
public class MD5Utils {

    /**
     * MD5
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }


    /**
     * 固定盐
     */
    private static final String SALT = "1a2b3c4d";

    /**
     * 用户输入生成form密码
     *
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        System.out.println(SALT.charAt(0));
        System.out.println(SALT.charAt(2));
        System.out.println("---------------");

        String str = SALT.charAt(0)+"" + SALT.charAt(2)+"" + inputPass + SALT.charAt(5)+"" + SALT.charAt(4)+"";
        System.out.println("-----"+str);
        return md5(str);
    }


    /**
     * 表单密码生成DB密码
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDbPass(String formPass, String salt) {
        String str = salt.charAt(0)+"" + salt.charAt(2)+"" + formPass + salt.charAt(5)+"" + salt.charAt(4)+"";
        return md5(str);
    }



    /**
     * 用户输入直接生成DB密码
     *
     * @param inputPass
     * @param dbSalt
     * @return
     */
    public static String inputPassToDbPass(String inputPass, String dbSalt) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass, dbSalt);
        return dbPass;
    }


    /**
     * 12123456c3
     * 用户提交的密码：   d3b1294a61a07da9b49b6e22b2cbd7f9
     * @param args
     */
    public static void main(String[] args) {
        //ce21b747de5af71ab5c2e20ff0a60eea    第一次加盐
        //System.out.println("用户提交密码是===>>>>"+inputPassToFormPass("123456"));
        //System.out.println("数据库密码是===>>>>"+formPassToDbPass("d3b1294a61a07da9b49b6e22b2cbd7f9",SALT));
        System.out.println("数据库密码是:"+inputPassToDbPass("111111",SALT));//0687f9701bca74827fcefcd7e743d179


        //System.out.println(md5("12123456c3"));








    }


}
