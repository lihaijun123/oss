package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.OssAdminProxyUser;

public interface ProxyUserDao<T> extends BaseHibernateDao<T> {
    /**
     * 獲取所有代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> selectAllProxyuser();

    /**
     * 獲取還未到期的代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> selectUsefulProxyuser();

    /**
     * 獲取所有失效的代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> selectDisabledProxyuser();
}
