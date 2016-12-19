package com.focustech.oss2008.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.CatalogManagerDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.Catalog;
import com.focustech.oss2008.model.Category;
import com.focustech.uitool.framework.utils.DBTools;

/**
 * Copyright (c) 2006, focustech All rights reserved 本類用來處理ＭＩＣ系統中目錄相關的信息． 對目錄信息的初始化和緩存
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-20 下午03:55:13
 */
@Repository
public class DefaultCatalogManager extends OssHibernateDaoSupport implements CatalogManagerDao {
    @SuppressWarnings("deprecation")
    public Catalog getCataLogInfo(String lanCode, String catalogId) throws Exception {
        String tableName = "CORE_CATEGORY";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Catalog catalog = null;
        try {
            StringBuffer sbSql = new StringBuffer("SELECT REC_ID,CAT_CODE,PARENT_CAT_CODE,CAT_LEVEL,CAT_NAME_EN");
            sbSql.append(",CAT_NAME_CN,CAT_STATUS");
            sbSql.append(" FROM " + tableName + " A");
            sbSql.append(" WHERE CAT_CODE =? ");
            con = getCurrentSession().connection();
            pstmt = con.prepareStatement(sbSql.toString());
            pstmt.setString(1, catalogId);
            res = pstmt.executeQuery();
            if (res.next()) {
                catalog = new Catalog();
                catalog.setCatCode(res.getString("CAT_CODE"));
                catalog.setParentCatCode(res.getString("PARENT_CAT_CODE"));
                catalog.setCatLevel(res.getString("CAT_LEVEL"));
                catalog.setCatNameEn(res.getString("CAT_NAME_EN"));
                catalog.setCatNameCn(res.getString("CAT_NAME_CN"));
                catalog.setCatType("");
                catalog.setLinkCatCode("");
                catalog.setOldCatCode("");
                catalog.setCatStatus("");
                catalog.setAimCatCode("");
                catalog.setCatProperty("");
                // 實體目錄數據
                catalog.setSubEntity(0L);
                // 連接目錄數據
                catalog.setSubLink(0L);
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            DBTools.close(res);
            DBTools.close(pstmt);
        }
        return catalog;
    }

    /**
     * {@inheritDoc}
     */
    public Catalog getCataLogAndSubCatalog(String lanCode, String catalogParent, boolean bGetLinkCatalog)
            throws Exception {
        Catalog catalog = getCataLogInfo(lanCode, catalogParent);
        if (catalog == null) {
            catalog = new Catalog();
        }
        catalog.setSubCatalogs(getSubCatalogInfos(lanCode, catalogParent, bGetLinkCatalog));
        return catalog;
    }

    /**
     * 根據上級目錄獲取目錄信息
     *
     * @param catalogParent 上級目錄ID
     * @param bGetLinkCatalog 是否只獲取link目錄
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public List<Catalog> getSubCatalogInfos(String lanCode, String catalogParent, boolean bGetLinkCatalog)
            throws Exception {
        String tableName = "CORE_CATEGORY";
        List<Catalog> lisRet = new ArrayList<Catalog>();
        Connection con = null;
        //
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            //
            StringBuffer sbSql = new StringBuffer("SELECT REC_ID,CAT_CODE,PARENT_CAT_CODE,CAT_LEVEL,CAT_NAME_EN");
            sbSql.append(",CAT_NAME_CN,CAT_STATUS");
            sbSql.append("  FROM " + tableName);
            sbSql.append("  WHERE PARENT_CAT_CODE =?");
            // 不取link目錄
            // if (bGetLinkCatalog == false) {
            // sbSql.append(" AND CAT_TYPE<>? ");
            // }
            sbSql.append(" ORDER BY CAT_NAME_EN");
            //
            con = getCurrentSession().connection();
            pstmt = con.prepareStatement(sbSql.toString());
            pstmt.setString(1, catalogParent);
            //
            // if (bGetLinkCatalog == false) {
            // pstmt.setString(2, Constants.CATALOG_TYPE_LINK);
            // }
            res = pstmt.executeQuery();
            Catalog catalog = null;
            while (res.next()) {
                catalog = new Catalog();
                String catCode = res.getString("CAT_CODE");
                String catNameCn = res.getString("CAT_NAME_CN");
                String catNameEn = res.getString("CAT_NAME_EN");
                String catLevel = res.getString("CAT_LEVEL");
                String catStatus = res.getString("CAT_STATUS");
                catalog.setCatCode(catCode);
                catalog.setCatType("");
                catalog.setLinkCatCode("");
                catalog.setCatNameCn(catNameCn);
                catalog.setCatNameEn(catNameEn);
                catalog.setCatLevel(catLevel);
                catalog.setCatStatus(catStatus);
                catalog.setAimCatCode("");
                // 實體目錄數據
                catalog.setSubEntity(0L);
                // 連接目錄數據
                catalog.setSubLink(0L);
                //
                catalog.setParentCatCode(catalogParent);
                //
                lisRet.add(catalog);
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            DBTools.close(res);
            DBTools.close(pstmt);
        }
        return lisRet;
    }

    @SuppressWarnings("deprecation")
    public List<Catalog> getAllParentCatalogInfo(String lanCode, String catalogId) throws Exception {
        String tableName = "CORE_CATEGORY";
        List<Catalog> lisRet = new ArrayList<Catalog>();
        Connection con = null;
        //
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            //
            StringBuffer sbSql = new StringBuffer("SELECT REC_ID,CAT_CODE,PARENT_CAT_CODE,CAT_LEVEL,CAT_NAME_EN");
            sbSql.append(",CAT_NAME_CN,CAT_STATUS");
            sbSql.append(" FROM " + tableName + " A");
            sbSql.append(" CONNECT BY PRIOR PARENT_CAT_CODE = CAT_CODE START WITH CAT_CODE =?");
            sbSql.append(" ORDER BY CAT_LEVEL");
            //
            con = getCurrentSession().connection();
            pstmt = con.prepareStatement(sbSql.toString());
            pstmt.setString(1, catalogId);
            res = pstmt.executeQuery();
            Catalog catalog = null;
            while (res.next()) {
                catalog = new Catalog();
                String catCode = res.getString("CAT_CODE");
                String catNameCn = res.getString("CAT_NAME_CN");
                String catNameEn = res.getString("CAT_NAME_EN");
                String catLevel = res.getString("CAT_LEVEL");
                String catStatus = res.getString("CAT_STATUS");
                catalog.setCatCode(catCode);
                catalog.setCatType("");
                catalog.setLinkCatCode("");
                catalog.setCatNameCn(catNameCn);
                catalog.setCatNameEn(catNameEn);
                catalog.setCatLevel(catLevel);
                catalog.setCatStatus(catStatus);
                catalog.setAimCatCode("");
                catalog.setParentCatCode(res.getString("PARENT_CAT_CODE"));
                // 實體目錄數據
                catalog.setSubEntity(0l);
                // 連接目錄數據
                catalog.setSubLink(0l);
                lisRet.add(catalog);
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            DBTools.close(res);
            DBTools.close(pstmt);
        }
        return lisRet;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.CatalogManagerDao#getCategory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Category getCategory(String catCode) {
        String sql = "from Category c where c.catCode=?";
        List<Category> list = getCurrentSession().createQuery(sql).setString(0, catCode).list();
        if (list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

}
