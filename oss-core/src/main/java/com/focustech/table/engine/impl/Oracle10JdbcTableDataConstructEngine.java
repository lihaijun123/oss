package com.focustech.table.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JsonConfig;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.focustech.oss2008.model.Column;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.model.TableConfig;
import com.focustech.table.engine.DataConstructEngine;
import com.focustech.table.web.ext.extcomp.ColumnFilter;
import com.focustech.table.web.ext.extcomp.SearchField;
import com.focustech.table.web.ext.extcomp.SearchPanel;
import com.focustech.table.web.ext.format.DataFormat;
import com.focustech.table.web.ext.format.JSonColumnFormat;
import com.focustech.table.web.ext.format.JSonFormat;

/**
 * <li>JDBC implementation of TableDataConstructEngine from oracle 10g</li>
 * <p>
 * the sql query will dynatic construct with the gaving configuration.
 * <p>
 * this sql will select columns from only one table.
 *
 * @see TableConfiguration
 * @author MagicYang 2007-4-9 下午02:03:08 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class Oracle10JdbcTableDataConstructEngine extends JdbcDaoSupport implements DataConstructEngine {
    private TableConfig tableConfig;
    public static final String HISTORY_SQL = "HISTORY_SQL";

    protected StringBuffer wrapperPagination(StringBuffer sql, TableConfig configuration) {
        return null;
    }

    public String getData(TableConfig tableConfig, String tableName, String andOr, int start, int limit,
            String[] aNames, String[] aOperas, String[] aValues, String[] sortableNamesAndValues,
            HttpServletRequest request) {
        if (andOr == null || andOr.equals("")) {
            request.getSession().setAttribute(HISTORY_SQL, new ArrayList());
        }
        this.tableConfig = tableConfig;
        DataFormat dataFormat = new JSonFormat();
        String str = "";
        ColumnFilter columnFilter = new ColumnFilter(tableConfig);
        // 總行數
        List<String[]> list = getHistorySQL(request);
        // 過濾掉空值
        String[] names = null;
        String[] operas = null;
        String[] values = null;
        List fiterList = new ArrayList();
        int len = 0;
        if (aValues != null) {
            len = aValues.length;
        }
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                if (aValues[i] != null && !aValues[i].equals("")) {
                    fiterList.add(i);
                }
            }
        }
        len = fiterList.size();
        names = new String[len];
        operas = new String[len];
        values = new String[len];
        Map map = this.getTableConfig().getSearchPanel().getNameFieldMap();
        for (int i = 0; i < len; i++) {
            String aName = aNames[(Integer) fiterList.get(i)];
            aName = (String) map.get(aName);
            names[i] = aName;
            operas[i] = aOperas[(Integer) fiterList.get(i)];
            values[i] = aValues[(Integer) fiterList.get(i)];
        }
        add2History(list, andOr, names, operas, values, sortableNamesAndValues);
        String sql = getSQL(tableConfig, list, start, limit, names, operas, values, sortableNamesAndValues);
        String sql2 = getCountSQL(tableConfig, list, names, operas, values, sortableNamesAndValues);
        List datas = getData(tableName, sql);
        long total = getTotalRoles(sql2);
        JsonConfig jsonConfig = new JsonConfig();
        JSonColumnFormat jSonColumnFormat = new JSonColumnFormat();
        jSonColumnFormat.setDatas(datas);
        // 需要格式化的列,以及參數
        List<Column> columns = tableConfig.getColumns();
        int smnSize = columns.size();
        for (int j = 0; j < datas.size(); j++) {
            Map rowData = (Map) datas.get(j);
            Map copyData = new HashMap();
            if (this.tableConfig.isDeleteFlag()) {
                rowData.put("deleteAction", "刪除");
            }
            if (this.tableConfig.isViewFlag()) {
                rowData.put("viewAction", "查看");
            }
            if (this.tableConfig.isViewFlag()) {
                rowData.put("modifyAction", "修改");
            }
            copyData.putAll(rowData);
            for (int i = 0; i < smnSize; i++) {
                Column cmn = columns.get(i);
                if (cmn.getParameter() != null) {
                    jSonColumnFormat.addParameter(cmn.getName(), cmn.getParameter());
                    jsonConfig.registerJsonValueProcessor(cmn.getName(), jSonColumnFormat);
                }
                // 格式化url
                if (cmn.getUrl() != null && !cmn.getUrl().equals("")) {
                    formatUrl(cmn, rowData, copyData, request);
                }
                // 添加隱藏列
                rowData.put(cmn.getName() + "-HIDDEN", copyData.get(cmn.getName()));
                // ///////////////////
                if (cmn.getType().equals(TableConfig.DATE_TYPE)) {
                    jsonConfig.registerJsonValueProcessor(cmn.getName(), jSonColumnFormat);
                }
                if (cmn.getType().equals(TableConfig.DATETIME_TYPE)) {
                    jsonConfig.registerJsonValueProcessor(cmn.getName(), jSonColumnFormat);
                }
            }
        }
        jsonConfig.setJsonPropertyFilter(columnFilter);
        str = dataFormat.format(datas, total, jsonConfig);
        return str;
    }

    public void formatUrl(Column cmn, Map map, Map copyData, HttpServletRequest request) {
        Object obj = copyData.get(cmn.getName());
        String url = getAddr(copyData, cmn, request);
        map.put(cmn.getName(), url);
    }

    public String getAddr(Map map, Column cmn, HttpServletRequest request) {
        String addr = cmn.getUrl();
        String url = "";
        if (addr.indexOf("~") > 0) {
            int left = addr.indexOf("~");
            int right = addr.lastIndexOf("~");
            String IDRef = addr.substring(left + 1, right);
            Object replaceObj = map.get(IDRef);
            String replaceID = (String) replaceObj.toString();
            String realAddr = addr.replace("~" + IDRef + "~", replaceID);
            url = "<div><a href='" + realAddr + "' target='_blank'>" + map.get(cmn.getName()) + "</a></div>";
        }
        else if (addr.indexOf("#") > 0) {
            int left = addr.indexOf("#");
            int right = addr.lastIndexOf("#");
            String IDRef = addr.substring(left + 1, right);
            String replaceID = request.getParameter(IDRef);
            if (replaceID == null || replaceID.equals("")) {
                replaceID = (String) request.getSession().getAttribute(IDRef);
            }
            if (replaceID == null || replaceID.equals("")) {
                replaceID = "";
            }
            String realAddr = addr.replace("#" + IDRef + "#", replaceID);
            url = "<div><a href='" + realAddr + "' target='_blank'>" + map.get(cmn.getName()) + "</a></div>";
        }
        return url;
    }

    public String getConditionSQL(List<String[]> list, String names[], String operas[], String values[],
            String[] sortableNamesAndValues) {
        StringBuffer sql = new StringBuffer();
        String tSQL = "";
        StringBuffer resultSQL = new StringBuffer();
        SearchPanel sp = tableConfig.getSearchPanel();
        ArrayList<SearchField> searchFields = sp.getSearchFields();
        int i = 0;
        if (names.length >= 1) {
            Column cmn = tableConfig.getColumnByName(names[0]);
            String type = cmn.getType();
            String subSql = " where " + names[i] + operas[i] + getSQLValue(values[i], type, operas[i]);
            sql.append(subSql);
            sql.append(" ");
        }
        for (i = 1; i < names.length; i++) {
            // SearchField searchField = searchFields.get(i);
            Column cmn = tableConfig.getColumnByName(names[i]);
            String type = cmn.getType();
            if (values[i] != null && !values[i].equals("")) {
                // todo
                String subSql = " and " + names[i] + operas[i] + getSQLValue(values[i], type, operas[i]);
                sql.append(subSql);
                sql.append(" ");
            }
        }
        String rslt = sql.toString();
        rslt = rslt.replace("where", " ");
        resultSQL.append("where ");
        tSQL = " 1=1 ";
        if (list != null) {
            int size = list.size();
            for (i = 0; i < list.size(); i++) {
                String[] strs = (String[]) list.get(i);
                String type = strs[0];
                String aSQL = strs[1];
                if (aSQL != null && !aSQL.trim().equals("")) {
                    // resultSQL.append(type + " " + "( " + aSQL + " )");
                    tSQL = "(" + tSQL + type + " " + "( " + aSQL + " )" + ") ";
                }
            }
        }
        resultSQL.append(tSQL);
        // 外鍵約束
        resultSQL.append(" " + getForeignCondition() + " ");
        // 條件設定
        if (sortableNamesAndValues != null) {
            int splitFlagIndex = -1;
            if (sortableNamesAndValues.length >= 1) {
                splitFlagIndex = sortableNamesAndValues[0].lastIndexOf("_");
            }
            if (splitFlagIndex > 0) {
                String sortName = "";
                String sortValue = "";
                sortName = sortableNamesAndValues[0].substring(0, splitFlagIndex);
                sortValue = sortableNamesAndValues[0].substring(splitFlagIndex + 1, sortableNamesAndValues[0].length());
                resultSQL.append(" order by " + sortName + "  " + sortValue + "  ");
            }
        }
        return resultSQL.toString();
    }

    public String add2History(List<String[]> list, String andOr, String names[], String operas[], String values[],
            String[] sortableNamesAndValues) {
        StringBuffer sql = new StringBuffer();
        StringBuffer tSQL = new StringBuffer();
        if (list == null) {
            return "";
        }
        int sqlSize = list.size();
        for (int j = 0; j < sqlSize; j++) {
            String[] array = (String[]) list.get(j);
            String type = array[0];
            String hisSQL = array[1];
        }
        SearchPanel sp = tableConfig.getSearchPanel();
        ArrayList<SearchField> searchFields = sp.getSearchFields();
        int i = 0;
        if (names != null && values != null && names.length > 0 && values.length > 0) {
            if (names.length >= 1) {
                Column cmn = tableConfig.getColumnByName(names[0]);
                String type = cmn.getType();
                String subSql = " " + names[i] + " " + operas[i] + " " + getSQLValue(values[i], type, operas[i]);
                sql.append(subSql);
                sql.append(" ");
            }
            for (i = 1; i < names.length; i++) {
                // SearchField searchField = searchFields.get(i);
                Column cmn = tableConfig.getColumnByName(names[i]);
                String type = cmn.getType();
                if (values[i] != null && !values[i].equals("")) {
                    // todo
                    String subSql =
                            " and " + names[i] + " " + operas[i] + " " + getSQLValue(values[i], type, operas[i]);
                    sql.append(subSql);
                    sql.append(" ");
                }
            }
        }
        String result = sql.toString();
        if (result != null && !result.trim().equals("")) {
            if (andOr == null || andOr.equals("")) {
                andOr = "and";
            }
            if (list.size() > 0) {
                String[] lastStr = (String[]) list.get(list.size() - 1);
                if (andOr.equals(lastStr[0]) && result.equals(lastStr[1])) {
                }
                else {
                    list.add(new String[]{andOr, result});
                }
            }
            else {
                list.add(new String[]{andOr, result});
            }
        }
        return sql.toString();
    }

    public String getPrefixSQL(String compNames[], String[] compValues) {
        StringBuffer sql = new StringBuffer();
        String names[] = tableConfig.getVisibleFieldNames();
        List<Column> columns = tableConfig.getVisibleFieldColumn();
        for (int i = 0; i < columns.size(); i++) {
            Column cmn = columns.get(i);
            String fieldName = cmn.getName();
            String tableName = tableConfig.getSchema() + "." + tableConfig.getDataSource();
            String bm = (String) this.tableConfig.getTableNameMap().get(tableName);
            if (cmn.isForeignTableFlag()) {
                tableName = cmn.getForeignTableName();
                bm = (String) this.tableConfig.getTableNameMap().get(tableName);
            }
        }
        sql.append("select ");
        int i = 0;
        sql.append("ROWNUM ROWNUM_ID, ");
        if (columns.size() >= 1) {
            Column cmn = columns.get(0);
            String fieldName = cmn.getName();
            String tableName = tableConfig.getSchema() + "." + tableConfig.getDataSource();
            String bm = (String) this.tableConfig.getTableNameMap().get(tableName);
            if (cmn.isForeignTableFlag()) {
                tableName = cmn.getForeignTableName();
                bm = (String) this.tableConfig.getTableNameMap().get(tableName);
            }
            sql.append(bm + "." + fieldName + " ");
            // sql.append(names[i] + " ");
        }
        if (columns.size() > 1) {
            for (i = 1; i < columns.size(); i++) {
                Column cmn = columns.get(i);
                String fieldName = cmn.getName();
                String tableName = tableConfig.getSchema() + "." + tableConfig.getDataSource();
                String bm = (String) this.tableConfig.getTableNameMap().get(tableName);
                if (cmn.isForeignTableFlag()) {
                    tableName = cmn.getForeignTableName();
                    bm = (String) this.tableConfig.getTableNameMap().get(tableName);
                }
                sql.append(", " + bm + "." + fieldName + " ");
                // sql.append(", " + names[i]);
            }
        }
        sql.append(" from ");
        sql.append(getTableSQL());
        // sql.append(tableConfig.getSchema() + "." + tableConfig.getDataSource() + " ");
        // 條件設定
        return sql.toString();
    }

    public String getSQL(TableConfig tableConfig, List<String[]> list, int start, int limit, String compNames[],
            String operas[], String[] compValues, String[] sortableNamesAndValues) {
        StringBuffer subSql = new StringBuffer();
        StringBuffer sql = new StringBuffer();
        String pageStr = " ROWNUM_ID <= " + (start + limit) + " and ROWNUM_ID >" + start + " ";
        subSql.append(this.getPrefixSQL(compNames, compValues));
        if (compNames != null && compValues != null) {
            subSql.append(this.getConditionSQL(list, compNames, operas, compValues, sortableNamesAndValues));
        }
        sql.append("select * from " + "(" + subSql.toString() + ") where ");
        sql.append(pageStr);
        return sql.toString();
    }

    public List<String[]> getHistorySQL(HttpServletRequest request) {
        List<String[]> list = (List<String[]>) request.getSession().getAttribute(HISTORY_SQL);
        if (list == null) {
            request.getSession().setAttribute(HISTORY_SQL, new ArrayList());
        }
        return list;
    }

    public String getCountSQL(TableConfig tableConfig, List<String[]> list, String compNames[], String operas[],
            String[] compValues, String[] sortableNamesAndValues) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) as total from " + getTableSQL() + "   ");
        if (compNames != null && compValues != null) {
            sql.append(this.getConditionSQL(list, compNames, operas, compValues, sortableNamesAndValues));
        }
        // 條件設定
        return sql.toString();
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public List<Map> getData(String tableName, String sql) {
        List data = null;
        data = this.getJdbcTemplate().queryForList(sql);
        return data;
    }

    public long getTotalRoles(String sql) {
        int total = 0;
        Long tl = this.getJdbcTemplate().queryForLong(sql);
        return tl.longValue();
    }

    public static String getSQLValue(String value, String type, String opera) {
        String result = "";
        if (type.equals(TableConfig.STRING_TYPE)) {
            if (opera.equals("LIKE")) {
                result = result + "'%";
                result = result + value;
                result = result + "%'";
            }
            else {
                result = result + "'";
                result = result + value;
                result = result + "'";
            }
        }
        else if (type.equals(TableConfig.DATE_TYPE)) {
            String date = "TO_DATE('" + value + "', '" + TableConfig.DATETIME_FORMAT + "') ";
            result = result + date;
        }
        else if (type.equals(TableConfig.DATETIME_TYPE)) {
            String date = "TO_DATE('" + value + "', 'yyyy-mm-dd hh24:mi:ss') ";
            result = result + date;
        }
        else {
            // TODO
            result = result + value;
        }
        return result;
    }

    private String getTableSQL() {
        StringBuffer sql = new StringBuffer();
        List tableNameList = tableConfig.getTableNameList();
        int size = tableNameList.size();
        if (size >= 1) {
            String tableName = (String) tableNameList.get(0);
            String bm = (String) this.tableConfig.getTableNameMap().get(tableName);
            sql.append(tableName + " " + bm + "   ");
        }
        if (size > 1) {
            for (int i = 1; i < size; i++) {
                String tableName = (String) tableNameList.get(i);
                String bm = (String) this.tableConfig.getTableNameMap().get(tableName);
                sql.append(", " + tableName + " " + bm + "   ");
            }
        }
        return sql.toString();
    }

    private String getForeignCondition() {
        StringBuffer sql = new StringBuffer();
        List<String> list = this.tableConfig.getTableNameList();
        List<Column> columns = this.tableConfig.getVisibleFieldColumn();
        Map map = this.tableConfig.getTableNameMap();
        for (int i = 0; i < columns.size(); i++) {
            Column cmn = columns.get(i);
            if (cmn.isForeignTableFlag()) {
                String key = cmn.getForeignTableKey();
                String value = cmn.getForeignTableValue();
                String name = cmn.getForeignTableName();
                String fbm = (String) map.get(name);
                String bm = (String) map.get(tableConfig.getSchema() + "." + tableConfig.getDataSource());
                sql.append("and " + bm + "." + cmn.getName() + "=" + fbm + "." + key);
            }
        }
        return sql.toString();
    }

    /** 將list轉為Map OssAdminParameter中的value做為key 而key做為value */
    public static Map getValueKeyMap(List<OssAdminParameter> list) {
        Map hm = new HashMap();
        int size = list.size();
        int i = 0;
        for (i = 0; i < size; i++) {
            OssAdminParameter OssAdminParameter = list.get(i);
            hm.put(OssAdminParameter.getParameterValue(), OssAdminParameter.getParameterKey());
        }
        return hm;
    }
}
