package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.OssAdminResource;

/**
 * <li></li>
 *
 * @author chensu 2008-5-13 下午01:34:11
 * @param <T>
 */
public interface ResourceDao<T> extends BaseHibernateDao<T> {
    long PUBLIC_RESOURCE_ID = -9999;

    /**
     * 取得所有用戶
     */
    public List<OssAdminResource> selectAllResources();

    public List<OssAdminResource> selectResourceByType(String type, String active );

    /**
     * 根據資源類型得到資源列表
     */
    public List<OssAdminResource> selectResourceByType(String type);

    /**
     * 根據資源類型和角色查詢資源列表
     */
    public List<OssAdminResource> selectResourceByTypeAndRole(String resourceType, Long roleId);

    /**
     * 根據資源類型和角色查詢資源列表
     */
    public List<OssAdminResource> selectResourceByTypeAndUser(String resourceType, Long roleId, String userId);

    public long getNewResourceId();

    public void deleteResource(OssAdminResource adminResource);

    public String exportDeleteListSql(Long resoruceId, Long roleId);

    public String exportInsertListSql(Long resoruceId, Long roleId);
}
