package com.focustech.oss2008.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UitoolFuncTableDao;
import com.focustech.oss2008.model.UitoolFuncTable;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class UitoolFuncTableDaoImpl extends OssHibernateDaoSupport<UitoolFuncTable> implements UitoolFuncTableDao<UitoolFuncTable> {

	@Override
	public void deleteByFuncId(long funcId) {
		String hql = "delete from " + UitoolFuncTable.class.getName() + " as ut where ut.funcId=?";
		getCurrentSession().createQuery(hql).setLong(0, funcId).executeUpdate();
	}

	@Override
	public UitoolFuncTable selectByFuncId(long funcId) {
		Criteria criteria = getCurrentSession().createCriteria(UitoolFuncTable.class);
		criteria.add(Restrictions.eq("funcId", funcId));
		return (UitoolFuncTable) criteria.uniqueResult();
	}

	@Override
	public String exportDeleteFuncTableSql(Long funcId) {
		return "delete from uitool_func_table where func_id=" + funcId + ";";
	}

}
