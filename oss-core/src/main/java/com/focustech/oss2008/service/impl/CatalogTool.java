package com.focustech.oss2008.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.dao.CatalogManagerDao;
import com.focustech.oss2008.dao.OssAdminParameterDao;
import com.focustech.oss2008.model.Catalog;
import com.focustech.oss2008.model.Category;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.CatalogService;
import com.focustech.oss2008.service.OssAdminParameterService;


/**
 * @author hexuey 用來輔助ＭＩＣ系統中的分類信息操作．
 */
@Service
public class CatalogTool extends AbstractServiceSupport implements CatalogService {
    @Autowired
    private CatalogManagerDao catalogDao;
    @Autowired
    private OssAdminParameterDao<OssAdminParameter> parametersDao;
    //
    private static Map<String, Catalog> CATALOGS_CASH = new HashMap<String, Catalog>();

    public Catalog getCatalogInfo(String lanCode, String catalogId) {
        Catalog catalog = CATALOGS_CASH.get(catalogId);
        if (catalog == null) {
            try {
                catalog = catalogDao.getCataLogInfo(lanCode, catalogId);
            }
            catch (Exception e) {
                return new Catalog();
            }
            if (catalog == null) {
                return new Catalog();
            }

        }
        return catalog;
    }

    public Catalog getCatalogAndSub(String lanCode, String catalogId, boolean bGetLinkCatalog) {
        try {
            return catalogDao.getCataLogAndSubCatalog(lanCode, catalogId, bGetLinkCatalog);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Catalog> getAllParentCatCode(String lanCode, String catalogId) {
        try {
            return catalogDao.getAllParentCatalogInfo(lanCode, catalogId);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Catalog getRootCatalog(String lanCode) {
        try {
            return catalogDao.getCataLogAndSubCatalog(lanCode, "0", true);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根據分類編號取出其對應的所有下級子分類到3級分類為止 如果沒有下一級分類就返回一個空VTAB對象
     *
     * @param strCatCode
     * @param getType 0︰取實分類不取鏈接分類；1︰取實分類取鏈接分類;
     * @return 返回對象包含︰cat_code ,link_cat_code ,name_cn , name_en ,catlevel Link:Mica
     */
    public List<Catalog> getSubCatalogOfAllDepth(String catalogId, String type, String encode) {
        try {
            // boolean linkCatalog = false;
            if ("1".equals(type)) {
                // linkCatalog = true;
            }
            // return catalogDao.getCataLogAndSubCatalog(catalogId, linkCatalog);
            return null;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.CatalogService#convertCategory(java.lang.String, java.lang.String)
     */
    public String convertCategory(String busnissScope, String website) {
        String returnValue = "";
        if ((busnissScope != null) && (busnissScope.length() > 0)) {
            String[] s = busnissScope.split(",");
            for (String id : s) {
                OssAdminParameter param =
                        parametersDao.selectParameterByTypeKeyAndSite(OssAdminParameterService.PARAMETER_CATEGORY, id,
                                website);
                if (param != null) {
                    if (!"".equals(returnValue)) {
                        returnValue += ",";
                    }
                    returnValue += param.getParameterValue();
                }
            }
        }
        return StringUtils.removeRepeat(returnValue);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.CatalogService#getCategory(java.lang.String)
     */
    public Category getCategory(String catCode) {
        return catalogDao.getCategory(catCode);
    }
}
