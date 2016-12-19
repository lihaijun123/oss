package com.focustech.focus3d.paint.product.dao.impl;
import org.springframework.stereotype.Repository;

import com.focustech.focus3d.paint.product.dao.PaintProductDao;
import com.focustech.focus3d.paint.product.model.PaintProduct;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class PaintProductDaoImpl extends OssHibernateDaoSupport<PaintProduct> implements PaintProductDao<PaintProduct>{

}