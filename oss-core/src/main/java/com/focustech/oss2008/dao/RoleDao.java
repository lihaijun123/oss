package com.focustech.oss2008.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;

public interface RoleDao<T> extends BaseHibernateDao<T> {
    /**
     * 取得所有角色
     */
    public List<OssAdminRole> selectAllRoles();

    /**
     * 取得所有胡效角色
     */
    public List<OssAdminRole> selectAllUsefulRoles();

    /**
     * 根據角色名獲取一個角色
     *
     * @param roleName 角色名稱
     * @return
     */
    public OssAdminRole selectRoleByName(String roleName);

    /**
     * 根據角色名和角色編號獲取角色信息
     *
     * @param roleName 角色名
     * @param roleId 角色編號
     * @return 角色信息
     */
    public OssAdminRole selectRoleByNameAndId(String roleName, Long roleId);

    /**
     * 根據角色得到資源
     *
     * @param roleId
     * @return
     */
    @Deprecated
    public Set<OssAdminResource> getResourceByRole(Long roleId);

    /**
     * 根據角色取該角色可以登陸的模塊
     *
     * @param roleId 角色編號
     */
    public List<String> getModelByRole(Long roleId);

    /**
     * 根據角色取該角色隊列
     */
    public List<Map<String, Object>> getQueueByRole(Long roleId);

    /**
     * 插入新建角色隊列
     */
    public int insertRoleQueue(Long roleId, String queueId);

    public long getNewUserId();
}
