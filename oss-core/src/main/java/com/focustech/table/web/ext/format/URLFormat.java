package com.focustech.table.web.ext.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class URLFormat {
    public static final String splitFlag = "~";

    public static String[] getParamNames(String url) {
        String params[] = null;

        return params;
    }

    public static String getPrefix(String url) {
        String prefix = "";
        int index = url.indexOf("?");
        if (index == -1) {
            index = url.length();
        }
        prefix = url.substring(0, index);
        return prefix;
    }

    public static String getSufix(String url) {
        String sufix = "";
        int index = url.indexOf("?");
        if (index == -1) {
            index = url.length() - 1;
        }
        sufix = url.substring(index + 1, url.length());
        return sufix;
    }

    public static Map getParameterMap(String url) {
        Map hm = new HashMap();
        String sufix = getSufix(url);
        String subs[] = sufix.split("&");
        for (int i = 0; i < subs.length; i++) {
            String pm = subs[i];
            String[] nameValue = pm.split("=");
            if (nameValue.length == 2) {
                hm.put(nameValue[0], nameValue[1]);
            }
        }
        return hm;
    }

    public static String getUrl(String url) {
        StringBuffer aUrl = new StringBuffer();
        String prefix = getPrefix(url);
        aUrl.append(prefix + "?");
        Map<String, String> hm = getParameterMap(url);
        Set set = hm.keySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            String name = (String) itr.next();
            String value = hm.get(name);
            if (value.indexOf(splitFlag) == -1) {
                aUrl.append(name + "=" + value + "&");
            }
        }
        String result = aUrl.toString();
        int index = result.lastIndexOf("&");
        if (index == -1) {
            index = result.length() + 1;
        }
        return result.substring(0, index);
    }

    public static List getParameterName(String url) {
        Map hm = new HashMap();
        ArrayList list = new ArrayList();
        String sufix = getSufix(url);
        String subs[] = sufix.split("&");
        for (int i = 0; i < subs.length; i++) {
            String pm = subs[i];
            String[] nameValue = pm.split("=");
            if (nameValue.length == 2 && nameValue[1].indexOf(splitFlag) > -1) {
                list.add(nameValue[0]);
            }
        }

        return list;
    }

}
