package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UitoolFuncDisplayInfoDao;
import com.focustech.oss2008.model.UitoolFuncDisplayInfo;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class UitoolFuncDisplayInfoDaoImpl extends OssHibernateDaoSupport<UitoolFuncDisplayInfo>  implements UitoolFuncDisplayInfoDao<UitoolFuncDisplayInfo>{

	@Override
	public List<UitoolFuncDisplayInfo> list() {
		Criteria criteria = getCurrentSession().createCriteria(UitoolFuncDisplayInfo.class);
		criteria.addOrder(Order.asc("funcId"));
		return criteria.list();
	}

	@Override
	public String exportDeleteListSql(Long funcId) {
		return "delete from uitool_func_display_info where func_id = " + funcId + ";";
	}

	@Override
	public UitoolFuncDisplayInfo selectByFuncId(long funcId) {
		Criteria criteria = getCurrentSession().createCriteria(UitoolFuncDisplayInfo.class);
		criteria.add(Restrictions.eq("funcId", funcId));
		return (UitoolFuncDisplayInfo) criteria.uniqueResult();
	}
}
