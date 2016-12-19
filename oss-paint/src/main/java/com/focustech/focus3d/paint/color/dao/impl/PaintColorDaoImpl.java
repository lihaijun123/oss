package com.focustech.focus3d.paint.color.dao.impl;
import org.springframework.stereotype.Repository;

import com.focustech.focus3d.paint.color.dao.PaintColorDao;
import com.focustech.focus3d.paint.color.model.PaintColor;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class PaintColorDaoImpl extends OssHibernateDaoSupport<PaintColor> implements PaintColorDao<PaintColor>{

}