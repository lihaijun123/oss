package com.focustech.oss2008.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.focustech.oss2008.utils.GenericsUtils;

/**
 * <li>使用Hibernate的DAO支持类</li>
 * <p>
 * 一些公共方法
 * </p>
 *
 * @author yangpeng 2008-5-13 上午10:51:11
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDaoSupport<T> extends OssJdbcDaoSupport {
    /** DAO所管理的Entity類型. */
    protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass());

    /** 由子类实现得到当前Session的方法 */
    public abstract Session getCurrentSession();

    /** 取得当前类的Session Factory */
    public abstract SessionFactory getSessionFactory();

    public T select(Serializable id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public T pessimisticSelect(Serializable id) {
        return (T) getCurrentSession().get(entityClass, id, LockMode.UPGRADE);
    }

    public void insert(T t) {
        getCurrentSession().save(t);
        flush();
    }

    public void delete(T t) {
        getCurrentSession().delete(t);
        flush();
    }

    public void delete(Serializable id) {
        T t = select(id);
        if (null != t) {
            getCurrentSession().delete(t);
            flush();
        }
    }

    public void update(T t) {
        getCurrentSession().merge(t);
        flush();
    }

    public void insertOrUpdate(T t) {
        getCurrentSession().saveOrUpdate(t);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List<T> list(Class<T> clz){
    	Criteria c = getCurrentSession().createCriteria(clz.getName());
    	return c.list();
    }
    public List<T> list(){
    	Criteria c = getCurrentSession().createCriteria(entityClass.getName());
    	return c.list();
    }
}
