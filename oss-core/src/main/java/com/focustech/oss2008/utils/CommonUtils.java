package com.focustech.oss2008.utils;

public class CommonUtils {
    /**
     * 得到Double對象的值，為NULL時轉為0.0
     */
    public static double getDoubleValue(Double dou) {
        return dou == null ? 0.0 : dou.doubleValue();
    }

    /**
     * 得到Long對象的值，為NULL時轉為default值
     *
     * @param l Long值
     * @param defaultL default值
     */
    public static long getLongValueDefault(Long l, long defaultL) {
        return l == null ? defaultL : l.longValue();
    }
}
