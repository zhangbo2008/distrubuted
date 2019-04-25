package com.hc.distributed.utils;

import java.util.regex.PatternSyntaxException;

public class UserVerfiyUtil extends VerifyUtil{
    /**
     * 账号长度限制，6-16
     */
    private static final String accountLength = "\\b([6-9]|1[0-6])\\b";

    /**
     * 密码长度限制，6-16
     */
    private static final String passwordLength = "\\b([6-9]|1[0-6])\\b";

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 判断邮箱格式
     * @param str 邮箱
     * @return 符合格式返回true
     */
    public static boolean isEmail(String str) throws PatternSyntaxException {
        String regExp = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/\n" +
                "/^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$/或\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return isFormatMatching(str, regExp);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     */
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        return isFormatMatching(str, regExp);
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    private static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        return isFormatMatching(str, regExp);
    }
}
