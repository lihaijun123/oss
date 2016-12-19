package com.focustech.oss2008.model;

import java.io.Serializable;
import java.util.List;

/**
 * <li>列表中的數據列</li>
 *
 * @author yangpeng 2008-5-4 上午10:46:03
 */
public class Column implements Serializable, Comparable<Column> {
    private static final long serialVersionUID = 4003887073194281418L;
    /** 數據庫中的字段名稱 */
    private String name;
    /** 是否顯示 */
    private boolean visible;
    /** 是否可以排序;true:可以排序;false:不可以排序;默認為true */
    private boolean sortable;
    /** 是否可以搜索;true:可以;false:不可以;默認為true */
    private boolean searchable;
    /**
     * 顯示的類型.input | image | link | list.
     * <p>
     * 默認為input,非必填
     */
    private String displayType;
    /** 字段類型. String int float image */
    private String type;
    /** 字段值的url */
    private String url;
    /** 位置,從1開始 */
    private int order;
    /**
     * 搜索類型.input | list.
     * <p>
     * 默認為input,非必填
     */
    private String searchType;
    /** 搜索條件集合 */
    private SearchCondition[] searchConditions;
    /** 列名鏈接 */
    private String nameUrl;
    /** 參數名︰parameter_key */
    private String parameterName;
    /** 所用參數 */
    private List<OssAdminParameter> parameter;
    /** 列寬 */
    private String width;
    /** 列的顯示名 */
    private String displayName;

    /** 是否為數據庫中字段 */
    private boolean fieldFlag = true;

    /** 外鍵引用 */
    private boolean foreignTableFlag;
    private String foreignTableName;
    private String foreignTableKey;
    private String foreignTableValue;

    public Column() {
    }

    public Column(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Column o) {
        if (null == o) {
            return 1;
        }
        else if (getOrder() == o.getOrder()) {
            if (null == getName()) {
                return -1;
            }
            else
                return getName().compareTo(o.getName());
        }
        else {
            return getOrder() - o.getOrder();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public SearchCondition[] getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(SearchCondition[] searchConditions) {
        this.searchConditions = searchConditions;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public List<OssAdminParameter> getParameter() {
        return parameter;
    }

    public void setParameter(List<OssAdminParameter> parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object obj) {
        // 不要將不同表格的列做比較
        if (obj instanceof Column) {
            if (null != obj) {
                Column defaultColumn;
                defaultColumn = (Column) obj;
                if (null != getName()) {
                    if (getName().equals(defaultColumn.getName())) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFieldFlag() {
        return fieldFlag;
    }

    public void setFieldFlag(boolean fieldFlag) {
        this.fieldFlag = fieldFlag;
    }

    public boolean isForeignTableFlag() {
        return foreignTableFlag;
    }

    public void setForeignTableFlag(boolean foreignTableFlag) {
        this.foreignTableFlag = foreignTableFlag;
    }

    public String getForeignTableName() {
        return foreignTableName;
    }

    public void setForeignTableName(String foreignTableName) {
        this.foreignTableName = foreignTableName;
    }

    public String getForeignTableKey() {
        return foreignTableKey;
    }

    public void setForeignTableKey(String foreignTableKey) {
        this.foreignTableKey = foreignTableKey;
    }

    public String getForeignTableValue() {
        return foreignTableValue;
    }

    public void setForeignTableValue(String foreignTableValue) {
        this.foreignTableValue = foreignTableValue;
    }

}
