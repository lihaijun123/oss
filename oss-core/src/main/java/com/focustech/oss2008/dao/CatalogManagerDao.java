package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.Catalog;
import com.focustech.oss2008.model.Category;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 */
public interface CatalogManagerDao<T> extends BaseHibernateDao<T> {
    public Catalog getCataLogInfo(String lanCode, String catalogId) throws Exception;

    public Catalog getCataLogAndSubCatalog(String lanCode, String catalogParent, boolean bGetLinkCatalog)
            throws Exception;

    public List<Catalog> getSubCatalogInfos(String lanCode, String catalogParent, boolean bGetLinkCatalog)
            throws Exception;

    public List<Catalog> getAllParentCatalogInfo(String lanCode, String catalogId) throws Exception;

    /**
     */
    public Category getCategory(String catCode);
}
