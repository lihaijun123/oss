package com.focustech.oss2008.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.UserDao;
import com.focustech.oss2008.exception.OssUncheckedException;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.utils.SqlUtils;

/**
 * <li>用戶DAO實現</li>
 *
 * @author yangpeng 2008-4-14 下午06:30:40
 */
@Repository
public class UserDaoImpl extends OssHibernateDaoSupport<OssAdminUser> implements UserDao<OssAdminUser> {
    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.UserDao#selectUserByLoginName(java.lang.String)
     */
    public OssAdminUser selectUserByLoginName(String loginName) {
        return (OssAdminUser) getCurrentSession().createQuery("from OssAdminUser where loginName = ? and active = 1").setString(0,
                loginName).uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.UserDao#selectUserByLoginNameAndId(java.lang.String, java.lang.String)
     */
    public OssAdminUser selectUserByLoginNameAndId(String loginName, String userId) {
        Query query = getCurrentSession().createQuery("from OssAdminUser where loginName=? and userId!=?");
        query.setString(0, loginName);
        query.setString(1, userId);
        return (OssAdminUser) query.uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.UserDao#selectAllUsers()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminUser> selectAllUsers() {
        return getCurrentSession().createQuery("from OssAdminUser").list();
    }

    /**
     * 為兼容老數據，新的用戶編號由sequence里取得的數字轉換成字符串後得到
     */
    public final String getNewUserId() {
        String sql = "SELECT max(user_id) from oss_admin_user";
        long id = TCUtil.lv(getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
        return TCUtil.sv(++id);
    }

    /**
	 *
	 */
    public List<OssAdminUser> getUsersByDepartmentIdAndStatus(String[] departmentIds, String[] userIds, String status) {
        StringBuffer depIds = new StringBuffer();
        for (int i = 0; (departmentIds != null) && (i < departmentIds.length); i++) {
            if (depIds.length() > 0) {
                depIds.append(",");
            }
            depIds.append("'");
            depIds.append(departmentIds[i]);
            depIds.append("'");
        }
        //
        StringBuffer ids = new StringBuffer();
        for (int i = 0; (userIds != null) && (i < userIds.length); i++) {
            if (ids.length() > 0) {
                ids.append(",");
            }
            ids.append("'");
            ids.append(userIds[i]);
            ids.append("'");
        }
        List<OssAdminUser> users = null;
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(" USER_ID,DEPARTMENT_ID,WORKER_ID,GENDER,FULLNAME,AGNAME,LOGIN_NAME,EMAIL,EXTENSION");
        sql.append(" ,MOBILE_TELEPHONE,LDAP_USER_ID,DESCRIPTION,ACTIVE,CREATOR_ID,CREATED_TIME,MODIFIER_ID");
        sql.append(" ,MODIFIED_TIME FROM OSS_ADMIN_USER");
        sql.append(" WHERE (DEPARTMENT_ID IN (" + depIds + ") AND (ACTIVE=? OR ? IS NULL)) OR (USER_ID IN (" + ids
                + "))");
        sql.append(" ORDER BY FULLNAME");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = getCurrentSession().connection();
            pstmt = conn.prepareStatement(sql.toString());
            log.error("sql: " + sql);
            log.error("status: " + status);
            pstmt.setString(1, status);
            pstmt.setString(2, status);
            res = pstmt.executeQuery();
            users = new ArrayList<OssAdminUser>();
            OssAdminUser user = null;
            while (res.next()) {
                user = new OssAdminUser();
                user.setUserId(res.getString("USER_ID"));
                user.setWorkerId(res.getString("WORKER_ID"));
                user.setGender(res.getString("GENDER"));
                user.setFullname(res.getString("FULLNAME"));
                user.setAgname(res.getString("AGNAME"));
                user.setLoginName(res.getString("LOGIN_NAME"));
                user.setEmail(res.getString("EMAIL"));
                user.setExtension(res.getString("EXTENSION"));
                user.setMobileTelephone(res.getString("MOBILE_TELEPHONE"));
                user.setLdapUserId(res.getString("LDAP_USER_ID"));
                user.setDescription(res.getString("DESCRIPTION"));
                user.setActive(res.getString("ACTIVE"));
                user.setCreatorId(res.getString("CREATOR_ID"));
                // user.setCreatedTime(res.getDate("CREATED_TIME"));
                user.setModifierId(res.getString("MODIFIER_ID"));
                // user.setModifiedTime(res.getDate("MODIFIED_TIME"));
                users.add(user);
            }
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
        finally {
            SqlUtils.close(res);
            SqlUtils.close(pstmt);
        }
        return users;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.UserDao#synchronizeMICInfo(com.focustech.oss2008.model.OssAdminUser)
     */
    public void synchronizeMICInfo(OssAdminUser serviceUser) {
        String sql =
                "UPDATE MIC2005.CORE_CUSTOMER_SERVICE_INFO SET "
                        + "CS_NAME_CN = ?, CS_NAME_EN = ?, CS_GENDER = ?, CS_EXTENSION = ?, CS_EMAIL = ? WHERE CS_OSS_ID = ?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, serviceUser.getFullname());
        query.setString(1, serviceUser.getAgname());
        // 老系統中0:女1:男,新系統中2:女1:男,因此需要做個轉換
        query.setString(2, serviceUser.getGender().equals(Constants.SEX_FEMALE) ? "0" : "1");
        query.setString(3, serviceUser.getExtension());
        query.setString(4, serviceUser.getEmail());
        query.setString(5, serviceUser.getUserId());
        query.executeUpdate();
    }

    public List<OssAdminUser> getAsUserInfo(String roleId) {
        String sql =
                "SELECT U.FULLNAME,U.EXTENSION,U.EMAIL FROM OSS_ADMIN_USER_ROLE R,OSS_ADMIN_USER U WHERE R.USER_ID=U.USER_ID AND U.ACTIVE='1' AND R.ROLE_ID=?";
        return getJdbcTemplate().query(sql, new AsUserInfoMapper(), roleId);
    }
    private class AsUserInfoMapper implements ParameterizedRowMapper<OssAdminUser> {
        public OssAdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            OssAdminUser user = new OssAdminUser();
            user.setFullname(rs.getString("FULLNAME"));
            user.setExtension(rs.getString("EXTENSION"));
            user.setEmail(rs.getString("EMAIL"));
            return user;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.UserDao#getUserIdByLoginName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String getUserIdByLoginName(String loginName) {
        String sql = " from OssAdminUser u where u.loginName=? ";
        List<OssAdminUser> users = new ArrayList<OssAdminUser>();
        users = getCurrentSession().createQuery(sql).setString(0, loginName).list();
        if (users.size() == 0) {
            return "";
        }
        else if (users.size() > 1) {
            throw new OssUncheckedException("該用戶名(" + loginName + ")不唯一！");
        }
        else {
            return users.get(0).getUserId();
        }

    }

	@Override
	public List<OssAdminUser> getUsersByDepartment(String departmentId) {
		Criteria c = getCurrentSession().createCriteria(OssAdminUser.class.getName());
		c.createAlias("ossAdminDepartment", "ossAdminDepartment");
		c.add(Restrictions.eq("ossAdminDepartment.departmentId", departmentId));
		return c.list();
	}

	@Override
	public boolean hasUser(String roleId) {
		String sql = "select count(record_id) from oss_admin_user_role where role_id=" + roleId;
		int list = TCUtil.iv(getCurrentSession().createSQLQuery(sql).uniqueResult());
		return list > 0;
	}
}
