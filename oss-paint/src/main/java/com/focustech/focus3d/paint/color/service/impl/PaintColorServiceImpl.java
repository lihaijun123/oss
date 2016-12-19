package com.focustech.focus3d.paint.color.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.paint.color.dao.PaintColorDao;
import com.focustech.focus3d.paint.color.model.PaintColor;
import com.focustech.focus3d.paint.color.service.PaintColorService;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.impl.BaseEntityServiceImpl;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class PaintColorServiceImpl extends BaseEntityServiceImpl<PaintColor> implements PaintColorService<PaintColor>{
    @Autowired
	private PaintColorDao<PaintColor> paintColorDaoImpl;
    @Override
	public BaseHibernateDao<PaintColor> getEntityDao() {
		return paintColorDaoImpl;
	}
}