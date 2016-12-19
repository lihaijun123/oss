package com.focustech.table.engine;

import javax.servlet.http.HttpServletRequest;

import com.focustech.table.web.ext.extcomp.TableParameters;

/**
 * <li>表格數據獲取服務</li>
 *
 * @author yangpeng 2008-5-5 上午10:07:45
 */
public interface RequestAnalyseEngine {

    /**
     * 取得列表數據
     *
     * @param start
     * @param limit
     * @param names
     * @param values
     * @return 列表數據
     */
    public String getData(String tableName, String andOr, int start, int limit, String[] names, String[] operas,
            String values[], String[] sortableNamesAndValues, HttpServletRequest request);

    public String getData(TableParameters tableParameters, HttpServletRequest request);

    /**
     * 取得列表的視圖,以HTML的形式
     */
    public String getTable(String tableName);

    public String getTable(TableParameters tableParameters);

}
