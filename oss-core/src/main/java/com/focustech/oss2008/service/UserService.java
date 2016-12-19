package com.focustech.oss2008.service;

import java.util.List;
import java.util.Map;

import org.acegisecurity.userdetails.UserDetailsService;

import com.focustech.oss2008.exception.service.LoginNameDuplicationException;
import com.focustech.oss2008.exception.service.PasswordInvalidException;
import com.focustech.oss2008.model.OssAdminUser;

/**
 *
 */
public interface UserService extends UserDetailsService {
    /**
     *
     * @throws LoginNameDuplicationException �n��W���_
     * @throws PasswordInvalidException �K�X�D�k
     */
    public void addUser(OssAdminUser user) throws LoginNameDuplicationException, PasswordInvalidException;

    /**
     *
     * @throws LoginNameDuplicationException
     * @throws LoginNameDuplicationException �n��W���_
     */
    public void modifyUser(OssAdminUser user) throws LoginNameDuplicationException, PasswordInvalidException;

    /**
     *
     */
    public List<OssAdminUser> getAllUsers();
    /**
     *
     * *
     * @param departmentId
     * @return
     */
    public List<OssAdminUser> getUsersByDepartment(String departmentId);

    /**
     *
     */
    public OssAdminUser getUserById(String userId);
    /**
     *
     * *
     * @param userId
     */
    public void delete(String userId);

    /**
     * <p/>
     *
     */
    @SuppressWarnings("unchecked")
    public Map getUsersByDepartmentIdAndStatus(String departmentId, String orgViewType, String orgRange,
            String defOrgId, String userId, String userType, String defUserId, String resourceId);

    /**
     *
     * @param user
     */
    public void modifyUserSelf(OssAdminUser user) throws PasswordInvalidException;

    /**
     *
     */
    public void modifyUserResourceScope(String deletedIdStrs, Long[] resourceIds, String userId);

    /**
     */
    public void saveLoginLog(OssAdminUser user, String ip, String model, String remoteName);

    /**
     * @param user
     * @param resourceId
     * @return
     */
    public boolean hasResourceRole(OssAdminUser user, long resourceId);

    /**
     * @param resourceId
     * @return
     */
    public boolean hasResourceRole4CurrUser(long resourceId);

    /**
     */
    public void saveLoginFailed(String model, String loginName, String ip, String remoteName, String remark);

    /**
     */
    public boolean isLoginWithLdap(String loginName);

    /**
     */
    public String getUserIdByLoginName(String loginName);
    /**
     *
     * *
     * @param roleId
     * @return
     */
    public boolean hasUser(String roleId);
}
