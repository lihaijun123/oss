package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.exception.service.ResourceNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;

public interface ResourceService {
    /** 資源類型:url */
    public static final String RESOURCE_TYPE_URL = "2";
    /** 資源類型:方法 */
    public static final String RESOURCE_TYPE_METHOD = "3";
    /** 資源類型:菜單 */
    public static final String RESOURCE_TYPE_MENU = "1";
    /**工作台*/
    public static final String RESOURCE_TYPE_WORKSPACE = "6";

    /** 資源類型名:url */
    public static final String RESOURCE_TYPE_URL_NAME = "url";
    /** 資源類型名:method */
    public static final String RESOURCE_TYPE_METHOD_NAME = "method";
    /** 資源類型名:method */
    public static final String RESOURCE_TYPE_MENU_NAME = "menu";

    public static final String RESOURCE_TYPE_WORKSPACE_NAME = "workspace";

    /**
     * 新增許可
     */
    public void addResource(OssAdminResource resource) throws ResourceNameDuplicationException;

    /**
     * 取得所有許可
     */
    public List<OssAdminResource> getAllResources();

    /**
     * 根據許可編號，取得一個許可信息
     */
    public OssAdminResource getResource(Long resourceId);

    /**
     * 修改一個許可信息
     */
    public void modifyResource(OssAdminResource resource) throws ResourceNameDuplicationException;

    /**
     * 通過資源類型找到對應所有資源
     */
    public List<OssAdminResource> getResourceByType(String type);

    /**
     * 刪除資源
     */
    public void deleteResource(OssAdminResource resource);

    public String exportDeleteListSql(Long resoruceId, Long roleId);

    public String exportInsertListSql(Long resoruceId, Long roleId);

    public List<OssAdminResource> selectResourceByType(String type, String active );

}
