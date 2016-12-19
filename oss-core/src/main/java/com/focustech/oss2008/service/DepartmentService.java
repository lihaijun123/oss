package com.focustech.oss2008.service;

import java.util.List;
import java.util.Map;

import com.focustech.oss2008.exception.service.DepartmentNameDuplicationException;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.model.TreeNode;

/**
 * <li>組織機構Service</li>
 *
 * @author yangpeng 2008-3-27 下午02:16:23 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
public interface DepartmentService {
    /**
     * 新增機構
     *
     * @throws DepartmentNameDuplicationException
     */
    public void addDepartment(OssAdminDepartment department) throws DepartmentNameDuplicationException;

    /**
     * 取得所有機構
     */
    public List<OssAdminDepartment> getAllDepartments();

    /**
     * 根據組織編號，取得一個組織信息
     */
    public OssAdminDepartment getDepartment(String departmentId);
    /**
     *
     * *
     * @param departmentId
     * @return
     */
    public void delete(String departmentId);

    /**
     * 修改一個組織信息
     *
     * @throws DepartmentNameDuplicationException
     */
    public void modifyDepartment(OssAdminDepartment department) throws DepartmentNameDuplicationException;

    /**
     * 獲取啟用的部門
     *
     * @return
     */
    public List<OssAdminDepartment> getTheActiveDepartments();

    /**
     * 根據部門編號,獲取下一層部門信息
     *
     * @param departmentId 部門編號
     * @return
     */
    public List<OssAdminDepartment> getNextLevelSubDepartmentsByParentId(String departmentId);

    /**
     * 根據部門編號，獲取該部門的所有下屬機構。
     *
     * @param departmentId 部門編號
     * @return
     */
    public List<OssAdminDepartment> getAllSubDepartmentsByParentId(String departmentId);

    /**
     * <li>根據當前用戶的角色，從緩存中取得此角色相對的目錄</li>
     * <p>
     * 例如，當前juese為manager，則從緩存中取得manager_menu.xml相對應的menu對象
     */
    public TreeNode getAllDepartmentTree();

    /**
     * 獲取代理商類型
     */
    public List<OssAdminDepartment> getDepartmentByAgentType();

    /**
     * 獲取辦事處類型部門
     *
     * @return
     */
    public List<OssAdminDepartment> getDepartmentByOfficeType();

    /**
     * 根據部門編號獲取對應的部門列表信息
     *
     * @param departmentIds 部門編號集合,部門編號之間用逗號隔開
     * @return
     */
    public List<OssAdminDepartment> getDepartmentsByIds(String departmentIds);

    /**
     * 根據部門編號獲取對應的部門中的人員信息
     *
     * @param departmentList 部門編號集合
     * @return
     */
    public List<OssAdminUser> getDepartmentUserListByIds(List departmentList);

    /**
     * 根據部門編號獲取對應的部門中的有效人員信息
     *
     * @param departmentList
     * @return
     */
    public Map<String, String> getDepartmentUserAbleUserListByIds(List departmentList);

    /**
     * 根據srcDeptId部門編號，檢查是否是targetDeptId部門的下屬機構。
     *
     * @param srcDeptId
     * @param targetDeptId
     * @return
     */
    public boolean checkIfSubDepartmentsByParentId(String srcDeptId, String targetDeptId);
}
