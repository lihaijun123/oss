package com.focustech.oss2008.service;

import java.util.List;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface UitoolListDisplayFieldService<T> {
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public List<T> getListByFuncId(long funcId);
	/**
	 *
	 * *
	 * @param t
	 */
	public void insertOrUpdate(T t);
}
