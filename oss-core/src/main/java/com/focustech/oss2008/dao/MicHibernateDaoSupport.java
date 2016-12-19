package com.focustech.oss2008.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * <li></li>
 *
 */
public class MicHibernateDaoSupport<T> extends AbstractHibernateDaoSupport<T> {
    //@Autowired
    //@Qualifier("micSessionFactory")
    private SessionFactory micSessionFactory;

    @Override
    public Session getCurrentSession() {
        return micSessionFactory.getCurrentSession();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return micSessionFactory;
    }
}
