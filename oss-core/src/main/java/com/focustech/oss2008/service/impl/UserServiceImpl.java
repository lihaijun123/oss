package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.acegisecurity.DisabledException;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.DepartmentDao;
import com.focustech.oss2008.dao.ResourceDao;
import com.focustech.oss2008.dao.RoleDao;
import com.focustech.oss2008.dao.UserDao;
import com.focustech.oss2008.dao.UserLoginLogDao;
import com.focustech.oss2008.dao.UserResourceDao;
import com.focustech.oss2008.exception.service.LoginNameDuplicationException;
import com.focustech.oss2008.exception.service.PasswordInvalidException;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminLoginControl;
import com.focustech.oss2008.model.OssAdminLoginLog;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.RoleService;
import com.focustech.oss2008.service.UserService;
import com.focustech.oss2008.utils.PatternUtils;

/**
 * <li>用戶組件</li>
 *
 * @author yangpeng 2008-4-14 下午01:25:39
 */
@Service
public class UserServiceImpl extends AbstractServiceSupport implements UserService {
    @Autowired
    UserDao<OssAdminUser> userDao;
    @Autowired
    DepartmentDao<OssAdminDepartment> departDao;
    @Autowired
    RoleService role;
    @Autowired
    UserResourceDao userResourceDao;
    @Autowired
    ResourceDao<OssAdminResource> resourceDao;
    @Autowired
    RoleDao<OssAdminRole> roleDao;
    @Autowired
    UserLoginLogDao<OssAdminLoginLog> userLoginLogDao;

    /*
     * (non-Javadoc)
     * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails user = userDao.selectUserByLoginName(username);
        if (null == user) {
            log.error("user name :'" + username + "' is null!");
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#insertUser(com.focustech.oss2008.model.OssAdminUser)
     */
    public void addUser(OssAdminUser user) throws LoginNameDuplicationException, PasswordInvalidException {
        checkLoginName(user.getLoginName());
        if (user.isLdapUser()) {
            isValidLdapLoginName(user);
            generateRandomPassword(user);
        }
        else {
            checkPassword(user);
        }
        user.setCreatorId(getLoginUser().getUserId());
        user.setCreatedTime(new Date());
        fillTheUpdateInfo(user);
        user.setUserId(userDao.getNewUserId());
        userDao.insert(user);
    }

    protected void checkPassword(OssAdminUser user) throws PasswordInvalidException {
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new PasswordInvalidException("使用OSS系统用户名、密码登录时,密码不能为空!");
        }
        else if ((user.getPassword().length() < 6) || (user.getPassword().length() > 20)) {
            throw new PasswordInvalidException("使用OSS系统用户名、密码登录时,登录密码长度需在6-20位间!");
        }
        else if (!(PatternUtils.isJavaPatternFound("[a-z_A-Z]+", user.getPassword()) && PatternUtils
                .isJavaPatternFound("[0-9]+", user.getPassword()))) {
            throw new PasswordInvalidException("使用OSS系统用户名、密码登录时,登录密码必须同时含有数字与字母!");
        }
    }

    /**
     * <li>位用戶生成隨機密碼</li>
     * <p>
     * 如果用戶是LDAP認證,則為其生成隨機密碼,用于cookie自動登陸.自動密碼采用8位的大寫字母加數字的組合方式.
     */
    protected void generateRandomPassword(OssAdminUser user) {
        Random random = new Random(System.currentTimeMillis());
        String sRand = "";
        String[] VALIDATE_CHAR_LIB =
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                        "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 8; i++) {
            String rand = VALIDATE_CHAR_LIB[random.nextInt(VALIDATE_CHAR_LIB.length)];
            sRand += rand;
        }
        user.setPassword(sRand);
    }

    /**
     * <li>檢查用戶的ldap登錄名是否合法.</li>
     * <p>
     * 目前的規則是,等填寫的系統登錄名和LDAP登錄名一致時,認為LDAP登錄名合法.
     */
    protected boolean isValidLdapLoginName(OssAdminUser user) {
        return StringUtils.isNotEmpty(user.getLdapUserId()) && user.getLdapUserId().equals(user.getLoginName());
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#selectAllUsers()
     */
    public List<OssAdminUser> getAllUsers() {
        return userDao.selectAllUsers();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#selectUser(java.lang.String)
     */
    public OssAdminUser getUserById(String userId) {
        return userDao.select(userId);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#updateUser(com.focustech.oss2008.model.OssAdminUser)
     */
    public void modifyUser(OssAdminUser user) throws LoginNameDuplicationException, PasswordInvalidException {
        checkLoginName(user.getLoginName(), user.getUserId());
        OssAdminUser user1 = userDao.select(user.getUserId());
        // 如果相應客服的信息發生變化,應該同步前台
        //synchronizedMicService(user, user1);
        if (user.isLdapUser()) {
            isValidLdapLoginName(user);
            generateRandomPassword(user);
        }
        else {
            checkPassword(user);
        }
        user1.setLoginName(user.getLoginName());
        user1.setPassword(user.getPassword());
        user1.setWorkerId(user.getWorkerId());
        user1.setFullname(user.getFullname());
        user1.setAgname(user.getAgname());
        user1.setOssAdminDepartment(user.getOssAdminDepartment());
        user1.setGender(TCUtil.sv(user.getGender()));
        user1.setExtension(user.getExtension());
        user1.setMobileTelephone(user.getMobileTelephone());
        user1.setEmail(user.getEmail());
        user1.setRoles(user.getRoles());
        user1.setActive(user.getActive());
        user1.setDescription(user.getDescription());
        user1.setLdapUserId(user.getLdapUserId());
        fillTheUpdateInfo(user1);
        userDao.update(user1);
    }

    /**
     * 檢查用戶登錄名是否存在,用于修改驗證
     *
     * @param loginName 用戶登錄名
     * @param userId 用戶編號
     * @throws LoginNameDuplicationException 登錄名重復
     */
    protected void checkLoginName(String loginName, String userId) throws LoginNameDuplicationException {
        if (userDao.selectUserByLoginNameAndId(loginName, userId) != null) {
            throw new LoginNameDuplicationException();
        }
    }

    /**
     * 檢查用戶登錄名是否存在,用于添加驗證
     *
     * @param loginName 用戶登錄名
     * @return LoginNameDuplicationException 登錄名重復
     */
    protected void checkLoginName(String loginName) throws LoginNameDuplicationException {
        if (userDao.selectUserByLoginName(loginName) != null) {
            throw new LoginNameDuplicationException();
        }
    }

    private void fillTheUpdateInfo(OssAdminUser user) {
        user.setModifierId(getLoginUser().getUserId());
        user.setModifiedTime(new Date());
    }

    // orgViewType:0︰不顯示，1︰合並顯示，2︰分級顯示
    // orgRange︰ 0︰當前部門，1︰當前部門直屬，2︰當前部門和所有子部門
    // userType: 0:不顯示；1︰顯示正常；2︰顯示所有(包含非正常員工)
    @SuppressWarnings("unchecked")
    public Map getUsersByDepartmentIdAndStatus(String departmentId, String orgViewType, String orgRange,
            String defOrgId, String userId, String userType, String defUserId, String resourceId) {
        Map data = new HashMap();
        log.error("departmentId: " + departmentId);
        log.error("orgViewType: " + orgViewType);
        log.error("orgRange: " + orgRange);
        log.error("defOrgId: " + defOrgId);
        log.error("userId: " + userId);
        log.error("userType: " + userType);
        log.error("defUserId: " + defUserId);
        log.error("resourceId: " + resourceId);
        // 如果機構與用戶都不顯示，則不要做任何的查詢處理
        if ("0".equals(orgViewType) && "0".equals(userType)) {
            return data;
        }
        // 如果沒有指定部門則從cookie部門中權限
        String scope = null;
        if ((departmentId == null) || (departmentId.length() <= 0)) {
            OssAdminUser user = getLoginUser();
            departmentId = user.getOssAdminDepartment().getDepartmentId();
            log.error("user.userid: " + user.getUserId());
            log.error("loginuser.department: " + departmentId);
            // Set<OssAdminRole> roles= user.getRoles();
            if ((resourceId != null) && (resourceId.length() > 0)) {
                long lrId = Long.parseLong(resourceId);
                log.error("user.getUserFirstRole(): " + user.getUserFirstRole());
                log.error("lrId: " + lrId);
                scope = role.getUrlScope(user.getUserFirstRole(), lrId);
                log.error("get scope: " + scope);
                // 沒有數據範圍權限
                if (scope == null) {
                    orgRange = "-1";
                }
                // 所有數據範圍權限
                else if (scope.length() <= 0) {
                    departmentId = Constants.DEPARTMENT_FOCUSTECH;
                    scope = null;
                }
                // 指定數據範圍權限
                else {
                    // 只有自己的，所以也不查詢機構
                    if ("&OWNER_ID".equalsIgnoreCase(scope)) {
                        orgRange = "-1";
                        scope = null;
                    }
                    else {
                        scope = scope.replaceAll("&S_ORG_ID", "'" + departmentId + "'");
                        scope = scope.replaceAll("&OWNER_ID", "'" + user.getUserId() + "'");
                        log.error("scope after replaceAll " + scope);
                    }
                }
            }
        }
        // 查看是否要顯示部門
        String[] departmentIds = departmentId.split(",");
        // if (!"0".equals(orgRange))
        // {
        List<OssAdminDepartment> departs = null;
        departs = departDao.getDepartments(departmentIds, orgRange, scope);
        log.error("departs size: " + departs.size());
        data.put("subDepartments", departs);
        if ("0".equals(orgViewType)) {
        	log.error("orgViewType is 0");
            data.put("subDepartments", new ArrayList());
        }
        else if ((defOrgId != null) && (defOrgId.length() > 0)) {
        	log.error("defOrgId != null) && (defOrgId.length() > 0");
        	log.error("defOrgId" + defOrgId);
            List parents = departDao.getAllParentDepartments(defOrgId);
            data.put("parents", parents);
        }
        // }
        //
        // 如果要顯示用戶信息
        if (!"0".equals(userType)) {
            // 機構不顯示但是要把對應機構的用戶取出來顯示
            if ("0".equals(orgViewType)) {
                departmentIds = new String[departs.size()];
                OssAdminDepartment depart = null;
                for (int i = 0; i < departs.size(); i++) {
                    depart = departs.get(i);
                    departmentIds[i] = depart.getDepartmentId();
                }
            }
            //
            // 缺省顯示用戶類型
            if ((userType == null) || (userType.length() <= 0)) {
                userType = "1";
            }
            if (defUserId == null) {
                defUserId = "";
            }
            // 添加缺省用戶
            if ((userId == null) || (userId.length() <= 0)) {
                userId = defUserId;
            }
            else {
                userId += "," + defUserId;
            }
            String[] userIds = userId.split(",");
            // 安全處理
            if (departmentIds.length <= 0) {
                departmentIds = new String[]{" "};
            }
            if (userIds.length <= 0) {
                userIds = new String[]{" "};
            }
            if ("1".equals(userType)) {
                // 取正常用戶
            	log.error("departmentIds: " + departmentIds);
            	log.error("userIds: " + userIds);
            	log.error("userType: " + userType);
                List<OssAdminUser> users = userDao.getUsersByDepartmentIdAndStatus(departmentIds, userIds, userType);
                data.put("users", users);
            }
            else if ("2".equals(userType)) {
                // 取所有用戶
            	log.error("departmentIds: " + departmentIds);
            	log.error("userIds: " + userIds);
            	log.error("userType: " + userType);
                List<OssAdminUser> users = userDao.getUsersByDepartmentIdAndStatus(departmentIds, userIds, null);
                data.put("users", users);
            }
        }
        return data;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#modifyUserSelf(com.focustech.oss2008.model.OssAdminUser)
     */
    public void modifyUserSelf(OssAdminUser user) throws PasswordInvalidException {
        OssAdminUser user1 = userDao.select(user.getUserId());
        // 如果相應客服的信息發生變化,應該同步前台
        // synchronizedMicService(user, user1);
        if (user1.isLdapUser()) {

            generateRandomPassword(user);
        }
        else {
            checkPassword(user);
        }
        user1.setAgname(user.getAgname());
        user1.setGender(user.getGender());
        user1.setExtension(user.getExtension());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setMobileTelephone(user.getMobileTelephone());
        user1.setFullname(user.getFullname());
        fillTheUpdateInfo(user1);
        userDao.update(user1);
    }

    /**
     * {@inheritDoc}
     */
    public void modifyUserResourceScope(String deletedIdStrs, Long[] resourceIds, String userId) {
        String[] userIds = null;
        if (deletedIdStrs == null) {
            deletedIdStrs = "";
        }
        // 先刪除被刪除的用戶資源權限
        String[] deletedIds = deletedIdStrs.split(",");
        if (deletedIds != null) {
            Long[] ids = new Long[deletedIds.length];
            for (int i = 0; i < ids.length; i++) {
                if ((deletedIds[i] != null) && (deletedIds[i].length() > 0)) {
                    ids[i] = new Long(deletedIds[i]);
                }
                else {
                    ids[i] = new Long(0);
                }
            }
            userIds = new String[deletedIds.length];
            Arrays.fill(userIds, 0, userIds.length, userId);
            userResourceDao.delete(userIds, ids);
        }
        if (resourceIds != null) {
            // 再把要添加的先刪除一下，然後再添加，這樣不會有重復記錄出現
            userIds = new String[resourceIds.length];
            Arrays.fill(userIds, 0, userIds.length, userId);
            userResourceDao.delete(userIds, resourceIds);
            // 添加
            userResourceDao.insert(userIds, resourceIds);
        }
    }

    /**
     * 如果用戶的 中文名字\英文名字\分機號\郵箱\性別 之一發生變化就要同步更新前台客服表信息
     *
     * @param user 發生變化的信息
     * @param user1 發生變化前的信息
     */
    private void synchronizedMicService(OssAdminUser user, OssAdminUser user1) {
        if (!(user.getFullname().equals(user1.getFullname()) && user.getAgname().equals(user1.getAgname())
                && user.getExtension().equals(user1.getExtension()) && user.getEmail().equals(user1.getEmail()) && user
                .getGender().equals(user1.getGender()))) {
            userDao.synchronizeMICInfo(user);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#saveLoginLog(com.focustech.oss2008.model.OssAdminUser,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    public void saveLoginLog(OssAdminUser user, String ip, String model, String remoteName) {
        OssAdminLoginControl loginControl = null;
        //add by lihaijun 先不保存
        user.setLoginControl(null);
        if (null == user.getLoginControl()) {
            /*loginControl = new OssAdminLoginControl();
            loginControl.setUser(user);
            loginControl.setLoginName(user.getLoginName());
            loginControl.setLoginIp(ip);
            loginControl.setUserId(user.getUserId());*/
            user.setLoginControl(null);
        }
        else {
            loginControl = user.getLoginControl();
            loginControl.setLoginIp(ip);
        }
        //
        userDao.update(user);
        //
        OssAdminLoginLog loginLog = new OssAdminLoginLog();
        loginLog.setLoginIp(ip);
        loginLog.setLoginName(user.getLoginName());
        loginLog.setLoginTime(new Date());
        loginLog.setModelName(model);
        loginLog.setRemoteName(remoteName);
        loginLog.setLoginStatus(OssAdminLoginLog.LOGIN_STATUS_SUCCESS);
        userLoginLogDao.insert(loginLog);
    }

    public boolean hasResourceRole(OssAdminUser user, long resourceId) {
        String scope = role.getUrlScope(user.getUserFirstRole(), resourceId);
        if (scope == null) {
            return false;
        }
        return true;
    }

    public boolean hasResourceRole4CurrUser(long resourceId) {
        return hasResourceRole(getLoginUser(), resourceId);
    }

    public void saveLoginFailed(String model, String loginName, String ip, String remoteName, String remark) {
        OssAdminLoginLog loginLog = new OssAdminLoginLog();
        loginLog.setLoginIp(ip);
        loginLog.setLoginName(loginName);
        loginLog.setLoginTime(new Date());
        loginLog.setModelName(model);
        loginLog.setRemoteName(remoteName);
        loginLog.setLoginStatus(OssAdminLoginLog.LOGIN_STATUS_FAILED);
        loginLog.setRemark(remark);
        userLoginLogDao.insert(loginLog);
    }

    public boolean isLoginWithLdap(String loginName) {
        OssAdminUser user = userDao.selectUserByLoginName(loginName);
        if (null == user) {
            throw new UsernameNotFoundException("user name " + loginName + " not found!");
        }
        else if (!OssAdminUser.USER_ENABLED.equals(user.getActive())) {
            throw new DisabledException("the user :" + loginName + " is disabled!");
        }
        else if (StringUtils.isNotEmpty(user.getLdapUserId())) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.UserService#getUserIdByLoginName(java.lang.String)
     */
    public String getUserIdByLoginName(String loginName) {
        //
        return userDao.getUserIdByLoginName(loginName);
    }

	@Override
	public void delete(String userId) {
		userDao.delete(userId);
	}

	@Override
	public List<OssAdminUser> getUsersByDepartment(String departmentId) {
		return userDao.getUsersByDepartment(departmentId);
	}

	@Override
	public boolean hasUser(String roleId) {
		// TODO Auto-generated method stub
		return userDao.hasUser(roleId);
	}
}
