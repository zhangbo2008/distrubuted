package com.hc.hccommon.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Pattern;

public class VerifyUtil implements Serializable {

    /**
     * 字符串判空
     * @param str 字符串
     * @return 不为空返回true
     */
    public static boolean isNotEmpty(String str) {
        return (null != str && !str.equals(""));
    }

    public static boolean isNotEmpty(String... args) {
        for (String str : args) {
            if (null == str || str.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNull(String...arg) {
        for (String str : arg) {
            if (!checkNull(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对象判空
     * @param object 对象
     * @return 不为空返回true
     */
    public static boolean checkNull(Object object) {
        return null != object;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 匹配格式
     * @param message 传入信息
     * @param format 规定格式
     * @return 符合返回true，否则为false
     */
    static boolean isFormatMatching(String message, String format) {
        return isNotEmpty(message) && Pattern.compile(format).matcher(message).matches();
    }

    public static boolean checkGrouper(String str) {
        return isNotEmpty(str) && (str.equals("后台组") || str.equals("前端组") || str.equals("移动组")
                || str.equals("嵌入式组") || str.equals("手游组") || str.equals("设计组") || str.equals("数据挖掘组"));
    }

    public static boolean checkSex(String sex) {
        String regExp = "[\"男\",\"女\"]{1}";

        return isNotEmpty(sex) && isFormatMatching(regExp, sex);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T)object;
    }

}
