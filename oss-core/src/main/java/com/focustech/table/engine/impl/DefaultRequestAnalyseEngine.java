package com.focustech.table.engine.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.oss2008.model.TableConfig;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.table.engine.DataConstructEngine;
import com.focustech.table.engine.DataConstructEngineFactory;
import com.focustech.table.engine.RequestAnalyseEngine;
import com.focustech.table.engine.TableConfigueEngine;
import com.focustech.table.exception.NoSuchDataConstructEngineConfigurationException;
import com.focustech.table.exception.NoSuchTableConfigurationException;
import com.focustech.table.web.ext.extcomp.ExtGridComp;
import com.focustech.table.web.ext.extcomp.TableParameters;

/**
 * <li>用戶組件</li>
 *
 * @author yangpeng 2008-4-14 下午01:25:39
 */
public class DefaultRequestAnalyseEngine extends AbstractServiceSupport implements RequestAnalyseEngine {
    @Autowired
    OssAdminParameterService baseParametersService;
    /** 數據構造引擎工廠 */
    private DataConstructEngineFactory dataConstructEngineFactory;
    /** 默認的數據構造引擎,通過Ioc注入 */
    @Autowired
    private DataConstructEngine defaultDataConstructEngine;
    /** 表格配置引擎,通過Ioc注入 */
    @Autowired
    private TableConfigueEngine tableConfigueEngine;

    /**
     * 取得列表數據
     */
    public String getData(String tableName, String andOr, int start, int limit, String[] names, String[] operas,
            String[] values, String[] sortableNamesAndValues, HttpServletRequest request) {
        // 1.取得表格配置
        TableConfig configuration = getTableConfigueEngine().getTableConfig(tableName);
        // 2.取得表格構造引擎
        DataConstructEngine dataConstructEngine = getDataConstructEngine(configuration);
        if (null == dataConstructEngine) {
            throw new NoSuchDataConstructEngineConfigurationException("");
        }
        else {
            // 3.構造表格數據
            return dataConstructEngine.getData(configuration, tableName, andOr, start, limit, names, operas, values,
                    sortableNamesAndValues, request);
        }
    }

    public String getTable(String tableName) {
        // 1.取得表格配置
        TableConfigueEngine tableConfigueEngine = getTableConfigueEngine();
        if (null == tableConfigueEngine) {
            throw new NoSuchTableConfigurationException("");
        }
        TableConfig tableConfig = tableConfigueEngine.getTableConfig(tableName);
        if (tableConfig == null) {
            return "表格不存在";
        }
        ExtGridComp table = new ExtGridComp();
        table.setTableConfig(tableConfig);
        table.config();
        return table.getHTML();
    }

    public String getData(TableParameters tableParameters, HttpServletRequest request) {
        // 1.取得表格配置
        TableConfig configuration = getTableConfigueEngine().getTableConfig(tableParameters.getTableName());
        // 2.取得表格構造引擎
        DataConstructEngine dataConstructEngine = getDataConstructEngine(configuration);
        if (null == dataConstructEngine) {
            throw new NoSuchDataConstructEngineConfigurationException("");
        }
        else {
            // 3.構造表格數據
            return dataConstructEngine.getData(configuration, tableParameters.getTableName(), tableParameters
                    .getAndOr(), tableParameters.getStart(), tableParameters.getLimit(), tableParameters.getNames(),
                    tableParameters.getOperas(), tableParameters.getValues(), tableParameters
                            .getSortableNamesAndValues(), request);
        }
    }

    public String getTable(TableParameters tableParameters) {
        // 1.取得表格配置
        TableConfigueEngine tableConfigueEngine = getTableConfigueEngine();
        if (null == tableConfigueEngine) {
            throw new NoSuchTableConfigurationException("");
        }
        TableConfig tableConfig = tableConfigueEngine.getTableConfig(tableParameters.getTableName());
        if (tableConfig == null) {
            return "表格不存在";
        }
        ExtGridComp table = new ExtGridComp();
        table.setTableConfig(tableConfig);
        table.config();
        return table.getHTML();
    }

    public TableConfigueEngine getTableConfigueEngine() {
        return tableConfigueEngine;
    }

    public void setTableConfigueEngine(TableConfigueEngine tableConfigueEngine) {
        this.tableConfigueEngine = tableConfigueEngine;
    }

    public DataConstructEngineFactory getDataConstructEngineFactory() {
        return dataConstructEngineFactory;
    }

    /**
     * 取得數據構造引擎
     *
     * @param configuration 表格配置結構
     * @return 指定的數據構造引擎,如果未指定,則返回默認的數據構造引擎
     */
    protected DataConstructEngine getDataConstructEngine(TableConfig configuration) {
        DataConstructEngine dataConstructEngine = null;
        if (null == dataConstructEngineFactory) {
            log.info("no DataConstructEngineFactory be setted,use default DataConstructEngine.");
            return defaultDataConstructEngine;
        }
        dataConstructEngine =
                getDataConstructEngineFactory().findDataConstructEngine(configuration.getDataConstructEngineName());
        if (dataConstructEngine == null) {
            // 沒有配置數據構造引擎,返回默認的數據構造引擎
            dataConstructEngine = defaultDataConstructEngine;
        }
        return dataConstructEngine;
    }

    /**
     * @return the defaultDataConstructEngine
     */
    public DataConstructEngine getDefaultDataConstructEngine() {
        return defaultDataConstructEngine;
    }

    public void setDataConstructEngineFactory(DataConstructEngineFactory dataConstructEngineFactory) {
        this.dataConstructEngineFactory = dataConstructEngineFactory;
    }

    public void setDataConstructEngine(DataConstructEngine defaultDataConstructEngine) {
        this.defaultDataConstructEngine = defaultDataConstructEngine;
    }
}
