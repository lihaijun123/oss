package com.focustech.focus3d.dao.searchtable;

import com.focustech.oss2008.dao.BaseHibernateDao;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface SearchTableDao<T> extends BaseHibernateDao<T> {
	public static final String SEARCH_TABLE_SUFFIX = "_search";
	/**
	 *
	 * *
	 * @param bizTableName
	 * @param bizTableRecordSn
	 * @param action
	 */
	public void maintanceSearchDeleteTable(String bizTableName, Long bizTableRecordSn, String action);

}
