package com.focustech.oss2008.model;

/**
 * 用戶自定義Portlet
 *
 * @author pangyihong
 */
public class OssAdminPortlet implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2055864409706769130L;

    /** 空值 */
    public static final String EMPTY_VALUE = "null";

    /** 根節點ID */
    public static final String ROOT_ID = "000";

    /** 容器類型︰簡單容器 */
    public static final String CONTAINER_TYPE_SIMPLE = "1";

    /** 容器類型︰自定義容器 */
    public static final String CONTAINER_TYPE_ADVANCED = "2";

    /** 配置項︰功能 */
    public static final String ITEM_TYPE_FUNCTION = "1";

    /** 配置項︰隊列 */
    public static final String ITEM_TYPE_QUEUE = "2";

    /** Portlet類型︰功能 */
    public static final String PORTLET_TYPE_FUNCTION = "1";

    /** Portlet類型︰隊列 */
    public static final String PORTLET_TYPE_QUEUE = "2";

    /** Portlet類型︰高級功能 */
    public static final String PORTLET_TYPE_ADVANCED_FUNCTION = "3";

    /** Portlet類型︰高級隊列 */
    public static final String PORTLET_TYPE_ADVANCED_QUEUE = "4";

    /** Function Portlet類型︰用戶自定義提醒 */
    public static final String USER_DEFINED_REMIND = "1";

    /** Function Portlet類型︰客戶聯系信息提醒 */
    public static final String ACCOUNT_CONTACT_REMIND = "2";

    /** Function Portlet類型︰最新銷售提成信息 */
    public static final String SALE_SPLIT_INFO = "3";

    /** 列數類型︰2列 */
    public static final String COLUMN_TYPE_TWO = "1";

    /** 列數類型︰3列 */
    public static final String COLUMN_TYPE_THREE = "2";

    /** 重置首頁標志︰是 */
    public static final String RESET_FLAG_TRUE = "1";

    // Fields
    private Long portletId;
    private String userId;
    private String queueId;
    private String includeQueueId;
    private Long rowIndex;
    private Long columnIndex;
    private String queueName;
    private String portletType;
    private String portletTitle;
    private String columnType;

    public Long getPortletId() {
        return portletId;
    }

    public void setPortletId(Long portletId) {
        this.portletId = portletId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getIncludeQueueId() {
        return includeQueueId;
    }

    public void setIncludeQueueId(String includeQueueId) {
        this.includeQueueId = includeQueueId;
    }

    public Long getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Long rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Long getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Long columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getPortletType() {
        return portletType;
    }

    public void setPortletType(String portletType) {
        this.portletType = portletType;
    }

    public String getPortletTitle() {
        return portletTitle;
    }

    public void setPortletTitle(String portletTitle) {
        this.portletTitle = portletTitle;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

}
