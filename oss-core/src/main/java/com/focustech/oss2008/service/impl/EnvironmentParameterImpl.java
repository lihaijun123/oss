package com.focustech.oss2008.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.focustech.oss2008.service.EnvironmentParameter;

public class EnvironmentParameterImpl implements EnvironmentParameter {
    private Map<String, String> envParam = new HashMap<String, String>();

    public Map<String, String> getEnvParam() {
        return envParam;
    }

    @SuppressWarnings("unchecked")
    public void setEnvParam(Map envParam) {
        this.envParam = envParam;
    }

    public float getFloatValue(String key) {
        float value = 0;
        try {
            value = Float.parseFloat(envParam.get(key));
        }
        catch (Exception e) {
            value = 0;
        }
        return value;
    }

    public int getIntValue(String key) {
        int value = 0;
        try {
            value = Integer.parseInt(envParam.get(key));
        }
        catch (Exception e) {
            value = 0;
        }
        return value;
    }

    public String getStringValue(String key) {
        return envParam.get(key);
    }

    public boolean getBooleanValue(String key) {
        boolean value = false;
        try {
            value = Boolean.parseBoolean(envParam.get(key));
        }
        catch (Exception e) {
            value = false;
        }
        return value;
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        boolean result = defaultValue;
        try {
            result = Boolean.parseBoolean(envParam.get(key));
        }
        catch (Exception e) {
            result = defaultValue;
        }
        return result;
    }

    public float getFloatValue(String key, float defaultValue) {
        float value = defaultValue;
        try {
            value = Float.parseFloat(envParam.get(key));
        }
        catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public int getIntValue(String key, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.parseInt(envParam.get(key));
        }
        catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    public String getStringValue(String key, String defaultValue) {
        String value = envParam.get(key);
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }
}
