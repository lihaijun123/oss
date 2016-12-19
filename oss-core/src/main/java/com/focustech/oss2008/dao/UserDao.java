package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.OssAdminUser;

/**
 * <li>用戶DAO</li>
 *
 * @author yangpeng 2008-4-14 下午06:28:51
 */
public interface UserDao<T> extends BaseHibernateDao<T> {
    /**
     * 根據用戶登陸名，取用戶信息
     */
    public OssAdminUser selectUserByLoginName(String loginName);

    /**
     * 取得所有用戶
     */
    public List<OssAdminUser> selectAllUsers();

    /**
     * 根據用戶登錄名與用戶編號獲取用戶信息
     *
     * @param loginName 用戶登錄名
     * @param userId 用戶編號
     * @return
     */
    public OssAdminUser selectUserByLoginNameAndId(String loginName, String userId);

    /**
     * 取得新的用戶編號
     * <p>
     * 為兼容老數據，新的用戶編號由sequence里取得的數字轉換成字符串後得到
     * </p>
     */
    public String getNewUserId();

    /**
     * 根據部門編號獲取當前部門下的指定狀態的用戶，如果狀態沒有指定則獲取所有用戶
     *
     * @param departmentId 部門編號
     * @param userIds 特定查詢的用戶ID
     * @param status 用戶狀態
     * @return 返回用列表
     */
    public List<OssAdminUser> getUsersByDepartmentIdAndStatus(String[] departmentIds, String[] userIds, String status);

    /**
     * 同步前台客服人員的信息
     *
     * @param serviceUser 客服人員信息
     */
    public void synchronizeMICInfo(OssAdminUser serviceUser);

    /**
     * 獲取AS客服人員信息，組裝AS審核文件清單
     *
     * @param roleId
     * @return
     */
    public List<OssAdminUser> getAsUserInfo(String roleId);

    /**
     * 根據用戶名獲取用戶編號
     *
     * @param loginName
     * @return
     */
    public String getUserIdByLoginName(String loginName);

    public List<OssAdminUser> getUsersByDepartment(String departmentId);

	public boolean hasUser(String roleId);
}
