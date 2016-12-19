package com.focustech.focus3d.paint.product.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.paint.product.dao.PaintProductDao;
import com.focustech.focus3d.paint.product.model.PaintProduct;
import com.focustech.focus3d.paint.product.service.PaintProductService;
import com.focustech.oss2008.dao.BaseHibernateDao;
import com.focustech.oss2008.service.impl.BaseEntityServiceImpl;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class PaintProductServiceImpl extends BaseEntityServiceImpl<PaintProduct> implements PaintProductService<PaintProduct>{
    @Autowired
	private PaintProductDao<PaintProduct> paintProductDaoImpl;
    @Override
	public BaseHibernateDao<PaintProduct> getEntityDao() {
		return paintProductDaoImpl;
	}
}