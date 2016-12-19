package com.focustech.table.engine;

import javax.servlet.http.HttpServletRequest;

import com.focustech.oss2008.model.TableConfig;

/**
 * <li>數據構造引擎</li>
 *
 * @author MagicYang 2007-1-18 上午11:55:15 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public interface DataConstructEngine {

    /**
     * 取得列表數據
     *
     * @param start
     * @param limit
     * @param names
     * @param values
     * @return 列表數據
     */
    public String getData(TableConfig tableConfig, String tableName, String andOr, int start, int limit,
            String[] names, String[] operas, String values[], String[] sortableNamesAndValues,
            HttpServletRequest request);
}
