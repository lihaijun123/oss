package com.focustech.oss2008.dao;

import java.util.List;
import java.util.Map;

/**
 * <li>關鍵詞信息管理Dao</li>
 *
 * @author jibin
 */
public interface KeywordDao<T> extends BaseHibernateDao<T> {
    /**
     * 根據funcId取得相應SQL並執行取得相應信息
     *
     * @param funcId
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getInfo(long funcId) throws Exception;

}
