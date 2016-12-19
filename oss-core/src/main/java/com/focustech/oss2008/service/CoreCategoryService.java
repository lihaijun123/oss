/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.oss2008.service;

import com.focustech.oss2008.model.Category;

/**
 * CoreCategoryService.java
 *
 * @author gaoying
 */
public interface CoreCategoryService {
    /**
     * 根據目錄id查詢出目錄信息
     *
     * @param recId
     * @return
     */
    public Category selectCategory(Long recId);

}
