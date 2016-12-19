package com.focustech.table.web.ext.extcomp;

public class SearchField implements SearchFieldIFace {
    /** 搜索條件中控件的名字 */
    protected String name;
    /** 數據庫中字段名 */
    protected String fieldName;
    protected String displayName;
    protected String columnName;
    protected String type;
    protected String defaultValue;
    protected String[] listValues;
    protected String oper = "equal";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String[] getListValues() {
        return listValues;
    }

    public void setListValues(String[] listValues) {
        this.listValues = listValues;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHTML() {
        return "";
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
