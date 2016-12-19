package com.focustech.oss2008.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.focustech.oss2008.model.Category;

public interface ToolBoxService {
    /**
     * 獲取所有目錄碼信息
     *
     * @param lanCode
     * @return
     */
    public List<Category> getAllCatalogInfo(String lanCode);

    /**
     * 獲取目錄碼信息
     *
     * @param lanCode
     * @return
     */
    public HashMap<String, List<Category>> Category(String lanCode);

    /**
     * 獲取省份列表
     *
     * @param website 站點類型
     * @return
     */
    public Map<String, String[]> getProvinceList(String website);

    /**
     * 獲取指定地區的下級地區列表
     *
     * @param value 指定的地區值
     * @param type 字段類型
     * @param website 站點類型
     * @return
     */
    public Map<String, String[]> getNextAreaList(String value, String type, String website);

    /**
     * 獲取大標題目錄碼信息
     *
     * @param lanCode
     * @return
     */
    public List<Category> getTitleCategory(String lanCode);

    /**
     * 獲取招聘網站省份列表
     *
     * @param website 站點類型
     * @return
     */
    public Map<String, String[]> getJobProvinceList(String paramType, String website);

    /**
     * 獲取所給目錄碼的目錄信息
     *
     * @param catCode
     * @return
     */
    public Category getCategory(String catCode);
}
