package com.focustech.oss2008.dao;

import com.focustech.model.common.BaseEntity;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface BaseEntityDao<T extends BaseEntity> extends BaseHibernateDao<T> {

	public T select(BaseEntity baseEntity);
}
