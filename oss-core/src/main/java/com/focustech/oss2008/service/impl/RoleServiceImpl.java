package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.ResourceDao;
import com.focustech.oss2008.dao.RoleDao;
import com.focustech.oss2008.dao.RoleModelDao;
import com.focustech.oss2008.dao.RoleResourceDao;
import com.focustech.oss2008.exception.service.OssRollbackCheckedException;
import com.focustech.oss2008.exception.service.RoleNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.RoleModel;
import com.focustech.oss2008.model.RoleResource;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;

/**
 * <li></li>
 *
 * @author chensu 2008-5-13 下午01:41:17
 */
@Service
public class RoleServiceImpl extends AbstractServiceSupport implements RoleService {
    @Autowired
    RoleDao<OssAdminRole> roleDao;
    @Autowired
    ResourceDao<OssAdminResource> resourceDao;
    @Autowired
    RoleResourceDao<RoleResource> roleResourceDao;
    @Autowired
    RoleModelDao<RoleModel> roleModelDao;

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#addUser(com.focustech.oss2008.model.OssAdminUsers)
     */
    public void addRole(OssAdminRole role) throws RoleNameDuplicationException {
        checkRoleName(role.getRoleName());
        role.setRoleId(roleDao.getNewUserId());
        role.setCreatorId(getLoginUser().getUserId());
        role.setCreatedTime(new Date());
        role.setModifierId(getLoginUser().getUserId());
        role.setModifiedTime(new Date());
        roleDao.insert(role);
        //保存模块角色关系数据
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(role.getRoleId());
        roleModel.setModel("SAL");
        roleModelDao.insertOrUpdate(roleModel);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#copyRole(java.lang.Long, com.focustech.oss2008.model.OssAdminRole)
     */
    public void copyRole(Long roleId, boolean resource, boolean queue, OssAdminRole role)
            throws RoleNameDuplicationException, OssRollbackCheckedException {
        checkRoleName(role.getRoleName());
        // 先增加一個角色
        roleDao.insert(role);
        // 復制相應的資源權限
        if (resource) {
            List<RoleResource> lst = roleResourceDao.getRoleResourcesByRoleId(roleId);
            for (RoleResource roleResource : lst) {
                RoleResource resources = new RoleResource();
                resources.setRole(role);
                resources.setResource(roleResource.getResource());
                resources.setScope(roleResource.getScope());
                roleResourceDao.update(resources);
            }
        }
        // 復制相應的隊列權限
        if (queue) {
            List<Map<String, Object>> queues = roleDao.getQueueByRole(roleId);
            for (Map<String, Object> map : queues) {
                String queueId = (String) map.get("rtt_tsknod");
                int i = roleDao.insertRoleQueue(role.getRoleId(), queueId);
                if (i != 1) {
                    throw new OssRollbackCheckedException("復制角色失敗");
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#getAllUsers()
     */
    public List<OssAdminRole> getAllRoles() {
        return roleDao.selectAllRoles();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getAllUsefulRoles()
     */
    public List<OssAdminRole> getAllUsefulRoles() {
        return roleDao.selectAllUsefulRoles();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#getUser(java.lang.String)
     */
    public OssAdminRole getRole(Long roleId) {
        return roleDao.select(roleId);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#modifyUser(com.focustech.oss2008.model.OssAdminUsers)
     */
    public void modifyRole(OssAdminRole role, String resource) throws RoleNameDuplicationException {
        Long roleId = role.getRoleId();
        OssAdminRole pRole = roleDao.select(roleId);
		checkRoleName(role.getRoleName(), roleId);
        Set<RoleResource> pResources = pRole.getResources();
        Set<RoleResource> resources = role.getResources();
        pRole.setResources(null);
        for (RoleResource pResource : pResources) {
        	OssAdminResource r = resourceDao.select(pResource.getResource().getResourceId());
        	if(r.getResourceType().equals(resource)){
        		roleResourceDao.delete(pResource);
        	}
		}
        if(resources != null){
        	for (RoleResource roleResource : resources) {
        		roleResource.setRole(pRole);
        	}
        }
        pRole.setResources(resources);
        roleDao.update(pRole);
        //兼容旧数据，如果role模块关系表没有数据则加入新数据
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(roleId);
        roleModel.setModel("SAL");
        int isExistRoleModel = roleModelDao.checkRoleModel(roleModel);
        if(isExistRoleModel <= 0){
        	roleModelDao.insertOrUpdate(roleModel);
        }
    }
    /**
     * resource 是否被选中
     * *
     * @param resources
     * @param resourceId
     * @return
     */
    public boolean isChecked(Set<RoleResource> resources, long resourceId){
    	boolean flag = false;
    	for (RoleResource roleResource : resources) {
			if(roleResource.getRecordId() == resourceId){
				flag = true;
				break;
			}
		}
    	return flag;
    }
    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#deleteRole(com.focustech.oss2008.model.OssAdminRoles)
     */
    public void deleteRole(OssAdminRole role) {
        roleDao.delete(role);
    }

    /**
     * 查看該角色名稱是否已經存在,用于修改驗證
     *
     * @param roleName 角色名稱
     * @param roleId 角色編號
     * @throws RoleNameDuplicationException
     */
    protected void checkRoleName(String roleName, Long roleId) throws RoleNameDuplicationException {
        if (roleDao.selectRoleByNameAndId(roleName, roleId) != null)
            throw new RoleNameDuplicationException();
    }

    /**
     * 檢查該用戶名是否存在,用于添加驗證
     *
     * @param roleName
     * @throws RoleNameDuplicationException
     */
    protected void checkRoleName(String roleName) throws RoleNameDuplicationException {
        if (roleDao.selectRoleByName(roleName) != null)
            throw new RoleNameDuplicationException();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getMenuByRole(java.lang.Long)
     */
    public List<OssAdminResource> getResourceByRole(Long roleId, String resourceType) {
        return resourceDao.selectResourceByTypeAndRole(resourceType, roleId);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getMenuByUserAndRole(java.lang.String, java.lang.Long)
     */
    public List<OssAdminResource> getMenuByUserAndRole(String userId, Long roleId) {
        return resourceDao.selectResourceByTypeAndUser(ResourceService.RESOURCE_TYPE_MENU, roleId, userId);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getRoleNameById(java.lang.Long)
     */
    @Deprecated
    public List<OssAdminResource> getMethodByRole(Long roleId) {
        List<OssAdminResource> menuResourcesList = new ArrayList<OssAdminResource>();
        Set<OssAdminResource> resourceSet = roleDao.getResourceByRole(roleId);
        for (Iterator<OssAdminResource> itr = resourceSet.iterator(); itr.hasNext();) {
            OssAdminResource resource = itr.next();
            if (resource.getResourceType().equals(ResourceService.RESOURCE_TYPE_METHOD))
                menuResourcesList.add(resource);
        }
        return menuResourcesList;
    }

    /*
	 *
	 */
    @Deprecated
    public List<OssAdminResource> getURLByRole(Long roleId) {
        List<OssAdminResource> menuResourcesList = new ArrayList<OssAdminResource>();
        Set<OssAdminResource> resourceSet = roleDao.getResourceByRole(roleId);
        for (Iterator<OssAdminResource> itr = resourceSet.iterator(); itr.hasNext();) {
            OssAdminResource resource = itr.next();
            if (resource.getResourceType().equals(ResourceService.RESOURCE_TYPE_URL))
                menuResourcesList.add(resource);
        }
        return menuResourcesList;
    }

    public String getRoleNameById(Long roleId) {
        return roleDao.select(String.valueOf(roleId)).getRoleName();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#modifyRoleResourceScope(java.lang.Long, java.lang.String,
     * java.lang.Long[], java.lang.String[])
     */
    public void modifyRoleResourceScope(Long roleId, String deletedIds, Long[] resourceId, String[] scope) {
        OssAdminRole role = roleDao.select(roleId);
        // 刪除角色資源對應關系
        String[] deletedIdAry = deletedIds.split(",");
        for (int i = 0; i < deletedIdAry.length; i++) {
            for (RoleResource roleResource : role.getResources()) {
                if (roleResource.getResource().getResourceId().toString().equals(deletedIdAry[i])) {
                    role.getResources().remove(roleResource);
                    roleResourceDao.delete(roleResource);
                    break;
                }
            }
        }
        // 添加修改角色資源對應關系
        for (int i = 0; null != resourceId && i < resourceId.length; i++) {
            boolean isRoleResourceSaved = false;
            boolean hasRoleResource = false;
            for (RoleResource roleResource : role.getResources()) {
                if (resourceId[i].equals(roleResource.getResource().getResourceId())) {
                    hasRoleResource = true;
                    if (!roleResource.isEqualsScope(scope[i])) {
                        roleResource.setScope(scope[i]);
                        isRoleResourceSaved = true;
                    }
                    break;
                }
            }
            if (!hasRoleResource && !isRoleResourceSaved) {
                RoleResource newRoleResource = new RoleResource();
                OssAdminResource resource = resourceDao.select(resourceId[i]);
                newRoleResource.setResource(resource);
                newRoleResource.setRole(role);
                newRoleResource.setScope(scope[i]);
                role.getResources().add(newRoleResource);
            }
        }
        roleDao.update(role);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getUrlScope(java.lang.Long, java.lang.Long)
     */
    public String getUrlScope(Long roleId, Long resourceId) {
        String scope = roleResourceDao.selectRoleUrlScope(roleId, resourceId, getLoginUser().getUserId());
        try {
            return ScopeAuthorityHelper.analysisSql(scope);
        }
        catch (IllegalArgumentException e) {
            log.error("error roleId:" + roleId + ".error resourceId:" + resourceId);
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.RoleService#getModelListByRole(java.lang.Long)
     */
    public List<String> getModelListByRole(Long roleId) {
        return roleDao.getModelByRole(roleId);
    }
}
