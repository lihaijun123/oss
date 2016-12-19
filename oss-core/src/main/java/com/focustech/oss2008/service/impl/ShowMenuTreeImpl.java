package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.TreeNode;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.ShowMenuTree;

/**
 *
 */
@Service
public class ShowMenuTreeImpl implements ShowMenuTree {

    @Autowired
    private RoleService roleService;

    public String getMenuByRole(Long roleId) {
        List<OssAdminResource> resources = roleService.getResourceByRole(roleId, ResourceService.RESOURCE_TYPE_MENU);
        return prepareMenu(resources);
    }

    public String getMenuByUser(OssAdminUser user) {
        List<OssAdminResource> resources = roleService.getMenuByUserAndRole(user.getUserId(), user.getUserFirstRole());
        return prepareMenu(resources);
    }

    /**
     *
     * @param tempMenuMap
     * @param resources
     * @param roleName
     */
    protected String prepareMenu(List<OssAdminResource> resources) {
        List<TreeNode> level1Menu = new ArrayList<TreeNode>();
        if (null != resources && resources.size() > 0) {
            for (OssAdminResource resource : resources) {
                if (Constants.ROOT_MENU.equals(String.valueOf(resource.getResourceParentId()))) {
                    TreeNode node = newNode(resource);
                    prepareNode(node, resources);
                    level1Menu.add(node);
                }
            }
            // for (int i = 0; i < level1Menu.size(); i++)
            // {
            // TreeNode menu = level1Menu.get(i);
            // if (!TreeNode.SAL_ROOT_MENU.equals(menu.getId()) && !TreeNode.AUT_ROOT_MENU.equals(menu.getId()))
            // {
            // level1Menu.remove(i);
            // i--;
            // }
            // }
            sortSubMenu(level1Menu);
        }
        return JSONArray.fromObject(level1Menu).toString();
    }

    /**
     */
    protected void prepareNode(TreeNode node, List<OssAdminResource> resources) {
        List<TreeNode> subNodeList = new ArrayList<TreeNode>();
        if (null != resources && resources.size() > 0) {
            for (OssAdminResource resource : resources) {
                if (node.getId().equals(String.valueOf(resource.getResourceParentId()))) {
                    TreeNode subNode = newNode(resource);
                    prepareNode(subNode, resources);
                    subNodeList.add(subNode);
                }
            }
            if (subNodeList.size() > 0) {
                node.setCls(TreeNode.MIDDLE_NODE);
                node.setLeaf(false);
                node.setChildren(subNodeList);
                sortSubMenu(node.getChildren());
            }
            else {
                node.setCls(TreeNode.LEAF_NODE);
                node.setLeaf(true);
                node.setChildren(null);
            }
        }
    }

    protected TreeNode newNode(OssAdminResource resource) {
        TreeNode node = new TreeNode();
        node.setId(String.valueOf(resource.getResourceId()));
        node.setText(resource.getResourceName());
        node.setOrder(resource.getResourceOrder());
        String link = "javascript:window.status='';";
        node.setLink(null);
        node.setHref(link);
        return node;
    }

    protected void sortSubMenu(List<TreeNode> subMenu) {
        Collections.sort(subMenu);
    }
}
