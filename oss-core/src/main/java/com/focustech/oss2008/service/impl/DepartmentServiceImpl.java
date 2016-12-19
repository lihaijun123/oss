package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.DepartmentDao;
import com.focustech.oss2008.exception.service.DepartmentNameDuplicationException;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.TreeNode;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.DepartmentService;

/**
 * <li>組織機構Service實現</li>
 *
 * @author yangpeng 2008-3-27 下午02:18:36 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@Service
public class DepartmentServiceImpl extends AbstractServiceSupport implements DepartmentService {
    @Autowired
    private DepartmentDao<OssAdminDepartment> departmentDao;

    // // 目錄列表,存放所有目錄信息
    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.DepartmentService#insertDepartment(com.focustech.oss2008.model.OssAdminDepartment)
     */
    public void addDepartment(OssAdminDepartment department) throws DepartmentNameDuplicationException {
        checkDepartmentName(department.getDepartmentName());
        department.setDepartmentId(departmentDao.getNewDepartmentId());
        departmentDao.insert(department);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#selectAllDepartments()
     */
    public List<OssAdminDepartment> getAllDepartments() {
        return departmentDao.selectAllDepartments();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#selectDepartment(java.lang.String)
     */
    public OssAdminDepartment getDepartment(String departmentId) {
        return departmentDao.select(departmentId);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.DepartmentService#updateDepartment(com.focustech.oss2008.model.OssAdminDepartment)
     */
    public void modifyDepartment(OssAdminDepartment department) throws DepartmentNameDuplicationException {
        checkDepartmentName(department.getDepartmentName(), department.getDepartmentId());
        OssAdminDepartment persistentDepartment = departmentDao.select(department.getDepartmentId());
        persistentDepartment.setDepartmentName(department.getDepartmentName());
		persistentDepartment.setDepartmentParentId(department.getDepartmentParentId());
        persistentDepartment.setDepartmentType(department.getDepartmentType());
        persistentDepartment.setDepartmentAddress(department.getDepartmentAddress());
        persistentDepartment.setDepartmentContact(department.getDepartmentContact());
        persistentDepartment.setDepartmentPhone(department.getDepartmentPhone());
        persistentDepartment.setDepartmentFax(department.getDepartmentFax());
        persistentDepartment.setActive(department.getActive());
        persistentDepartment.setDescription(department.getDescription());
        departmentDao.update(persistentDepartment);
    }

    /**
     * 檢查部門名稱是否已經存在,用于修改驗證
     *
     * @param departmentName 部門名稱
     * @param departmentId 部門編號
     * @throws DepartmentNameDuplicationException 部門名稱重復
     */
    protected void checkDepartmentName(String departmentName, String departmentId)
            throws DepartmentNameDuplicationException {
        if (departmentDao.selectDepartmentByNameAndId(departmentName, departmentId) != null) {
            throw new DepartmentNameDuplicationException();
        }
    }

    /**
     * 檢查部門名稱是否已經存在,用于添加驗證
     *
     * @param departmentName 部門名稱
     * @throws DepartmentNameDuplicationException 部門名稱重復
     */
    protected void checkDepartmentName(String departmentName) throws DepartmentNameDuplicationException {
        if (departmentDao.selectDepartmentByName(departmentName) != null) {
            throw new DepartmentNameDuplicationException();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#getTheActiveDepartments()
     */
    public List<OssAdminDepartment> getTheActiveDepartments() {
        return departmentDao.selectTheActiveDepartments();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#getDepartmentByParentId(java.lang.String)
     */
    public List<OssAdminDepartment> getNextLevelSubDepartmentsByParentId(String departmentId) {
        return departmentDao.selectNextLevelSubDepartmentsByParentId(departmentId);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#getAllDepartmentByParentId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> getAllSubDepartmentsByParentId(String departmentId) {
        List allDepartments = new LinkedList();
        return getNextLevelDepartment(allDepartments, departmentId);
    }

    /**
     * 遞歸獲取所有下屬機構
     *
     * @param all 所有機構列表
     * @param parentList 當前機構列表
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<OssAdminDepartment> getNextLevelDepartment(List all, String departmentId) {
        all.add(departmentDao.select(departmentId));
        List parentList = departmentDao.selectNextLevelSubDepartmentsByParentId(departmentId);
        for (int i = 0; i < parentList.size(); i++) {
            OssAdminDepartment currentDepartment = (OssAdminDepartment) parentList.get(i);
            // 如果當前部門處于禁用狀態,則不再取它及它的下屬.
            if (currentDepartment.getActive().equals(Constants.DEPARTMENT_FORBIDDEN)) {
                continue;
            }
            List parentListTmp =
                    departmentDao.selectNextLevelSubDepartmentsByParentId(currentDepartment.getDepartmentId());
            if (!parentListTmp.isEmpty()) {
                getNextLevelDepartment(all, currentDepartment.getDepartmentId());
            }
            else {
                all.add(currentDepartment);
            }
        }
        return all;
    }

    /**
     * 讀取resource菜單資源，並初始化菜單列表
     */
    private void initMenuList(List<OssAdminDepartment> departments, List<TreeNode> menuList) {
        for (Iterator<OssAdminDepartment> itr = departments.iterator(); itr.hasNext();) {
            OssAdminDepartment department = itr.next();
            TreeNode menu = new TreeNode();
            menu.setId(String.valueOf(department.getDepartmentId()));
            menu.setText(department.getDepartmentName());
            // menu.setLink(department.getResourceres());
            // 默認信息
            menu.setLeaf(true);
            menu.setCls(TreeNode.MIDDLE_NODE);
            menu.setChildren(null);
            menuList.add(menu);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#getAllDepartmentTree()
     */
    public TreeNode getAllDepartmentTree() {
        List<TreeNode> menuList = new ArrayList<TreeNode>();
        List<OssAdminDepartment> departments = getAllDepartments();
        // 初始化菜單列表
        initMenuList(departments, menuList);
        // 構造菜單樹結構
        constructMenuTree(departments, menuList);
        // 轉換菜單格式，完成與JSON進行交互的格式,放入緩存
        return convertFormat(departments, menuList);
    }

    /**
     * 轉換格式，與JSON接口交互
     */
    private TreeNode convertFormat(List<OssAdminDepartment> departments, List<TreeNode> menuList) {
        TreeNode root = constructRootMenu();
        List<TreeNode> childList = new ArrayList<TreeNode>();
        for (Iterator<OssAdminDepartment> itr = departments.iterator(); itr.hasNext();) {
            OssAdminDepartment resource = itr.next();
            if (isRoot(resource)) {
                childList.add(getMenuInfoByMenuId(String.valueOf(resource.getDepartmentId()), menuList));
            }
        }
        root.setChildren(childList);
        return root;
    }

    /**
     * 構造空結點
     */
    private TreeNode constructRootMenu() {
        TreeNode menu = new TreeNode();
        menu.setCls(TreeNode.LEAF_NODE);
        menu.setId(Constants.ROOT_DEPARTMENT);
        menu.setLeaf(false);
        menu.setLink("");
        menu.setText("");
        menu.setChildren(null);
        return menu;
    }

    /**
     * 基于菜單列表，構造菜單樹結構
     */
    private void constructMenuTree(List<OssAdminDepartment> departments, List<TreeNode> menuList) {
        for (Iterator<OssAdminDepartment> itr = departments.iterator(); itr.hasNext();) {
            OssAdminDepartment department = itr.next();
            // 不為根菜單情況
            if (!isRoot(department)) {
                // 取得父菜單信息
                TreeNode parentMenu = getMenuInfoByMenuId(String.valueOf(department.getDepartmentParentId()), menuList);
                parentMenu.setCls(TreeNode.LEAF_NODE);
                parentMenu.setLeaf(false);
                // 取得當前菜單信息
                TreeNode currentMenu = getMenuInfoByMenuId(String.valueOf(department.getDepartmentId()), menuList);
                currentMenu.setCls(TreeNode.MIDDLE_NODE);
                currentMenu.setLeaf(true);
                // 取得父菜單信息中的子菜單列表,先檢測該目錄是否已存在于父目錄當中
                if ((parentMenu.getChildren() != null) && !parentMenu.getChildren().contains(currentMenu)) {
                    List<TreeNode> childrenMenus = parentMenu.getChildren();
                    childrenMenus.add(currentMenu);
                    parentMenu.setChildren(childrenMenus);
                }
                else if (parentMenu.getChildren() == null) {
                    List<TreeNode> childrenMenus = new ArrayList<TreeNode>();
                    parentMenu.setCls(TreeNode.LEAF_NODE);
                    parentMenu.setLeaf(false);
                    childrenMenus.add(currentMenu);
                    currentMenu.setCls(TreeNode.MIDDLE_NODE);
                    currentMenu.setLeaf(true);
                    parentMenu.setChildren(childrenMenus);
                }
            }
        }
    }

    /**
     * 查詢菜單信息
     */
    @SuppressWarnings("unused")
    private TreeNode getMenuInfoByMenuId(String menuId, List<TreeNode> menuList) {
        for (Iterator<TreeNode> iter = menuList.iterator(); iter.hasNext();) {
            TreeNode menu = iter.next();
            if (menuId.equals(menu.getId())) {
                return menu;
            }
        }
        return null;
    }

    /**
     * 獲取菜單中子菜單列表
     */
    @SuppressWarnings("unused")
    private List<TreeNode> getChildMenuListByMenuId(String menuId, List<TreeNode> menuList) {
        for (Iterator<TreeNode> iter = menuList.iterator(); iter.hasNext();) {
            TreeNode menu = iter.next();
            if (menuId.equals(menu.getId())) {
                return menu.getChildren();
            }
        }
        return null;
    }

    /**
     * 檢測該菜單目錄是否為根菜單，如果不為根菜單，放入上級菜單的子目錄下
     */
    private boolean isRoot(OssAdminDepartment department) {
        return Constants.ROOT_DEPARTMENT.equals(department.getDepartmentParentId()) ? true : false;
    }

    public List<OssAdminDepartment> getDepartmentByAgentType() {
        return departmentDao.getAgentDepartment();
    }

    public List<OssAdminDepartment> getDepartmentByOfficeType() {
        return departmentDao.getOfficeDepartment();
    }

    public List<OssAdminDepartment> getDepartmentsByIds(String departmentIds) {
        return departmentDao.selectDepartmentsByIds(proccessIdFormat(departmentIds.split(",")));
    }

    public List<OssAdminUser> getDepartmentUserListByIds(List departmentList) {
        return departmentDao.selectSubAndParentDepartmentsByDepartmentId(departmentList);
    }

    public Map<String, String> getDepartmentUserAbleUserListByIds(List departmentList) {
        return departmentDao.selectDepartmentsUserAbleUserByDepartmentId(departmentList);
    }

    /**
     * 將選中的客戶ID轉換成查詢條件形式.如:將"3617,3618,3619"轉換成"('3617','3618','3619')" (與customerService中的方法一樣)
     *
     * @param ids
     * @return
     */
    private String proccessIdFormat(String[] ids) {
        /** 客戶編號的查詢條件形式(如:('3415','800000260')) */
        String condition = "";
        for (String id : ids) {
            condition = condition + "'" + id + "',";
        }
        condition = condition.substring(0, condition.length() - 1);
        condition = "(" + condition + ")";
        return condition;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.DepartmentService#checkIfSubDepartmentsByParentId(java.lang.String)
     */
    public boolean checkIfSubDepartmentsByParentId(String srcDeptId, String targetDeptId) {
        return departmentDao.checkIfSubDepartmentsByParentId(srcDeptId, targetDeptId);
    }

	@Override
	public void delete(String departmentId) {
		departmentDao.delete(departmentId);
	}
}
