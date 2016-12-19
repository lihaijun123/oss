package com.focustech.oss2008.model;

import java.util.ArrayList;
import java.util.List;

import com.focustech.oss2008.Constants;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-20 下午02:18:13
 */
public class Catalog {
    /**
     * 父分類
     */
    private Catalog parentCatalog = null;
    /** 記錄ID */
    private String recId = "";
    /** 分類碼 */
    private String catCode = "";
    private String encodeCatCode = "";
    /** 父分類碼 */
    private String parentCatCode = "";
    private String encodeParentCatCode = "";
    /** 分類層次 */
    private String catLevel = "";
    /** 分類英文名稱 */
    private String catNameEn = "";
    /** 分類中文名稱 */
    private String catNameCn = "";
    /** 分類類型 */
    private String catType = "";
    /** 鏈接分類碼 */
    private String linkCatCode = "";
    /** 舊分類碼 */
    private String oldCatCode = "";
    // /////////////////////////////////////////////////////////
    /** 分類狀態 */
    private String catStatus = "";
    /** 目標分類碼 */
    private String aimCatCode = "";
    private String encodeAimCatCode = "";
    /** 分類特性 */
    private String catProperty = "";
    /** 分類描述 */
    private String catDescription = "";
    // /////////////////////////////////////////////////////////
    /** 上次更新時間 */
    private String lastUpdateTime = "";
    /** 上次更新人號 */
    private String lastUpdaterNo = "";
    /** 上次更新人姓名 */
    private String lastUpdaterName = "";
    //
    private long subEntity = 0;
    private long subLink = 0;
    private List<Catalog> subCatalogs = new ArrayList<Catalog>();
    /** Common分類子分類英文名排序序列 */
    // private List childCatalogCodeEnOrder = new ArrayList();
    /** Common分類分類碼最小的子分類 */
    private Catalog MinCodeSubCommonCatalog = null;

    // ///////////////////////////////////////////////////////////////////////////////
    public boolean isRoot() {
        if ((parentCatalog == null || parentCatalog.getCatCode() == null || parentCatalog.getCatCode().length() <= 0 || "0"
                .equals(parentCatalog.getCatCode()))
                && (parentCatCode == null || parentCatCode.length() <= 0 || "0".equals(parentCatCode))) {
            return true;
        }
        return false;
    }

    public boolean isNewOrModifyRoot() {
        if ((parentCatalog == null || parentCatalog.getCatCode() == null || parentCatalog.getCatCode().length() <= 0 || "0"
                .equals(parentCatalog.getCatCode()))
                && (parentCatCode == null || parentCatCode.length() <= 0 || "0".equals(parentCatCode))
                && Constants.CATALOG_PROPERTY_NEWORMODIFY_LEVEL1.equals(getCatProperty())) {
            return true;
        }
        return false;
    }

    public boolean isLinkCatalog() {
        if (Constants.CATALOG_TYPE_LINK.equals(getCatType())) {
            return true;
        }
        return false;
    }

    public boolean isEntityCatalog() {
        if (Constants.CATALOG_TYPE_ENTITY.equals(getCatType())) {
            return true;
        }
        return false;
    }

    public boolean isTransferOrUniteCatalog() {
        if (Constants.CATALOG_STATUS_TRANSFERORUNITE.equals(getCatStatus())) {
            return true;
        }
        return false;
    }

    public boolean isCommonCatalog() {
        if (Constants.CATALOG_STATUS_COMMON.equals(getCatStatus())) {
            return true;
        }
        return false;
    }

    public boolean hasSubCatalog() {
        // return !childCatalogCodeEnOrder.isEmpty();
        return (getSubEntity() + getSubLink()) > 0;
    }

    public boolean hasEntitySubCatalog() {
        return getSubEntity() > 0;
        // try
        // {
        // if (!childCatalogCodeEnOrder.isEmpty())
        // {
        // for (Iterator iter = childCatalogCodeEnOrder.iterator(); iter.hasNext();)
        // {
        // String cataCode = (String) iter.next();
        // // Catalog catalog = CatalogTool.getCatalogInfo(cataCode);
        // // if (catalog != null && catalog.isEntityCatalog())
        // {
        // return true;
        // }
        // }
        // }
        // }
        // catch (Exception e)
        // {
        // }
        // return false;
    }

    public boolean hasLinkSubCatalog() {
        // try
        // {
        // if (!childCatalogCodeEnOrder.isEmpty())
        // {
        // for (Iterator iter = childCatalogCodeEnOrder.iterator(); iter.hasNext();)
        // {
        // String cataCode = (String) iter.next();
        // // Catalog catalog = CatalogTool.getCatalogInfo(cataCode);
        // // if (catalog != null && catalog.isLinkCatalog())
        // {
        // return true;
        // }
        // }
        // }
        // }
        // catch (Exception e)
        // {
        // }
        // return false;
        return getSubLink() > 0;
    }

    /**
     * 此方法返回VO中部分顯示要求的分類信息 擴充三級的分類返回分類碼最小的三級分類 轉合分類返回目標分類,如果目標分類也擴充了三級,返回目標分類的分類碼最小的三級分類
     *
     * @return Catalog
     */
    public Catalog getDisplayCatalog() {
        Catalog reCatalog = this;
        try {
            if (isCommonCatalog()) {
                if (hasEntitySubCatalog()) {
                    reCatalog = getMinCodeSubCommonCatalog();
                }
            }
            else if (isTransferOrUniteCatalog()) {
                // Catalog aimCatalog = CatalogTool.getCatalogInfo(getAim_cat_code());
                // reCatalog = aimCatalog.getDisplayCatalog();
            }
        }
        catch (Exception e) {
        }
        return reCatalog;
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    public Catalog getParentCatalog() {
        return parentCatalog;
    }

    /**
     * @param catalog
     */
    public void setParentCatalog(Catalog catalog) {
        parentCatalog = catalog;
    }

    public Catalog getMinCodeSubCommonCatalog() {
        return MinCodeSubCommonCatalog;
    }

    public void setMinCodeSubCommonCatalog(Catalog minCodeSubCommonCatalog) {
        MinCodeSubCommonCatalog = minCodeSubCommonCatalog;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getEncodeCatCode() {
        return encodeCatCode;
    }

    public void setEncodeCatCode(String encodeCatCode) {
        this.encodeCatCode = encodeCatCode;
    }

    public String getParentCatCode() {
        return parentCatCode;
    }

    public void setParentCatCode(String parentCatCode) {
        this.parentCatCode = parentCatCode;
    }

    public String getEncodeParentCatCode() {
        return encodeParentCatCode;
    }

    public void setEncodeParentCatCode(String encodeParentCatCode) {
        this.encodeParentCatCode = encodeParentCatCode;
    }

    public String getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(String catLevel) {
        this.catLevel = catLevel;
    }

    public String getCatNameEn() {
        return catNameEn;
    }

    public void setCatNameEn(String catNameEn) {
        this.catNameEn = catNameEn;
    }

    public String getCatNameCn() {
        return catNameCn;
    }

    public void setCatNameCn(String catNameCn) {
        this.catNameCn = catNameCn;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public String getLinkCatCode() {
        return linkCatCode;
    }

    public void setLinkCatCode(String linkCatCode) {
        this.linkCatCode = linkCatCode;
    }

    public String getOldCatCode() {
        return oldCatCode;
    }

    public void setOldCatCode(String oldCatCode) {
        this.oldCatCode = oldCatCode;
    }

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }

    public String getAimCatCode() {
        return aimCatCode;
    }

    public void setAimCatCode(String aimCatCode) {
        this.aimCatCode = aimCatCode;
    }

    public String getEncodeAimCatCode() {
        return encodeAimCatCode;
    }

    public void setEncodeAimCatCode(String encodeAimCatCode) {
        this.encodeAimCatCode = encodeAimCatCode;
    }

    public String getCatProperty() {
        return catProperty;
    }

    public void setCatProperty(String catProperty) {
        this.catProperty = catProperty;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdaterNo() {
        return lastUpdaterNo;
    }

    public void setLastUpdaterNo(String lastUpdaterNo) {
        this.lastUpdaterNo = lastUpdaterNo;
    }

    public String getLastUpdaterName() {
        return lastUpdaterName;
    }

    public void setLastUpdaterName(String lastUpdaterName) {
        this.lastUpdaterName = lastUpdaterName;
    }

    public long getSubEntity() {
        return subEntity;
    }

    public void setSubEntity(long subEntity) {
        this.subEntity = subEntity;
    }

    public long getSubLink() {
        return subLink;
    }

    public void setSubLink(long subLink) {
        this.subLink = subLink;
    }

    public List<Catalog> getSubCatalogs() {
        return subCatalogs;
    }

    public void setSubCatalogs(List<Catalog> subCatalogs) {
        this.subCatalogs = subCatalogs;
    }
}
