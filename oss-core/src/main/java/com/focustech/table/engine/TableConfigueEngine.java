package com.focustech.table.engine;

import com.focustech.oss2008.model.TableConfig;

/**
 * <li></li>
 *
 * @author MagicYang 2007-1-18 下午03:53:17 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public interface TableConfigueEngine {
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
