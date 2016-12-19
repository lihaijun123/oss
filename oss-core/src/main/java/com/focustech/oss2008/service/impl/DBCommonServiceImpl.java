package com.focustech.oss2008.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.model.common.BaseEntity;
import com.focustech.oss2008.dao.BaseEntityDao;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.DBCommonService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class DBCommonServiceImpl extends BaseEntityServiceImpl<BaseEntity>  implements DBCommonService<BaseEntity> {
	@Autowired
	private BaseEntityDao<BaseEntity> baseEntityDao;

	@Override
	public BaseEntity get(BaseEntity baseEntity) {
		return baseEntityDao.select(baseEntity);
	}

	@Override
	public BaseHibernateDao<BaseEntity> getEntityDao() {
		return baseEntityDao;
	}

	@Override
	public void insertOrUpdate(BaseEntity t) {
		getEntityDao().insertOrUpdate(t);
	}
}
