
package com.focustech.oss2008.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * <li>DAO支持類</li>
 * <p>
 * 子類通過繼承自動獲得SessionFactory
 * </p>
 *
 * @author yangpeng 2008-3-27 下午04:45:29 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@SuppressWarnings("unchecked")
public class OssHibernateDaoSupport<T> extends AbstractHibernateDaoSupport<T> {
    @Autowired
    @Qualifier("ossSessionFactory")
    private SessionFactory ossSessionFactory;

    /**
     * 取當前OSS Session
     */
    @Override
    public final Session getCurrentSession() {
        return ossSessionFactory.getCurrentSession();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return ossSessionFactory;
    }
}
