package com.focustech.oss2008.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.focustech.table.web.ext.extcomp.SearchComboxField;
import com.focustech.table.web.ext.extcomp.SearchDateField;
import com.focustech.table.web.ext.extcomp.SearchField;
import com.focustech.table.web.ext.extcomp.SearchPanel;
import com.focustech.table.web.ext.extcomp.SearchTextField;

/**
 * <li>列表對象</li>
 *
 * @author yangpeng 2008-4-30 下午02:48:05
 */
public class TableConfig implements Serializable {
    /** 字段類型是字符串 */
    public static final String STRING_TYPE = "String";
    /** 字段類型是整數 */
    public static final String INT_TYPE = "int";
    /** 字段類型是日期格式 */
    public static final String DATE_TYPE = "date";
    /** 字段類型是日期時間 */
    public static final String DATETIME_TYPE = "datetime";

    /** 搜索的控件類型是日期選擇框 */
    public static final String SEARCH_DATE_TYPE = "date";
    /** 搜索的控件類型是下拉列表 */
    public static final String SEARCH_LIST_TYPE = "list";

    /** 搜索的控件類型是文本框 */
    public static final String SEARCH_INPUT_TYPE = "input";

    public static final String DATE_FORMAT = "Y-m-d";
    public static final String DATETIME_FORMAT = "yyyy-mm-dd hh24:mi:ss";
    private static final long serialVersionUID = -7100634181328362674L;
    /** 默認數量為每頁20條 */
    public static final int DEFAULT_PAGE_DATA_SIZE = 20;
    /** 列表名稱,此為唯一標示 */
    private String name;
    /** 列表顯示名 */
    private String displayName;
    /** 數據庫schema */
    private String schema;
    /** 數據源 */
    private String dataSource;
    /** 默認每頁數據量 */
    private int pageDataSize = DEFAULT_PAGE_DATA_SIZE;
    /** 列表中的列 */
    private List<Column> columns;
    /** 列表數據的範圍條件 */
    private Map<String, String> dataScope;
    /** 授權可以使用的角色 */
    private List<String> authorizationRoles;
    /** 列表的長寬 */
    private int width;
    private int height;
    private String action = "/getTable.do?method=data";

    /** 批量刪除的列標志 */
    private String bulkDeleteField;
    private boolean deleteFlag = false;
    private String deleteUrl;
    private boolean viewFlag = false;
    private String viewUrl;
    private boolean modifyFlag = false;
    private String modifyUrl;
    private boolean bulkDeleteFlag = false;
    private String bulkDeleteUrl;

    /** 所有表名和別名 */
    private Map tableNameMap = new HashMap();
    private List tableNameList = new ArrayList();
    /** 當前的別名索引 */
    private char bmIndex = 65;

    /**
     * 表格數據構造引擎,如果系統中設置了默認的數據構造引擎則該項為非必填.
     * <p>
     * 目前準備配置以下幾個數據構造引擎:
     * <p>
     * jdbc_sql | jdbc_table |hibernate_object | hiberante_hql | ibatis_sql.
     * <p>
     * 默認為jdbc_table
     */
    private String dataConstructEngineName;

    /**
     * 驗證角色是否被授權可訪問此列表
     *
     * @param roleName 角色名稱
     */
    public boolean isAuthorizationRole(String roleName) {
        return authorizationRoles.contains(roleName);
    }

    /** 取得字段名數組 */
    public String[] getFieldsNames() {
        ArrayList<String> nameArray = new ArrayList();
        String fieldNames[] = null;
        List<Column> columns = this.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            String fieldName = columns.get(i).getName();
            if (columns.get(i).isVisible()) {
                nameArray.add(fieldName);
            }
        }
        fieldNames = new String[nameArray.size()];
        for (int i = 0; i < nameArray.size(); i++) {
            fieldNames[i] = (String) nameArray.get(i);
        }
        return fieldNames;
    }

    public String[] getVisibleFieldsNames() {
        ArrayList<String> nameArray = new ArrayList();
        String fieldNames[] = null;
        List<Column> columns = this.getVisibleColumn();
        for (int i = 0; i < columns.size(); i++) {
            String fieldName = columns.get(i).getName();

            nameArray.add(fieldName);

        }
        fieldNames = new String[nameArray.size()];
        for (int i = 0; i < nameArray.size(); i++) {
            fieldNames[i] = (String) nameArray.get(i);
        }
        return fieldNames;
    }

    /** 取得可見字段 */
    public String[] getVisibleFieldNames() {
        ArrayList<String> nameArray = new ArrayList();
        String fieldNames[] = null;
        List<Column> columns = this.getVisibleFieldColumn();
        for (int i = 0; i < columns.size(); i++) {
            String fieldName = columns.get(i).getName();

            nameArray.add(fieldName);

        }
        fieldNames = new String[nameArray.size()];
        for (int i = 0; i < nameArray.size(); i++) {
            fieldNames[i] = (String) nameArray.get(i);
        }
        return fieldNames;
    }

    /** 判斷某字段是否存在于列表中 */
    public boolean hasFieldName(String fieldName, String fieldNames[]) {
        boolean flag = false;
        int i = 0;
        for (i = 0; i < fieldNames.length; i++) {
            if (fieldNames[i].equals(fieldName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /** 取得可見字段 */
    public List<Column> getVisibleColumn() {
        ArrayList<Column> visibleColumns = new ArrayList();
        int i = 0;
        for (i = 0; i < this.columns.size(); i++) {
            Column cmn = columns.get(i);
            if (cmn.isVisible()) {
                visibleColumns.add(cmn);
            }
        }
        return visibleColumns;
    }

    public List<Column> getVisibleFieldColumn() {
        ArrayList<Column> visibleColumns = new ArrayList();
        int i = 0;
        for (i = 0; i < this.columns.size(); i++) {
            Column cmn = columns.get(i);
            if (cmn.isVisible() && cmn.isFieldFlag()) {
                visibleColumns.add(cmn);
            }
        }
        return visibleColumns;
    }

    public ArrayList getSearchFieldNames() {
        ArrayList searchFieldNames = new ArrayList();
        List<Column> visibleColumns = getVisibleColumn();
        int size = visibleColumns.size();
        int i = 0;
        for (i = 0; i < size; i++) {
            Column cmn = columns.get(i);
            if (cmn.isSearchable()) {
                searchFieldNames.add(cmn.getName());
            }
        }
        return searchFieldNames;
    }

    public Column getColumnByName(String name) {
        Column cmn = null;
        int i = 0;
        for (i = 0; i < this.columns.size(); i++) {
            Column cmn2 = this.columns.get(i);
            if (name.equals(cmn2.getName())) {
                cmn = cmn2;
                break;
            }
        }
        return cmn;
    }

    /** 將數組對像與visibleFieldNames組裝成Map對象 */
    private Map getMap(Object[] objs) {
        HashMap hm = new HashMap();
        String[] fieldNames = this.getVisibleFieldsNames();
        int i = 0;
        for (i = 0; i < this.getVisibleColumn().size(); i++) {
            hm.put(fieldNames[i], objs[i + 1]);
        }
        return hm;
    }

    private Map getMap(Object obj) {
        HashMap hm = new HashMap();
        String[] fieldNames = this.getVisibleFieldsNames();
        hm.put(fieldNames[0], obj);
        return hm;
    }

    public List<Map> getDataList(List list) {
        int size = list.size();
        List data = new ArrayList();
        int i = 0;
        for (i = 0; i < size; i++) {
            Map map = null;
            System.out.print(list.get(i).getClass());
            if (this.getVisibleFieldsNames().length > 1) {
                Object[] objs = (Object[]) list.get(i);
                map = this.getMap(objs);
            }
            else {
                Object obj = (Object) list.get(i);
                map = this.getMap(obj);
            }
            data.add(map);
        }
        return data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public int getPageDataSize() {
        return pageDataSize;
    }

    public void setPageDataSize(int pageDataSize) {
        this.pageDataSize = pageDataSize;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Map<String, String> getDataScope() {
        return dataScope;
    }

    public void setDataScope(Map<String, String> dataScope) {
        this.dataScope = dataScope;
    }

    public List<String> getAuthorizationRoles() {
        return authorizationRoles;
    }

    public void setAuthorizationRoles(List<String> authorizationRoles) {
        this.authorizationRoles = authorizationRoles;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public SearchPanel getSearchPanel() {
        SearchPanel searchPanel = new SearchPanel();
        searchPanel.setAction(this.action);
        int i = 0;
        int size = columns.size();
        for (i = 0; i < size; i++) {
            Column cmn = columns.get(i);
            if (cmn.isSearchable()) {
                if (cmn.getSearchType().equals("list")) {
                    // String parameterName = cmn.getParameterName();
                    // List list = baseParametersService.selectParameters(parameterName);
                    // cmn.setParameter(list);
                }
                List<SearchField> list = getSearchField(cmn);
                int fieldSize = list.size();
                int j = 0;
                for (j = 0; j < fieldSize; j++) {
                    SearchField searchField = list.get(j);;
                    searchPanel.addSearchField(searchField);
                }
            }
        }
        return searchPanel;
    }

    private List<SearchField> getSearchField(Column cmn) {
        List<SearchField> list = new ArrayList();
        SearchField searchField = null;
        if (cmn.isSearchable()) {
            SearchCondition[] searchConditions = cmn.getSearchConditions();
            int len = searchConditions.length;
            String header = cmn.getDisplayName();
            String name = cmn.getName();
            String searchType = cmn.getSearchType();
            int i = 0;
            for (i = 0; i < len; i++) {
                SearchCondition searchCondition = cmn.getSearchConditions()[i];
                String displayName = searchCondition.getDisplayName();
                if (searchType.equals("input")) {
                    searchField = new SearchTextField(20);
                    searchField.setDisplayName(displayName);
                    searchField.setType("text");
                    searchField.setDefaultValue("");
                    searchField.setOper(searchCondition.getKey());
                    searchField.setFieldName(name);
                    searchField.setName(name + "-" + searchCondition.getKey());
                    searchField.setDefaultValue(searchCondition.getDefaultValue());
                }
                else if (searchType.equals("list")) {
                    searchField = new SearchComboxField(cmn.getParameter());
                    searchField.setDisplayName(displayName);
                    searchField.setType("text");
                    searchField.setDefaultValue("");
                    searchField.setOper(searchCondition.getKey());
                    searchField.setFieldName(name);
                    searchField.setName(name + "-" + searchCondition.getKey());
                    searchField.setDefaultValue(searchCondition.getDefaultValue());
                }
                else if (searchType.equals("date")) {
                    searchField = new SearchDateField();
                    searchField.setDisplayName(displayName);
                    searchField.setType("date");
                    searchField.setDefaultValue("");
                    searchField.setOper(searchCondition.getKey());
                    searchField.setFieldName(name);
                    searchField.setName(name + "-" + searchCondition.getKey());
                    searchField.setDefaultValue(searchCondition.getDefaultValue());
                }

                searchField.setFieldName(name + "_" + i);
                searchField.setColumnName(name);
                list.add(searchField);
            }
        }
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TableConfig) {
            if (null != obj) {
                TableConfig defaultTableConfig;
                defaultTableConfig = (TableConfig) obj;
                if (null != getName()) {
                    if (getName().equals(defaultTableConfig.getName())) {
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

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDataConstructEngineName() {
        return dataConstructEngineName;
    }

    public void setDataConstructEngineName(String dataConstructEngineName) {
        this.dataConstructEngineName = dataConstructEngineName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public boolean isViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }

    public boolean isModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(boolean modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getModifyUrl() {
        return modifyUrl;
    }

    public void setModifyUrl(String modifyUrl) {
        this.modifyUrl = modifyUrl;
    }

    public boolean isBulkDeleteFlag() {
        return bulkDeleteFlag;
    }

    public void setBulkDeleteFlag(boolean bulkDeleteFlag) {
        this.bulkDeleteFlag = bulkDeleteFlag;
    }

    public String getBulkDeleteUrl() {
        return bulkDeleteUrl;
    }

    public void setBulkDeleteUrl(String bulkDeleteUrl) {
        this.bulkDeleteUrl = bulkDeleteUrl;
    }

    public String getBulkDeleteField() {
        return bulkDeleteField;
    }

    public void setBulkDeleteField(String bulkDeleteField) {
        this.bulkDeleteField = bulkDeleteField;
    }

    public void configAction() {
        if (this.isViewFlag()) {
            Column cmn = new Column();
            cmn.setDisplayName("查看");
            cmn.setType("action");
            cmn.setSortable(false);
            cmn.setSearchable(false);
            cmn.setUrl(this.getViewUrl());
            cmn.setVisible(true);
            cmn.setFieldFlag(false);
            cmn.setName("viewAction");
            this.columns.add(cmn);
        }
        if (this.isModifyFlag()) {
            Column cmn = new Column();
            cmn.setDisplayName("修改");
            cmn.setType("action");
            cmn.setSortable(false);
            cmn.setSearchable(false);
            cmn.setUrl(this.getViewUrl());
            cmn.setVisible(true);
            cmn.setFieldFlag(false);
            cmn.setName("modifyAction");
            this.columns.add(cmn);
        }
        if (this.isDeleteFlag()) {
            Column cmn = new Column();
            cmn.setDisplayName("刪除");
            cmn.setType("action");
            cmn.setSortable(false);
            cmn.setSearchable(false);
            cmn.setUrl(this.getViewUrl());
            cmn.setVisible(true);
            cmn.setFieldFlag(false);
            cmn.setName("deleteAction");
            this.columns.add(cmn);
        }
    }

    public void configHiddenColumns() {
        List<Column> columns = getVisibleFieldColumn();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            Column cmn = columns.get(i);
            Column cmnHidden = new Column();
            cmnHidden.setDisplayName(cmn.getDisplayName());
            cmnHidden.setType("hidden");
            cmnHidden.setSortable(false);
            cmnHidden.setSearchable(false);
            cmnHidden.setVisible(true);
            cmnHidden.setFieldFlag(false);
            cmnHidden.setName(cmn.getName() + "-HIDDEN");
            this.columns.add(cmnHidden);
        }
    }

    public String getBulkDeleteParamName() {
        String aUrl = this.getBulkDeleteUrl();
        String paramName = "";
        if (aUrl.indexOf("~") > 0) {
            int left = aUrl.indexOf("?");
            int right = aUrl.indexOf("=");
            paramName = aUrl.substring(left + 1, right);
        }
        return paramName;
    }

    public void configTableNames() {
        List<Column> columns = getVisibleFieldColumn();
        int size = columns.size();
        int i = 0;
        addTableName(this.getSchema() + "." + this.getDataSource());
        for (i = 0; i < size; i++) {
            Column cmn = columns.get(i);
            if (cmn.isForeignTableFlag()) {
                addTableName(cmn.getForeignTableName());
            }
        }
    }

    private void addTableName(String name) {
        if (!tableNameMap.containsValue(name)) {
            this.tableNameMap.put(name, new String(new char[]{this.bmIndex++}));
            this.tableNameList.add(name);
        }
    }

    public Map getTableNameMap() {
        return tableNameMap;
    }

    public void setTableNameMap(Map tableNameMap) {
        this.tableNameMap = tableNameMap;
    }

    public List getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List tableNameList) {
        this.tableNameList = tableNameList;
    }

}
