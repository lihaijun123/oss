package com.focustech.table.web.ext.extcomp;

import java.util.ArrayList;
import java.util.List;

import com.focustech.oss2008.model.Column;
import com.focustech.oss2008.model.SearchCondition;
import com.focustech.oss2008.model.Table;
import com.focustech.oss2008.model.TableConfig;
import com.focustech.table.web.ext.format.DataFormat;
import com.focustech.table.web.ext.format.URLFormat;

public class ExtGridComp extends Table {
    /*
     * 對象的格式轉換器，用于將對象轉換為各種格式，諸如xml,json, javascript數組等等。
     */
    private DataFormat dataFormat;
    /*
     * 列名 格式如下︰ 'id', 'name', 'title'
     */
    private String fieldNames;
    /*
     * 列的顯示名 格式如下: {header: 'ID', width: 120, dataIndex: 'id', sortable: true}, {header: '名字', width: 180, dataIndex:
     * 'name', sortable: true}, {header: '標題', width: 115, dataIndex: 'title', sortable: true}
     */
    private String displayName;
    /*
     * 搜索條件 1. name type default listValue for example user_name text michael
     */
    public ArrayList actions = new ArrayList();
    private String storeName = "store";
    private String selectionModelName = "sm";
    private String gridName = "grid";

    public ExtGridComp() {
        long id = System.currentTimeMillis();
        this.storeName = this.storeName + "_" + id;
        this.selectionModelName = this.selectionModelName + "_" + id;
        this.gridName = this.gridName + "_" + id;
    }

    public void addSearchField(SearchField searchField) {
        this.searchPanel.addSearchField(searchField);
    }

    /*
     * 取得HTML字符串
     */
    public String getBodyHTML() {
        StringBuffer html = new StringBuffer();
        if (tableConfig.isBulkDeleteFlag()) {
            String url = URLFormat.getUrl(tableConfig.getBulkDeleteUrl());
            List paramNames = URLFormat.getParameterName(tableConfig.getBulkDeleteUrl());
            String paramName = "";
            if (paramNames.size() > 0) {
                paramName = (String) paramNames.get(0);
            }
            html.append("function deleteRow() {");
            html.append("\r\n");
            html.append("var selecteds = " + gridName + ".getSelectionModel().getSelections();");
            html.append("\r\n");
            html.append("var paramsValues = new Array();");
            html.append("\r\n");
            html.append("for(i=0; i<selecteds.length; i++) {");
            html.append("\r\n");
            html.append("paramsValues.push(selecteds[i].get('" + this.tableConfig.getBulkDeleteField() + "-HIDDEN'));");
            html.append("\r\n");
            html.append("}");
            html.append("\r\n");
            html.append("if(paramsValues.length==0){paramsValues=null;};\r\n");
            html.append("Ext.Ajax.request({");
            html.append("\r\n");
            html.append("method : 'POST',");
            html.append("\r\n");
            html.append("url : '" + url + "',");
            html.append("\r\n");
            html.append("params :{" + paramName + ":paramsValues},");
            html.append("\r\n");
            html.append("success : function(response) {");
            html.append("\r\n");
            html.append("var x = response.responseText;");
            html.append("\r\n");
            html.append("alert(x);");
            html.append("\r\n");
            html.append("}");
            html.append("\r\n");
            html.append("})");
            html.append("\r\n");
            html.append("}");
            html.append("\r\n");
        }
        html.append("var " + this.getStoreName() + " = new Ext.data.Store({");
        html.append("\r\n");
        html.append("url: '" + this.getAction() + "'");
        html.append("\r\n");
        html.append(",reader: new Ext.data.JsonReader({");
        html.append("\r\n");
        html.append("root: 'results',");
        html.append("\r\n");
        html.append("totalProperty: 'total',");
        html.append("\r\n");
        html.append("id: 'rolddeId'");
        html.append("\r\n");
        html.append("}, [");
        html.append("\r\n");
        html.append(this.getFieldNames());
        html.append("\r\n");
        html.append("])");
        html.append("\r\n");
        html.append("});");
        html.append("\r\n");
        html.append("var " + this.selectionModelName + " = new Ext.grid.CheckboxSelectionModel();");
        html.append("\r\n");
        html.append("var " + gridName + " = new Ext.grid.GridPanel({");
        html.append("\r\n");
        html.append("store: " + this.getStoreName() + ",");
        html.append("\r\n");
        html.append(" columns: [");
        html.append("\r\n");
        html.append("new Ext.grid.RowNumberer(),");
        html.append("\r\n");
        html.append("" + selectionModelName + ",");
        html.append("\r\n");
        html.append(this.getHeaderDisplay());
        html.append("],");
        html.append("\r\n");
        html.append("renderTo:'example-grid',");
        html.append("\r\n");
        if (this.getWidth() == 0) {
            html.append("autoWidth:true,");
        }
        else {
            html.append("width:" + this.getWidth() + ",");
        }
        html.append("\r\n");
        if (this.getHeight() == 0) {
            html.append("autoHeight:true,");
        }
        else {
            html.append("height:" + this.getHeight() + ",");
        }
        html.append("\r\n");
        html.append("sm:" + selectionModelName + ",");
        html.append("\r\n");
        html.append(" bbar: new Ext.PagingToolbar({");
        html.append("\r\n");
        html.append(" pageSize: " + this.getCountsPerPage() + ",");
        html.append("\r\n");
        html.append("store: " + this.getStoreName() + ",");
        html.append("\r\n");
        html.append("displayInfo: true,");
        html.append("\r\n");
        html.append("displayMsg: '從{0}到{1}條記錄  共:{2}條',");
        html.append("\r\n");
        html.append("emptyMsg: 'No topics to display',");
        html.append("\r\n");
        html.append("items:[");
        html.append("\r\n");
        html.append(" '-', {");
        html.append("\r\n");
        html.append("text: '批量刪除',");
        html.append("\r\n");
        html.append("cls: 'x-btn-text-icon details',");
        html.append("\r\n");
        html.append("handler: function(){deleteRow();}");
        html.append("\r\n");
        html.append(" }]");
        html.append("\r\n");
        html.append("})");
        html.append("\r\n");
        html.append("});");
        html.append("\r\n");
        html.append("" + gridName + ".addListener('cellclick', cellclick);");
        html.append("\r\n");
        html.append("function cellclick(" + gridName + ", rowIndex, columnIndex, e) {");
        html.append("\r\n");
        html.append("var record = " + gridName + ".getStore().getAt(rowIndex);   //Get the Record");
        html.append("\r\n");
        html.append("var fieldName = " + gridName + ".getColumnModel().getDataIndex(columnIndex);  //Get field name");
        html.append("\r\n");
        html.append("var data = record.get(fieldName);");
        html.append("\r\n");
        html.append("}");
        html.append("\r\n");
        html.append("" + gridName + ".getStore().load({params:{tableName:'" + this.getTableConfig().getName()
                + "', start:0, limit:" + this.getCountsPerPage() + "}});");
        html.append("\r\n");
        html.append("function doAdd(){");
        html.append("\r\n");
        html.append("alert(1);");
        html.append("\r\n");
        html.append("\r\n");
        html.append("}");
        html.append("\r\n");
        return html.toString();
    }

    public String getHTML() {
        StringBuffer html = new StringBuffer();

        html.append("<div id = 'container'></div>\r\n");
        // html.append("<div>" + this.getSearchPanelHTML() + "</div>\r\n");
        html.append("<div id='example-grid' style='width:100%'></div>\r\n");
        html.append("<script>\r\n");

        html.append("new Ext.Panel({\r\n");
        html.append("title: '搜索',\r\n");
        html.append("collapsible:true,\r\n");
        html.append("renderTo: 'container',\r\n");
        html.append("width:600,\r\n");
        html.append("html: \"" + "<div>" + this.getSearchPanelHTML() + "</div>" + "\"");
        html.append("});\r\n");

        html.append(this.getBodyHTML());
        html.append(this.getSearchPanel().getValidateFunction());
        html.append(this.getSearchFieldNames() + ";\r\n");
        html.append(this.getSearchFieldValues() + "\r\n");
        html.append(this.getFieldOperaNames() + ";\r\n");
        html.append(this.getSearchFunction() + ";\r\n");
        html.append("function sortChange(extgrid, info) {");
        html.append("sortableNamesAndValues = new Array();");
        html.append("sortableNamesAndValues.push(info.field+'_'+info.direction);");
        html.append("_getCondition();");
        html.append("}");
        html.append("" + this.gridName + ".addListener('sortchange', sortChange);");
        html.append("</script>\r\n");
        return html.toString();
    }

    public String getNoConditionHTML() {
        StringBuffer html = new StringBuffer();
        html.append("<div id='example-grid' style='width:100%'></div>\r\n");
        html.append("<script>\r\n");
        html.append(this.getBodyHTML());
        html.append("</script>\r\n");
        return html.toString();
    }

    public String getSearchFunction() {
        return this.searchPanel.getSearchFunction(this.getCountsPerPage(), this.gridName);
    }

    /*
     * 取得各個搜索字段，以js數組的形式返回
     */
    public String getSearchFieldNames() {
        return this.searchPanel.getFieldNames();
    }

    /*
     * 以JS數組的形式。
     */
    public String getFieldOperaNames() {
        return this.searchPanel.getFieldOperaNames();
    }

    public String getSearchFieldValues() {
        return this.searchPanel.getSearchFieldValues();
    }

    public String getSearchOperaValues() {
        return this.searchPanel.getfieldOperaValues();
    }

    public DataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(DataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getHeaderDisplay() {
        StringBuffer header = new StringBuffer();
        header.append(getDisplayName());
        return header.toString();
    }

    public String getSearchPanelHTML() {
        String str = this.searchPanel.getHTML();
        return str;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAction() {
        return this.tableConfig.getAction();
    }

    public void config() {
        this.setDisplayName(tableConfig.getDisplayName());
        StringBuffer fieldNames = new StringBuffer();
        List<Column> allolumns = tableConfig.getColumns();
        List<Column> visibleColumns = tableConfig.getVisibleColumn();
        List<Column> columns = visibleColumns;
        int size = columns.size();
        int i = 0;
        for (i = 0; i < size - 1; i++) {
            Column cmn = columns.get(i);
            String name = "'" + cmn.getName() + "'";
            fieldNames.append(name + ",");
        }
        if (size >= 1) {
            Column cmn = columns.get(size - 1);
            String name = "'" + cmn.getName() + "'";
            fieldNames.append(name);
        }
        this.setFieldNames(fieldNames.toString());
        // displayName for ext grid
        StringBuffer displayName = new StringBuffer();
        for (i = 0; i < size - 1; i++) {
            Column cmn = columns.get(i);
            String header = "'" + cmn.getDisplayName() + "'";
            String width = cmn.getWidth();
            String dataIndex = "'" + cmn.getName() + "'";
            String hidden = "false";
            if (cmn.getType() == "hidden") {
                hidden = "true";
            }
            String menuDisabled = "" + !cmn.isSortable();
            String dsp =
                    "{header: " + header + ", width: " + width + ", dataIndex: " + dataIndex + ",hidden:" + hidden
                            + ", menuDisabled:" + menuDisabled + ", sortable:" + cmn.isSortable() + "},";
            displayName.append(dsp);
        }
        if (size >= 1) {
            Column cmn = columns.get(size - 1);
            String header = "'" + cmn.getDisplayName() + "'";
            String width = cmn.getWidth();
            String dataIndex = "'" + cmn.getName() + "'";
            String hidden = "false";
            if (cmn.getType() == "hidden") {
                hidden = "true";
            }
            String menuDisabled = "" + !cmn.isSortable();
            String dsp =
                    "{header: " + header + ", width: " + width + ", dataIndex: " + dataIndex + ",hidden:" + hidden
                            + ", menuDisabled:" + menuDisabled + ", sortable:" + cmn.isSortable() + "}";
            displayName.append(dsp);
        }
        this.setDisplayName(displayName.toString());
        // set countsPerPage
        this.setCountsPerPage(tableConfig.getPageDataSize());
        // width and height
        this.setWidth(tableConfig.getWidth());
        this.setHeight(tableConfig.getHeight());
        if (this.tableConfig.getSearchPanel() != null) {
            this.setSearchPanel(this.tableConfig.getSearchPanel());
            this.searchPanel.setTableName(tableConfig.getName());
        }
    }

    public SearchField getSearchField(Column cmn) {
        SearchField searchField = null;
        if (cmn.isSearchable()) {
            SearchCondition[] searchConditions = cmn.getSearchConditions();
            int len = searchConditions.length;
            String header = cmn.getDisplayName();
            String name = cmn.getName();
            String searchType = cmn.getSearchType();
            int i = 0;
            for (i = 0; i < len; i++) {
                SearchCondition searchCondition = cmn.getSearchConditions()[0];
                String displayName = searchCondition.getDisplayName();
                if (searchType.equals(TableConfig.SEARCH_INPUT_TYPE)) {
                    searchField = new SearchTextField(20);
                    searchField.setName(name);
                    searchField.setDisplayName(displayName);
                    searchField.setType("text");
                    searchField.setDefaultValue("");
                    searchField.setOper(searchCondition.getKey());
                }
                else {
                }
            }
        }
        return searchField;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSelectionModelName() {
        return selectionModelName;
    }

    public void setSelectionModelName(String selectionModelName) {
        this.selectionModelName = selectionModelName;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }
}
