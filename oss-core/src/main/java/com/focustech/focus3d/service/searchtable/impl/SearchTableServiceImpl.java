package com.focustech.focus3d.service.searchtable.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.dao.searchtable.SearchTableDao;
import com.focustech.focus3d.model.searchtable.SearchTable;
import com.focustech.focus3d.service.searchtable.SearchTableService;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.impl.BaseEntityServiceImpl;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class SearchTableServiceImpl extends BaseEntityServiceImpl<SearchTable> implements SearchTableService<SearchTable> {

	@Autowired
	private SearchTableDao<SearchTable> searchTableDao;

	@Override
	public BaseHibernateDao<SearchTable> getEntityDao() {
		return searchTableDao;
	}

	@Override
	public void maintanceSearchUpdateTable(String bizTableName, Long bizTableRecordSn, String action) {

		searchTableDao.maintanceSearchDeleteTable(bizTableName, bizTableRecordSn, action);

	}
}
