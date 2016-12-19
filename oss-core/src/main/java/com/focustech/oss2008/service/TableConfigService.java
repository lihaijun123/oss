package com.focustech.oss2008.service;

import com.focustech.oss2008.model.TableConfig;

/**
 * <li>表格服務</li>
 *
 * @author yangpeng 2008-5-4 下午05:16:15
 */
public interface TableConfigService {
    /**
     * 初始化
     */
    public void init();

    /**
     * 根據配置名稱取配置信息
     *
     * @param name 配置名稱
     */
    public TableConfig getTableConfig(String name);

    /**
     * 刷新所有緩存
     * <p>
     * 注意同步
     * </p>
     */
    public void refreshAll();
}
