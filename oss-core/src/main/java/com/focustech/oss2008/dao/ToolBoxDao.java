package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.Category;

public interface ToolBoxDao<T> extends BaseHibernateDao<T> {
    /**
     * 獲取所有目錄碼信息
     *
     * @param lanCode
     * @return
     */
    public List<Category> selectTitleCategory(String lanCode);

    /**
     * 獲取主目錄碼信息
     *
     * @param lanCode
     * @return
     */
    public List<Category> selectMainCategory(String lanCode);

    /**
     * 獲取所給目錄碼的目錄信息
     *
     * @param lanCode
     * @param catCode
     * @param status
     * @return
     */
    public Category selectCategory(String lanCode, Long catCode, String status);

    /**
     * 獲取所給目錄碼目錄信息
     *
     * @param catCode
     * @return
     */
    public Category selectCategory(String catCode);

}
