package com.focustech.oss2008.dao;

public interface ResourceBookLockDao<T> extends BaseHibernateDao<T> {
    /**
     */
    public void lock(String webSite, String typeId, String content, boolean first) throws Exception;
}
