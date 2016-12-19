package com.focustech.table.web.ext.format;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

public class ColumnFormatBeanProcessor implements JsonBeanProcessor {
    /**
     * Processes the input bean into a compatible JsDate.<br>
     */
    public JSONObject processBean(Object bean, JsonConfig jsonConfig) {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject().element("sex", "");
        return jsonObject;
    }
}
