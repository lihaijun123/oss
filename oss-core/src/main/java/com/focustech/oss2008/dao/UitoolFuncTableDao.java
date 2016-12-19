package com.focustech.oss2008.dao;
/**
 *
 * *
 * @author lihaijun
 *
 */
public interface UitoolFuncTableDao <T> extends BaseHibernateDao<T> {
	/**
	 *
	 * *
	 * @param funcId
	 */
	public void deleteByFuncId(long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 */
	public T selectByFuncId(long funcId);

	public String exportDeleteFuncTableSql(Long funcId);

}
