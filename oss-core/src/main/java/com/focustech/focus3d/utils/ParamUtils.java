/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.focus3d.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.focustech.common.constant.OverallConst;
import com.focustech.common.utils.StringUtils;
/**
 * ParamUtils.java
 * 
 * @author xulijiao
 */
public class ParamUtils {
    private static final String PARAMETER_NAME_REGEX = "~!([a-zA-Z]+?)!~";
    private static Map<String, String> param = new HashMap<String, String>();

    /**
     * 将text中匹配到的参数名换成实际参数值<br>
     * <I>例如：</I> 如果对于字符串："欢迎~!(name)!~参加",参数：name="李四"， 需要将"~!(name)!~"换成"李四"，<BR>
     * 则先将参数：name="李四"放进map中，调用方法 put("name","李四")返回一个map<String,String> <BR>
     * 然后调用该方法：ReplaceParam("欢迎~!(name)!~参加", map)<BR>
     * <I>说明：</I>如果原字符串为null则返回null；如果参数map为null或者替换失败，则返回原字符串text.
     * 
     * @param text 需要匹配的字符串
     * @param param 传过来的参数map，key是匹配参数名，value对应匹配参数实际值
     * @return 将text中匹配到的参数名换成实际参数值后的字符串
     */
    public static String ReplaceParam(String text, Map<String, String> param) {
        if (StringUtils.isEmpty(text) || param == null) {
            return text;
        }
        Pattern paramRegexPattern = Pattern.compile(PARAMETER_NAME_REGEX);
        Matcher matcher = paramRegexPattern.matcher(text);
        while (matcher.find()) {
            String paramName = matcher.group(1);
            String paramValue = StringUtils.trimToEmpty(param.get(paramName));
            text = StringUtils.replace(text, matcher.group(), paramValue);
        }
        return text;
    }

    /**
     * @return 返回一个map
     */
    public static Map<String, String> getParamMap() {
        return param;
    }

    /**
     * 将键值对存入map中，并返回该map <br>
     * <I>例如：</I> 将键值对name="String1"存入map中,则可以调用该方法put( "name", "String1")
     * 
     * @param name 对应map的key
     * @param value 对应map的value
     * @return 返回该map
     */
    public static Map<String, String> put(String name, String value) {
        param.put(name, value);
        return param;
    }

    /**
     * 将字符串转换成List
     * 
     * @param str 用逗号拼接成的字符串
     * @return List对象
     * @author xulijiao
     */
    public static List<String> invokeStr2List(String str) {
        List<String> list = new ArrayList<String>();
        String trimStr = StringUtils.trimToEmpty(str);
        if (StringUtils.isEmpty(trimStr)) {
            return list;
        }
        String[] strArry = trimStr.split(OverallConst.SYMBOL_COMMA);
        list.addAll(Arrays.asList(strArry));
        return list;
    }

    /**
     * 将List转换成用逗号拼接成的字符串
     * 
     * @param list
     * @return 用逗号拼接成的字符串
     * @author xulijiao
     */
    public static String invokeList2Str(List<String> list) {
        if (list.isEmpty()) {
            return StringUtils.EMPTY;
        }
        String[] arry = list.toArray(new String[list.size()]);
        return StringUtils.concatElements(arry, OverallConst.SYMBOL_COMMA);
    }
}
