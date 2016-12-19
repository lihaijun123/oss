package com.focustech.oss2008.service;

import java.io.Serializable;
import java.util.List;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface BaseEntityService<T> {
	/**
	 *
	 * *
	 * @param t
	 */
	public void insert(T t);
	/**
	 *
	 * *
	 * @param t
	 */
	public void update(T t);
	/**
	 *
	 * *
	 * @param t
	 */
	public void insertOrUpdate(T t);
	/**
	 *
	 * *
	 * @param id
	 */
	public void delete(Serializable id);
	/**
	 *
	 * *
	 * @param t
	 */
	public void delete(T t);
	/**
	 *
	 * *
	 * @param id
	 * @return
	 */
	public T select(Serializable id);
	/**
	 * *
	 * @return
	 */
	public List<T> list();
}
