package com.focustech.oss2008.service;

import java.util.Map;

/**
 * <li>系統環境參數變量</li>
 *
 * @author taofucheng 2008-9-10 上午10:29:49
 */
public interface EnvironmentParameter {
    /**
     * 獲取邏輯型常量
     *
     * @return
     */
    public boolean getBooleanValue(String key);

    /**
     * 獲取所有環境變量
     *
     * @return
     */
    public Map<String, String> getEnvParam();

    /**
     * 獲取字符串型變量
     *
     * @param key 需要獲取的變量名
     * @return
     */
    public String getStringValue(String key);

    /**
     * 獲取整型變量
     *
     * @param key 需要獲取的變量名
     * @return 以整型方式返回指定的變量名，如果出錯，返回0
     */
    public int getIntValue(String key);

    /**
     * 獲取浮點型變量
     *
     * @param key 需要獲取的變量名
     * @return 以浮點型方式返回指定的變量名，如果出錯，返回0
     */
    public float getFloatValue(String key);

    /**
     * 獲取布爾型常量，為空或轉換異常時采用指定默認值
     *
     * @param key 關鍵字
     * @param defaultValue 默認值
     * @return
     */
    public boolean getBooleanValue(String key, boolean defaultValue);

    /**
     * 獲取字符串型環境常量，為空時采用指定的默認值
     *
     * @param key 關鍵字
     * @param defaultValue 默認值
     * @return
     */
    public String getStringValue(String key, String defaultValue);

    /**
     * 獲取整型變量，為空或轉換異常時采用指定默認值
     *
     * @param key 需要獲取的變量名
     * @param defaultValue 默認值
     * @return
     */
    public int getIntValue(String key, int defaultValue);

    /**
     * 獲取浮點型變量，為空或轉換異常時采用指定默認值
     *
     * @param key 需要獲取的變量名
     * @param defaultValue 默認值
     * @return
     */
    public float getFloatValue(String key, float defaultValue);
}
