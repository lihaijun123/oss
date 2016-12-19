package com.focustech.oss2008.service;

import com.focustech.model.common.BaseEntity;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface DBCommonService<T> extends BaseEntityService<T>{
	/**
	 *
	 * *
	 * @param sn
	 * @return
	 */
	public BaseEntity get(BaseEntity baseEntity);
}
