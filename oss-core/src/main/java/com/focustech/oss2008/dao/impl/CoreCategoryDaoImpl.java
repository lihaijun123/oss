/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.oss2008.dao.impl;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.CoreCategoryDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.Category;

/**
 * CoreCategoryDaoImpl.java
 *
 * @author gaoying
 */
@Repository
public class CoreCategoryDaoImpl extends OssHibernateDaoSupport<Category> implements CoreCategoryDao<Category> {

}
