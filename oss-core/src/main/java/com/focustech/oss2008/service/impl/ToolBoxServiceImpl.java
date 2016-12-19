package com.focustech.oss2008.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.OssAdminParameterDao;
import com.focustech.oss2008.dao.ToolBoxDao;
import com.focustech.oss2008.model.Category;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.ToolBoxService;

@Service
public class ToolBoxServiceImpl extends AbstractServiceSupport implements ToolBoxService {
    @Autowired
    private ToolBoxDao<Object> toolBoxDao;
    @Autowired
    private OssAdminParameterDao<OssAdminParameter> parameterDao;

    /**
     * 目錄樹
     */
    public HashMap<String, List<Category>> Category(String lanCode) {
        HashMap<String, List<Category>> category = new HashMap<String, List<Category>>();
        category.put("title", toolBoxDao.selectTitleCategory(lanCode));
        category.put("main", toolBoxDao.selectMainCategory(lanCode));
        return category;
    }

    public Map<String, String[]> getNextAreaList(String value, String key, String website) {
        List<OssAdminParameter> nextAreasList = parameterDao.selectNextLevelAreaList(key, value, website);
        return formatData(nextAreasList);
    }

    public Map<String, String[]> getProvinceList(String website) {
        List<OssAdminParameter> provinceList = parameterDao.selectProvinceList(website);
        return formatData(provinceList);
    }

    private Map<String, String[]> formatData(List<OssAdminParameter> areaData) {
        Map<String, String[]> nextArea = new HashMap<String, String[]>();
        String[] values = new String[areaData.size()];
        String[] keys = new String[areaData.size()];
        for (int i = 0; i < areaData.size(); i++) {
            OssAdminParameter parameter = areaData.get(i);
            values[i] = parameter.getParameterValue();
            keys[i] = parameter.getParameterKey();
        }
        nextArea.put("value", values);
        nextArea.put("key", keys);
        return nextArea;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ToolBoxService#getAllCatalogInfo()
     */
    public List<Category> getAllCatalogInfo(String lanCode) {
        return toolBoxDao.selectMainCategory(lanCode);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ToolBoxService#getTitleCategory(java.lang.String)
     */
    public List<Category> getTitleCategory(String lanCode) {
        return toolBoxDao.selectTitleCategory(lanCode);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ToolBoxService#getJobProvinceList(java.lang.String, java.lang.String)
     */
    public Map<String, String[]> getJobProvinceList(String paramType, String website) {
        List<OssAdminParameter> provinceList = parameterDao.selectParameters(paramType, website);
        return formatData(provinceList);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ToolBoxService#getCategory(java.lang.String)
     */
    @Override
    public Category getCategory(String catCode) {
        // TODO Auto-generated method stub
        return toolBoxDao.selectCategory(catCode);
    }

}
