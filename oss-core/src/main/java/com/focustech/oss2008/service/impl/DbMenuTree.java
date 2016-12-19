package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.TreeNode;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.MenuTree;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.RoleService;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-24 上午11:44:44
 */
@Service
public class DbMenuTree extends AbstractServiceSupport implements MenuTree, InitializingBean {
    public static Map<String, String> cacheMap = new HashMap<String, String>();
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    public String getMenu() {
    	cacheMap.clear();
        //if (!cacheMap.containsKey(super.getLoginUser().getUserId())) {
            getMenuByUser(super.getLoginUser());
        //}
        return cacheMap.get(super.getLoginUser().getUserId());
    }

    @Override
	public String getMenu2() {
    	List<OssAdminResource> resources = resourceService.selectResourceByType("1", "1");
		List<TreeNode> level1Menu = new ArrayList<TreeNode>();
        if (null != resources && resources.size() > 0) {
            for (OssAdminResource resource : resources) {
                if (Constants.ROOT_MENU.equals(String.valueOf(resource.getResourceParentId()))) {
                    TreeNode node = newNode(resource);
                    prepareNode(node, resources);
                    level1Menu.add(node);
                }
            }
            sortSubMenu(level1Menu);
        }
		return JSONArray.fromObject(level1Menu).toString();
	}

    protected void getMenuByUser(OssAdminUser user) {
        try {
			//
			List<OssAdminResource> resources = roleService.getMenuByUserAndRole(user.getUserId(), user.getUserFirstRole());
			prepareMenu(cacheMap, resources, user.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MenuTree#init()
     */
    public void init() {
        cacheMap = new HashMap<String, String>();
         /*Map<String, String> tempMenuMap = new HashMap<String, String>();// �ϥΧ����ܶq�A�Τ_����
         Iterator<OssAdminRole> iterator = (Iterator<OssAdminRole>) roleService.getAllRoles().iterator();
         for (; iterator.hasNext();)
         {
         OssAdminRole role = iterator.next();
         List<OssAdminResource> resources = roleService.getMenuByRole(role.getRoleId());
         prepareMenu(tempMenuMap, resources, role.getRoleName());
         }
         cacheMap = tempMenuMap;*/
    }

    /**
     * 為每個角色準備目錄
     *
     * @param tempMenuMap
     * @param resources
     * @param roleName
     */
    protected void prepareMenu(Map<String, String> tempMenuMap, List<OssAdminResource> resources, String roleName) {
        List<TreeNode> level1Menu = new ArrayList<TreeNode>();
        if (null != resources && resources.size() > 0) {
            for (OssAdminResource resource : resources) {
                if (Constants.ROOT_MENU.equals(String.valueOf(resource.getResourceParentId()))) {
                    TreeNode node = newNode(resource);
                    prepareNode(node, resources);
                    level1Menu.add(node);
                }
            }
            //由于目前新系統只有銷售，則取消其他系統的菜單
            /*
            for (int i = 0; i < level1Menu.size(); i++) {
                TreeNode menu = level1Menu.get(i);
                if (!TreeNode.SAL_ROOT_MENU.equals(menu.getId()) && !TreeNode.AUT_ROOT_MENU.equals(menu.getId())) {
                    level1Menu.remove(i);
                    i--;
                }
            }
            */
            sortSubMenu(level1Menu);
        }
        tempMenuMap.put(roleName, JSONArray.fromObject(level1Menu).toString());
    }

    /**
     * 尋找當前節點的子結點
     *
     * @param node 當前節點
     * @param resources 所有菜單列表
     */
    protected void prepareNode(TreeNode node, List<OssAdminResource> resources) {
        List<TreeNode> subNodeList = new ArrayList<TreeNode>();
        if (null != resources && resources.size() > 0) {
            for (OssAdminResource resource : resources) {
                if (node.getId().equals(String.valueOf(resource.getResourceParentId()))) {
                	// 是當前節點的子節點
                    TreeNode subNode = newNode(resource);
                    prepareNode(subNode, resources);
                    subNodeList.add(subNode);
                }
            }
            if (subNodeList.size() > 0) {
            	// 中間節點
                node.setCls(TreeNode.MIDDLE_NODE);
                node.setLeaf(false);
                node.setChildren(subNodeList);
                sortSubMenu(node.getChildren());
            }
            else {
            	// 葉子節點
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
        String link = resource.getResourceInterface();
        node.setOrder(resource.getResourceOrder());
        if (link == null || link.trim().length() <= 0) {
            link = "javascript:window.status='';";
        }
        node.setLink(null);
        node.setHref(link);
        return node;
    }

    protected void sortSubMenu(List<TreeNode> subMenu) {
        Collections.sort(subMenu);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.MenuTree#refresh()
     */
    public synchronized void refresh() {
        init();
    }

    public void afterPropertiesSet() throws Exception {
        init();
    }

    public synchronized void refreshOne(String userId) {
        cacheMap.remove(userId);
    }
}
