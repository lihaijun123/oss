package com.focustech.focus3d.paint.model.dao.impl;
import org.springframework.stereotype.Repository;

import com.focustech.focus3d.paint.model.dao.PaintModelDao;
import com.focustech.focus3d.paint.model.model.PaintModel;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class PaintModelDaoImpl extends OssHibernateDaoSupport<PaintModel> implements PaintModelDao<PaintModel>{

}