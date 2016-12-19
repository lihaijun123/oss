package com.focustech.oss2008.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.UitoolFuncTableDao;
import com.focustech.oss2008.model.UitoolFuncTable;
import com.focustech.oss2008.service.UitoolFuncTableService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class UitoolFuncTableServiceImpl implements UitoolFuncTableService<UitoolFuncTable>{

	@Autowired
	private UitoolFuncTableDao<UitoolFuncTable> funcTableDao;

	@Override
	public void deleteByFuncId(long funcId) {
		funcTableDao.deleteByFuncId(funcId);
	}

	@Override
	public UitoolFuncTable selectByFuncId(long funcId) {
		return funcTableDao.selectByFuncId(funcId);
	}

	@Override
	public void save(UitoolFuncTable t) {
		funcTableDao.insertOrUpdate(t);
	}

}
