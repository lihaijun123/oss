package com.focustech.oss2008.service.impl;

import java.io.Serializable;
import java.util.List;

import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.BaseEntityService;
/**
 *
 * *
 * @author lihaijun
 *
 */
public abstract class BaseEntityServiceImpl<T> extends AbstractServiceSupport implements BaseEntityService<T> {

	public abstract BaseHibernateDao<T> getEntityDao();

	@Override
	public void insert(T t) {
		getEntityDao().insert(t);
	}

	@Override
	public void update(T t) {
		getEntityDao().update(t);
	}

	@Override
	public void insertOrUpdate(T t) {
		getEntityDao().insertOrUpdate(t);
	}


	@Override
	public void delete(Serializable id) {
		getEntityDao().delete(id);
	}

	@Override
	public void delete(T t) {
		getEntityDao().delete(t);
	}

	@Override
	public T select(Serializable id) {
		return getEntityDao().select(id);
	}

	@Override
	public List<T> list() {
		return getEntityDao().list();
	}
	
}
