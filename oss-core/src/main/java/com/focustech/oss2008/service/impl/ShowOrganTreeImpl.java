package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.DepartmentDao;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.TreeNode;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.ShowOrganTree;

/**
 * <li>機構樹組件</li>
 *
 * @author jibin 2008-11-20 上午10:33:34
 */
@Service
public class ShowOrganTreeImpl extends AbstractServiceSupport implements ShowOrganTree {
    @Autowired
    private DepartmentDao<OssAdminDepartment> departmentDao;

    public String getOrganByDeptId(String dpartId) {
        List<OssAdminDepartment> departments = departmentDao.selectAllDepartmentsByDpartId(dpartId);
        return prepareOrgan(dpartId, departments);
    }

    /**
     * 為傳過來的機構號準備機構樹
     *
     * @param departmentId
     * @param departments
     */
    protected String prepareOrgan(String dpartId, List<OssAdminDepartment> departments) {
        List<TreeNode> level1Menu = new ArrayList<TreeNode>();
        if (null != departments && departments.size() > 0) {
            for (OssAdminDepartment department : departments) {
                if (dpartId.equals(String.valueOf(department.getDepartmentParentId()))) {
                    TreeNode node = newNode(department);
                    prepareNode(node, departments);
                    level1Menu.add(node);
                }
            }
            sortSubMenu(level1Menu);
        }
        return JSONArray.fromObject(level1Menu).toString();
    }

    /**
     * 尋找當前節點的子結點
     *
     * @param node 當前節點
     * @param departments 所有機構列表
     */
    protected void prepareNode(TreeNode node, List<OssAdminDepartment> departments) {
        List<TreeNode> subNodeList = new ArrayList<TreeNode>();
        if (null != departments && departments.size() > 0) {
            for (OssAdminDepartment department : departments) {
                if (node.getId().equals(String.valueOf(department.getDepartmentParentId()))) {
                    // 是當前節點的子節點
                    TreeNode subNode = newNode(department);
                    prepareNode(subNode, departments);
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

    protected TreeNode newNode(OssAdminDepartment department) {
        TreeNode node = new TreeNode();
        node.setId(String.valueOf(department.getDepartmentId()));
        node.setText(department.getDepartmentName());
        String link = "javascript:window.status='';";
        node.setLink(null);
        node.setHref(link);
        return node;
    }

    protected void sortSubMenu(List<TreeNode> subMenu) {
        Collections.sort(subMenu);
    }
}
