package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.UitoolListDisplayField;

/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface UitoolListDisplayFieldDao<T> extends BaseHibernateDao<T> {
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public List<UitoolListDisplayField> getListByFuncId(long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 */
	public void deleteListByFuncId(long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public String exportDeleteListFieldSql(Long funcId);
}
