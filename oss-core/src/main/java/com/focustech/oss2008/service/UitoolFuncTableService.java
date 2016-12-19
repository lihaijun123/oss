package com.focustech.oss2008.service;


/**
 *
 * *
 * @author lihaijun
 *
 */
public interface UitoolFuncTableService<T> {

	public void deleteByFuncId(long funcId);

	public T selectByFuncId(long funcId);

	public void save(T t);
}
