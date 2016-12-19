package com.focustech.oss2008.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.ResourceDao;
import com.focustech.oss2008.dao.RoleResourceDao;
import com.focustech.oss2008.exception.service.ResourceNameDuplicationException;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.RoleResource;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.ResourceService;

/**
 * <li></li>
 *
 * @author chensu 2008-5-13 下午01:35:19
 */
@Service
public class ResourceServiceImpl extends AbstractServiceSupport implements ResourceService {
    @Autowired
    ResourceDao<OssAdminResource> resourceDao;
    @Autowired
    RoleResourceDao<RoleResource> roleResourceDao;

    public void addResource(OssAdminResource resource) throws ResourceNameDuplicationException {
    	resource.setResourceId(resourceDao.getNewResourceId());
    	resource.setFlag(resource.getResourceInterface());
    	resourceDao.insert(resource);
    	insertRoleResource(resource);
    }

	private void insertRoleResource(OssAdminResource resource) {
		OssAdminUser loginUser = getLoginUser();
    	Set<OssAdminRole> roles = loginUser.getRoles();
    	for (OssAdminRole ossAdminRole : roles) {
    		RoleResource roleResource = new RoleResource();
    		roleResource.setRole(ossAdminRole);
    		roleResource.setResource(resource);
    		roleResourceDao.insertOrUpdate(roleResource);
		}
	}

    public List<OssAdminResource> getAllResources() {
        return resourceDao.selectAllResources();
    }

    public OssAdminResource getResource(Long resourceId) {
        return resourceDao.select(resourceId);
    }

    public void modifyResource(OssAdminResource resource) throws ResourceNameDuplicationException {
        OssAdminResource persistant = resourceDao.select(resource.getResourceId());
        persistant.setResourceName(resource.getResourceName());
        persistant.setResourceParentId(resource.getResourceParentId());
        persistant.setResourceType(resource.getResourceType());
        persistant.setResourceInterface(resource.getResourceInterface());
        persistant.setResourceDisplay(resource.getResourceDisplay());
        persistant.setDescription(resource.getDescription());
        persistant.setResourceOrder(resource.getResourceOrder());
        persistant.setActive(resource.getActive());
        persistant.setFlag(resource.getResourceInterface());
        resourceDao.update(persistant);
    }

    public List<OssAdminResource> getResourceByType(String type) {
        List<OssAdminResource> selectResourceByType = resourceDao.selectResourceByType(type);
        if(RESOURCE_TYPE_MENU.equals(type)){
        	OssAdminResource root = new OssAdminResource();
        	root.setResourceId(0L);
        	root.setResourceName("后台管理系统");
        	root.setResourceParentId(-1L);
        	root.setResourceType("1");
        	root.setResourceDisplay("后台管理系统");
        	selectResourceByType.add(root);
        }
		return selectResourceByType;
    }

    public void deleteResource(OssAdminResource resource) {
        resourceDao.deleteResource(resource);
    }

	@Override
	public String exportDeleteListSql(Long resoruceId, Long roleId) {
		return "#删除资源与管理员角色数据#\n" + resourceDao.exportDeleteListSql(resoruceId, roleId) + "\n";
	}

	@Override
	public String exportInsertListSql(Long resoruceId, Long roleId) {
		return "#插入资源与管理员角色数据#\n" + resourceDao.exportInsertListSql(resoruceId, roleId) + "\n";
	}

	@Override
	public List<OssAdminResource> selectResourceByType(String type,
			String active) {
		return resourceDao.selectResourceByType(type, active);
	}
}
