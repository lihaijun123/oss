package com.focustech.table.web.ext.format;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class JSonFormat implements DataFormat {
    public String format(Object data) {
        JSONArray jsonObject = JSONArray.fromObject(data);
        String jsonStr = jsonObject.toString();
        jsonStr = jsonStr.substring(jsonStr.indexOf("[") + 1, jsonStr.lastIndexOf("]"));
        return "(" + jsonStr + ")";
    }

    public String format(Object data, long count, JsonConfig jsonConfig) {
        StringBuffer sbf = new StringBuffer();
        JSONArray jsonObject = JSONArray.fromObject(data, jsonConfig);
        String jsonStr = jsonObject.toString();
        jsonStr = jsonStr.substring(jsonStr.indexOf("[") + 1, jsonStr.lastIndexOf("]"));
        sbf.append("({");
        sbf.append("\"results\":[");
        sbf.append(jsonStr);
        sbf.append("],");
        sbf.append("\"total\":");
        sbf.append(count);
        sbf.append("})");
        return sbf.toString();
    }

}
