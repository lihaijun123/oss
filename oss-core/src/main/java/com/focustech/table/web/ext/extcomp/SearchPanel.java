package com.focustech.table.web.ext.extcomp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.focustech.oss2008.model.TableConfig;

public class SearchPanel {
    /*
     * action: 一個url 例如 /user.do?methd=list
     */
    private String action = "";
    private ArrayList searchFields = new ArrayList();
    private final String seachFunctionName = "_getCondition";
    private String tableName;

    /** 搜索條件中name的取值:> */
    public static final String SEARCHCONDITION_VALUE_MORE = "m";
    /** 搜索條件中name的取值:< */
    public static final String SEARCHCONDITION_VALUE_LESS = "l";
    /** 搜索條件中name的取值:>= */
    public static final String SEARCHCONDITION_VALUE_MORE_EQUAL = "me";
    /** 搜索條件中name的取值:<= */
    public static final String SEARCHCONDITION_VALUE_LESS_EQUAL = "le";
    /** 搜索條件中name的取值:> */
    public static final String SEARCHCONDITION_VALUE_EQUAL = "e";
    /** 搜索條件中name的取值:String */
    public static final String SEARCHCONDITION_VALUE_SRING = "str";
    /** 搜索條件中name的取值:數字 */
    public static final String SEARCHCONDITION_VALUE_NUM = "n";
    /** 搜索條件中name的取值:like */
    public static final String SEARCHCONDITION_VALUE_LIKE = "like";

    /** 面板是否隱藏 */
    private boolean hidden = false;

    /** 時間格式 */

    public ArrayList getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(ArrayList searchFields) {
        this.searchFields = searchFields;
    }

    public void addSearchField(SearchField searchField) {
        this.searchFields.add(searchField);
    }

    public String parseField(SearchField searchField) {
        String str = searchField.getHTML();
        return str;
    }

    private String getSortableField() {
        StringBuffer html = new StringBuffer();
        html.append("<input type='hidden' name='sortableNamesAndValue' id = 'sortableNamesAndValue' value = 'sdd'>");
        return html.toString();
    }

    public String getHTML() {
        if (this.hidden) {
            return "";
        }
        StringBuffer html = new StringBuffer();
        String str = "";
        int i = 0;
        html.append("<form name = '" + tableName + "' action = '" + this.action + "'>");
        html.append("<table border='0'>");
        int len = searchFields.size();
        for (i = 0; i < len; i++) {
            html.append("<tr>");
            html.append("<td>");
            html.append(parseField((SearchField) searchFields.get(i)));
            html.append("</td>");
            html.append("</tr>");
        }
        html.append("<tr>");
        html.append("<td>");
        html.append(this.getSortableField());
        html.append("</td>");
        html.append("</tr>");
        // 分頁
        html.append("<tr>");
        html.append("<td>");
        html
                .append("<SELECT NAME='countPerPage' style = 'display:none'><OPTION VALUE='20' SELECTED>20<OPTION VALUE='30'>30<OPTION VALUE='50'>50</SELECT>");
        html.append("</td>");
        html.append("</tr>");
        // 與或條件
        html.append("<tr>");
        html.append("<td>");
        html
                .append("<SELECT NAME='andOr'><OPTION VALUE='' SELECTED> <OPTION VALUE=''>   <OPTION VALUE='and'>與<OPTION VALUE='or'>或</SELECT>");
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>");
        String action = "<input type='button' onclick='" + this.seachFunctionName + "();' value = '搜索'>";
        html.append(action);
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</form>");
        return html.toString();
    }

    /** 取得控件名字與列名的名字值對 */
    public Map getNameFieldMap() {
        Map map = new HashMap();
        List fields = this.getSearchFields();
        int len = fields.size();
        for (int i = 0; i < len; i++) {
            SearchField searchField = (SearchField) fields.get(i);
            map.put(searchField.getFieldName(), searchField.getColumnName());
        }
        return map;
    }

    public String getValidateFunction() {
        StringBuffer html = new StringBuffer();

        return html.toString();
    }

    public String getSearchFunction(int cuntsPerPage, String gridName) {
        StringBuffer html = new StringBuffer();
        html
                .append(""
                        + gridName
                        + ".getStore().on('beforeload', function(){Ext.apply(this.baseParams,{'tableName':'"
                        + this.getTableName()
                        + "', 'names':fieldSearchNames, 'operas':fieldOperaValues, 'values':fieldSearchValues, 'sortableNamesAndValues':sortableNamesAndValues, 'andOr':document.forms['"
                        + this.tableName + "']['andOr'].value, 'countPerPage':document.forms['" + this.tableName
                        + "']['countPerPage'].value});});");
        html.append("\r\n");
        html.append("function " + this.seachFunctionName + "() {");
        html.append("\r\n");
        html.append("fieldSearchValues = new Array();");
        html.append("\r\n");
        html.append("fieldOperaValues = new Array();");
        html.append("var i = fieldSearchNames.length;");
        html.append("\r\n");
        html.append("for(i=0; i<fieldSearchNames.length; i++) {");
        html.append("\r\n");
        html.append("fieldSearchValues.push(document.forms['" + this.tableName + "'][fieldSearchNames[i]].value);");
        html.append("\r\n");
        html.append("}");
        html.append("\r\n");
        html.append("i = fieldOperaNames.length;");
        html.append("\r\n");
        html.append("for(i=0; i<fieldSearchNames.length; i++) {");
        html.append("\r\n");
        html.append("//alert(Ext.get(fieldOperaNames[i]).getValue());");
        html.append("\r\n");
        html.append("fieldOperaValues.push(document.forms['" + this.tableName + "'][fieldOperaNames[i]].value);");
        html.append("\r\n");
        html.append("}");
        html.append("\r\n");
        html.append("" + gridName + ".bbar.pageSize=document.forms['" + this.tableName
                + "']['countPerPage'].value;\r\n");
        html.append("" + gridName + ".getStore().load({params:{tableName:'" + this.getTableName()
                + "', start:0, limit:" + cuntsPerPage + "}});");
        html.append("\r\n");
        html.append("return fieldSearchValues;");
        html.append("\r\n");
        html.append("}");
        html.append("\r\n");
        List dateNames = getSearchDateNameList();
        int size = dateNames.size();
        html.append("Ext.onReady(function() {");
        for (int i = 0; i < size; i++) {
            html.append("new Ext.form.DateField({name: '" + dateNames.get(i) + "',	width:120,	renderTo:"
                    + dateNames.get(i) + ",	allowBlank:false,format:'" + TableConfig.DATE_FORMAT + "'});");
        }
        html.append("});");
        return html.toString();
    }

    /*
     * 以JS數組的形式。
     */
    public String getFieldNames() {
        String fieldNames = "var fieldSearchNames = [";
        int len = searchFields.size();
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len - 1; i++) {
            searchField = (SearchField) searchFields.get(i);
            fieldNames = fieldNames + "'" + searchField.getFieldName() + "',";
        }
        if (len >= 1) {
            searchField = (SearchField) searchFields.get(i);
            fieldNames = fieldNames + "'" + searchField.getFieldName() + "'";
        }
        fieldNames = fieldNames + "]";
        return fieldNames;
    }

    /*
     * 以JS數組的形式。
     */
    public String getFieldOperaNames() {
        String fieldOperaNames = "var fieldOperaNames = [";
        int len = searchFields.size();
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len - 1; i++) {
            searchField = (SearchField) searchFields.get(i);
            fieldOperaNames = fieldOperaNames + "'" + searchField.getName() + "',";
        }
        if (len >= 1) {
            searchField = (SearchField) searchFields.get(i);
            fieldOperaNames = fieldOperaNames + "'" + searchField.getName() + "'";
        }
        fieldOperaNames = fieldOperaNames + "]";
        return fieldOperaNames;
    }

    /*
     * 以數組的形式。
     */
    public String[] getSearchFieldNames() {
        int len = searchFields.size();
        String[] fieldNames = new String[len];
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len; i++) {
            searchField = (SearchField) searchFields.get(i);
            fieldNames[i] = searchField.getFieldName();
        }
        return fieldNames;
    }

    /* 取得各個字段的初始值， 默認為空，用于條件查詢 */
    public String getSearchFieldValues() {
        String fieldValues = "var fieldSearchValues = [";
        int len = searchFields.size();
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len - 1; i++) {
            searchField = (SearchField) searchFields.get(i);
            fieldValues = fieldValues + "'" + searchField.getDefaultValue() + "',";
        }
        if (len >= 1) {
            searchField = (SearchField) searchFields.get(i);
            fieldValues = fieldValues + "'" + searchField.getDefaultValue() + "'";
        }
        fieldValues = fieldValues + "];\r\n";
        fieldValues = fieldValues + getfieldOperaValues();
        fieldValues = fieldValues + getSortValues();
        return fieldValues;
    }

    /* 取得各個字段的初始值， 默認為空，用于條件查詢 */
    public String getfieldOperaValues() {
        String fieldValues = "var fieldOperaValues = [";
        int len = searchFields.size();
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len - 1; i++) {
            searchField = (SearchField) searchFields.get(i);
            fieldValues = fieldValues + "'" + "" + "',";
        }
        if (len >= 1) {
            searchField = (SearchField) searchFields.get(i);
            fieldValues = fieldValues + "'" + "" + "'";
        }
        fieldValues = fieldValues + "];\r\n";
        return fieldValues;
    }

    public String getSortValues() {
        String sortValues = "var sortableNamesAndValues = [];";
        return sortValues;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List getSearchDateNameList() {
        List aList = new ArrayList();
        int len = searchFields.size();
        int i = 0;
        SearchField searchField = null;
        for (i = 0; i < len; i++) {
            searchField = (SearchField) searchFields.get(i);

            if (searchField.getType().equals(TableConfig.SEARCH_DATE_TYPE)) {
                aList.add(searchField.getFieldName());
            }
        }
        return aList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
