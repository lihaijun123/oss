package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.RoleResource;
import com.focustech.oss2008.service.ResourceService;

public class ResourceTypeEditor extends PropertyEditorSupport {
    private ResourceService resourceService;

    public ResourceTypeEditor(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Set<RoleResource> set = new HashSet<RoleResource>();
        String[] resources = null;
        int i;
        if (!StringUtils.isEmpty(text)) {
            resources = text.split(",");
            for (i = 0; i < resources.length; i++) {
                OssAdminResource resource = resourceService.getResource(Long.valueOf(resources[i]));
                RoleResource roleResource = new RoleResource();
                roleResource.setResource(resource);
				set.add(roleResource);
            }
            super.setValue(set);
        }
    }
}
