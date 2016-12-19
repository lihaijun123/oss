package com.focustech.oss2008.model;

/**
 * <li>列搜索條件</li>
 *
 * @author yangpeng 2008-5-4 上午11:04:52
 */
public class SearchCondition {
    /**
     * 搜索條件 key為一下值: equal, more , less, moreEqual, lessEqual, like
     */
    private String key;
    /** 顯示名稱 */
    private String displayName;
    private String listName;
    protected String defaultValue;
    private String value = "";

    // public static String getSQLOper(String key) {
    // String oper = "";
    // if(key.equals("equal")) {
    // oper = "=";
    // }else if(key.equals("more")) {
    // oper = ">";
    // }else if(key.equals("less")) {
    // oper = "<";
    // }else if(key.equals("moreEqual")) {
    // oper = ">=";
    // }else if(key.equals("lessEqual")) {
    // oper = "<=";
    // }else if(key.equals("like")) {
    // oper = "like";
    // }else if(key.equals("or")) {
    // oper = "or";
    // }
    //
    // return oper;
    // }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
