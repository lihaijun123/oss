package com.focustech.oss2008.dao;

import java.util.List;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface UitoolFuncDisplayInfoDao<T> extends BaseHibernateDao<T>  {
	/**
	 *
	 * *
	 * @return
	 */
	public List<T> list();
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public T selectByFuncId(long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public String exportDeleteListSql(Long funcId);
}
