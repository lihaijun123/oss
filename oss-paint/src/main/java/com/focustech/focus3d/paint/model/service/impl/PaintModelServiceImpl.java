package com.focustech.focus3d.paint.model.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.paint.model.dao.PaintModelDao;
import com.focustech.focus3d.paint.model.model.PaintModel;
import com.focustech.focus3d.paint.model.service.PaintModelService;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.impl.BaseEntityServiceImpl;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class PaintModelServiceImpl extends BaseEntityServiceImpl<PaintModel> implements PaintModelService<PaintModel>{
    @Autowired
	private PaintModelDao<PaintModel> paintModelDaoImpl;
    @Override
	public BaseHibernateDao<PaintModel> getEntityDao() {
		return paintModelDaoImpl;
	}
}