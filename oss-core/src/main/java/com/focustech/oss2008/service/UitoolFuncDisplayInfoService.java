package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.model.UitoolFuncDisplayInfo;



/**
 *
 * *
 * @author lihaijun
 *
 */
public interface UitoolFuncDisplayInfoService<T> {
	/**
	 *
	 * *
	 * @return
	 */
	public List<T> list();
	/**
	 *
	 * @param funcId
	 */
	public void saveFuncListFields(UitoolFuncDisplayInfo funcDisplayInfo);
	/**
	 *
	 * *
	 * @param t
	 */
	public void saveFuncDisplayInfo(T t);
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public String exportDeleteListSql(Long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public String exportInsertListSql(Long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 * @return
	 */
	public T selectByfuncId(long funcId);
	/**
	 *
	 * *
	 * @param funcId
	 */
	public void deleteByfuncId(long funcId);
}
