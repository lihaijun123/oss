package com.focustech.oss2008.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.UitoolListDisplayFieldDao;
import com.focustech.oss2008.model.UitoolListDisplayField;
import com.focustech.oss2008.service.UitoolListDisplayFieldService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class UitoolListDisplayFieldServiceImpl implements UitoolListDisplayFieldService<UitoolListDisplayField> {
	@Autowired
	private UitoolListDisplayFieldDao<UitoolListDisplayField> listDisplayFieldDao;
	@Override
	public List<UitoolListDisplayField> getListByFuncId(long funcId) {
		return listDisplayFieldDao.getListByFuncId(funcId);
	}
	@Override
	public void insertOrUpdate(UitoolListDisplayField t) {
		listDisplayFieldDao.insertOrUpdate(t);
	}
}
