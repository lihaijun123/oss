package com.focustech.oss2008.dao;

import java.util.List;
import java.util.Map;

import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;

/**
 * <li>組織機構DAO</li>
 *
 * @author yangpeng 2008-3-27 下午02:19:28 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
public interface DepartmentDao<T> extends BaseHibernateDao<T> {
    /**
     * 取得所有組織機構
     */
    public List<OssAdminDepartment> selectAllDepartments();

    /**
     * 根據機構名稱獲取機構信息
     *
     * @param departmentName 機構名稱
     * @return
     */
    public OssAdminDepartment selectDepartmentByName(String departmentName);

    /**
     * 根據機構名與機構編號獲取機構信息
     *
     * @param departmentName 機構名稱
     * @param departmentId 機構編號
     * @return
     */
    public OssAdminDepartment selectDepartmentByNameAndId(String departmentName, String departmentId);

    /**
     * 根據部門編號獲取直屬下屬部門信息
     *
     * @param dParentId 部門編號
     * @return
     */
    public List<OssAdminDepartment> selectNextLevelSubDepartmentsByParentId(String dParentId);

    /**
     * 獲取啟用的部門
     *
     * @return
     */
    public List<OssAdminDepartment> selectTheActiveDepartments();

    /**
     * 為兼容老數據，新的組織編號由sequence里取得的數字轉換成字符串後得到
     */
    public String getNewDepartmentId();

    /**
     * 查詢指定部分以及指定情況下的部門信息
     *
     * @param departmentIds 指定部門編號；如果有多個則使用“,”分隔開；如果不指定則查詢當前登錄用戶的部門
     * @param orgType 0︰不顯示部門；1︰顯示當前部門；2︰顯示當前及以下部門；分級顯示部門；3︰顯示當前部門和以下所有部門
     * @return 返回查詢到的部門信息
     */
    public List<OssAdminDepartment> getDepartments(String[] departmentIds, String orgType);

    public List<OssAdminDepartment> getDepartments(String[] departmentIds, String orgType, String scope);

    /**
     * 根據指定部門id查詢出指定部門的所有上級部門
     *
     * @param departmentId 部門ID
     * @return 返回所有上級部門信息
     */
    public List getAllParentDepartments(String departmentId);

    /**
     * 獲取代理商部門
     */
    public List<OssAdminDepartment> getAgentDepartment();

    /**
     * 獲取辦事處部門
     *
     * @return
     */
    public List<OssAdminDepartment> getOfficeDepartment();

    /**
     * 根據部門編號獲取對應的部門列表信息
     *
     * @param departmentIdsCondition 部門編號的查詢條件形式
     * @return
     */
    public List<OssAdminDepartment> selectDepartmentsByIds(String departmentIdsCondition);

    /**
     * 根據部門編號獲取對應的部門信息以及下屬部門信息中的人員
     *
     * @param departmentId 部門編號
     * @return
     */
    public List<OssAdminUser> selectSubAndParentDepartmentsByDepartmentId(List departmentId);

    /**
     * 根據部門編號獲取對應的部門信息以及下屬部門信息中的有效的人員信息
     *
     * @param departmentId
     * @return
     */
    public Map<String, String> selectDepartmentsUserAbleUserByDepartmentId(List departmentId);

    /**
     * 判斷一個部門號是否是另一個部門的下屬部門
     *
     * @param orgNo 預判斷的部門號
     * @param parentNo 指定的上級部門號
     * @return
     */
    public boolean isContained(String orgNo, String parentNo);

    /**
     * 根據部門編號獲取下屬部門信息
     *
     * @param parentNo 指定的部門號
     * @return
     */
    public List<OssAdminDepartment> selectAllDepartmentsByDpartId(String dpartId);

    /**
     * 根據srcDeptId部門編號，檢查是否是targetDeptId部門的下屬機構。
     *
     * @param srcDeptId
     * @param targetDeptId
     * @return
     */
    public boolean checkIfSubDepartmentsByParentId(String srcDeptId, String targetDeptId);
}
