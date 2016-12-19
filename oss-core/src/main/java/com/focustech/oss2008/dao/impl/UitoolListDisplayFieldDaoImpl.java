package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UitoolListDisplayFieldDao;
import com.focustech.oss2008.model.UitoolListDisplayField;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class UitoolListDisplayFieldDaoImpl extends OssHibernateDaoSupport<UitoolListDisplayField> implements UitoolListDisplayFieldDao<UitoolListDisplayField> {

	@Override
	public List<UitoolListDisplayField> getListByFuncId(long funcId) {
		Criteria criteria = getCurrentSession().createCriteria(UitoolListDisplayField.class);
		criteria.add(Restrictions.eq("funcId", funcId));
		List<UitoolListDisplayField> list = criteria.list();
		return list;
	}

	@Override
	public void deleteListByFuncId(long funcId) {
		String hql = "delete from " + UitoolListDisplayField.class.getName() + " where funcId=" + funcId;
		getCurrentSession().createQuery(hql);
	}

	@Override
	public String exportDeleteListFieldSql(Long funcId) {
		return "delete from uitool_list_display_field where func_id=" + funcId + ";";
	}

}
