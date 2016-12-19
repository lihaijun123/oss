package com.focustech.focus3d.service.searchtable;

import com.focustech.oss2008.service.BaseEntityService;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface SearchTableService<T> extends BaseEntityService<T>{
	

	/**
	 *维护搜索表和delete表关系
	 *
	 * *
	 * @param bizTableName 业务表名称
	 * @param searchTableSnName 搜索表记录SN
	 * @param action
	 */
	public void maintanceSearchUpdateTable(String bizTableName, Long bizTableRecordSn, String action);
}
