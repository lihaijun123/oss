package com.focustech.focus3d.paint.category.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.paint.category.dao.PaintCatgoryDao;
import com.focustech.focus3d.paint.category.model.PaintCatgory;
import com.focustech.focus3d.paint.category.service.PaintCatgoryService;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.impl.BaseEntityServiceImpl;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class PaintCatgoryServiceImpl extends BaseEntityServiceImpl<PaintCatgory> implements PaintCatgoryService<PaintCatgory>{
    @Autowired
	private PaintCatgoryDao<PaintCatgory> paintCatgoryDaoImpl;
    @Override
	public BaseHibernateDao<PaintCatgory> getEntityDao() {
		return paintCatgoryDaoImpl;
	}
}