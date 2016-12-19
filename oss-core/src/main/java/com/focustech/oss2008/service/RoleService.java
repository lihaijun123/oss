package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.exception.service.OssRollbackCheckedException;
import com.focustech.oss2008.exception.service.RoleNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;

public interface RoleService {
    /**
     * 新增角色
     *
     * @throws RoleNameDuplicationException
     */
    public void addRole(OssAdminRole role) throws RoleNameDuplicationException;

    /**
     * 復制角色
     *
     * @throws RoleNameDuplicationException
     */
    public void copyRole(Long roleId, boolean resource, boolean queue, OssAdminRole role)
            throws RoleNameDuplicationException, OssRollbackCheckedException;

    /**
     * 取得所有角色
     */
    public List<OssAdminRole> getAllRoles();

    /**
     * 取得所有有效角色
     */
    public List<OssAdminRole> getAllUsefulRoles();

    /**
     * 根據角色編號，取得一個角色信息
     *
     * @param roleId 角色編號
     */
    public OssAdminRole getRole(Long roleId);

    /**
     * 修改一個角色信息
     *
     * @throws RoleNameDuplicationException
     */
    public void modifyRole(OssAdminRole role, String resource) throws RoleNameDuplicationException;

    /**
     * 刪除一個角色
     *
     * @param role
     */
    public void deleteRole(OssAdminRole role);

    /**
     * 通過角色信息得到menu資源
     */
    public List<OssAdminResource> getMenuByUserAndRole(String userId, Long roleId);

    /**
     * 通過角色信息得到menu資源
     */
    public List<OssAdminResource> getResourceByRole(Long roleId, String resourceType);

    /**
     * 通過角色號得角色名
     */
    public String getRoleNameById(Long roleId);

    /**
     * 通過角色信息得到url資源
     */
    @Deprecated
    public List<OssAdminResource> getURLByRole(Long roleId);

    /**
     * 通過角色信息得到方法資源
     */
    @Deprecated
    public List<OssAdminResource> getMethodByRole(Long roleId);

    /**
     * 修改角色資源的對應關系
     */
    public void modifyRoleResourceScope(Long roleId, String deletedIds, Long[] resourceId, String[] scope);

    /**
     * 根據角色編號和資源編號，取得此角色對應此資源的操作範圍。
     * <p>
     * 目前url的範圍僅限于列表工具的url使用，且返回的配置信息都為sql語句
     *
     * @return 該角色對應此url的數據選擇範圍的sql語句或null
     * @throws IllegalArgumentException
     */
    public String getUrlScope(Long roleId, Long resourceId);

    /**
     * 根據角色編號，取得該角色可以登陸的系統模塊列表
     *
     * @param roleId 角色編號
     */
    public List<String> getModelListByRole(Long roleId);
}
