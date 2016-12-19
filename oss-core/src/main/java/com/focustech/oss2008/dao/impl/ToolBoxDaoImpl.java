package com.focustech.oss2008.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.ToolBoxDao;
import com.focustech.oss2008.model.Category;

/**
 * ToolBoxDaoImpl.java
 *
 * @author jibin
 */
@Repository
public class ToolBoxDaoImpl extends OssHibernateDaoSupport<Object> implements ToolBoxDao<Object> {

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.ToolBoxDao#selectMainCategory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Category> selectMainCategory(String lanCode) {
        String tableName = "Category";
        // if (Constants.SITE_TYPE_MIC_CN.equals(lanCode)) {
        // tableName = "CoreCategoryCn";
        // }
        String sql = "from " + tableName + " c where c.catStatus<>'0' order by c.catCode asc";
        return getCurrentSession().createQuery(sql).list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.ToolBoxDao#selectTitleCategory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Category> selectTitleCategory(String lanCode) {
        String tableName = "Category";
        // if (Constants.SITE_TYPE_MIC_CN.equals(lanCode)) {
        // tableName = "CoreCategoryCn";
        // }
        String sql = "from " + tableName + " c where c.catStatus<>'0' and c.catLevel=1 order by c.catCode asc";
        return getCurrentSession().createQuery(sql).list();
    }

    @SuppressWarnings("unchecked")
    public Category selectCategory(String catCode) {
        String sql = "from Category c where c.catCode=? order by c.catCode asc";
        return (Category) getCurrentSession().createQuery(sql).setString(0, catCode).uniqueResult();
    }

    public Category selectCategory(String lanCode, Long catCode, String status) {
        String tableName = "Category";
        // if (Constants.SITE_TYPE_MIC_CN.equals(lanCode)) {
        // tableName = "CoreCategoryCn";
        // }
        String sql = "from " + tableName + " c where c.catCode=? ";
        // 不正常的目錄
        if ("0".equals(status) || "1".equals(status) || "2".equals(status)) {
            sql += "and c.catStatus=?";
        }
        else {
            sql += "and c.catStatus<>?";
        }
        return (Category) getCurrentSession().createQuery(sql).setLong(0, catCode).setString(1, status).uniqueResult();
    }

}
