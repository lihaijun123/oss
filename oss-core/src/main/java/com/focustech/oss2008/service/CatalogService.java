/*
 * Created on 2005-10-13 To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.model.Catalog;
import com.focustech.oss2008.model.Category;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-20 下午01:34:59
 */
public interface CatalogService {
    public Catalog getCatalogInfo(String lanCode, String catalogId);

    public Catalog getCatalogAndSub(String lanCode, String catalogId, boolean bGetLinkCatalog);

    public List<Catalog> getAllParentCatCode(String lanCode, String catalogId);

    /**
     * 獲取根據節點下的所有目錄信息 parent_cat_id = "0"
     *
     * @return
     */
    public Catalog getRootCatalog(String lanCode);

    /**
     * 中英文目錄轉換
     *
     * @param busnissScope
     * @param website
     * @return
     */
    public String convertCategory(String busnissScope, String website);

    /**
     * 獲取某個目錄
     *
     * @param catCode
     * @return
     */
    public Category getCategory(String catCode);
}
