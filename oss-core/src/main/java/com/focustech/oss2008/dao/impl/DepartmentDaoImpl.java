package com.focustech.oss2008.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.DepartmentDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.utils.SqlUtils;

/**
 * <li></li>
 *
 * @author yangpeng 2008-3-27 下午02:20:46 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
@Repository
public class DepartmentDaoImpl extends OssHibernateDaoSupport<OssAdminDepartment> implements
        DepartmentDao<OssAdminDepartment> {
    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectAllDepartments()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> selectAllDepartments() {
        return getCurrentSession().createQuery("from OssAdminDepartment order by departmentName").list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectDepartmentByName(java.lang.String)
     */
    public OssAdminDepartment selectDepartmentByName(String departmentName) {
        return (OssAdminDepartment) getCurrentSession().createQuery("from OssAdminDepartment where departmentName=?")
                .setString(0, departmentName).uniqueResult();
    }

    /**
	 *
	 */
    public final synchronized String getNewDepartmentId() {
        String sql = "SELECT max(department_id) from oss_admin_department";
        long id = TCUtil.lv(getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
        return TCUtil.sv(++id);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectDepartmentByNameAndId(java.lang.String, java.lang.String)
     */
    public OssAdminDepartment selectDepartmentByNameAndId(String departmentName, String departmentId) {
        Query query =
                getCurrentSession().createQuery("from OssAdminDepartment where departmentName=? and departmentId!=?");
        query.setString(0, departmentName);
        query.setString(1, departmentId);
        return (OssAdminDepartment) query.uniqueResult();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectDepartmentByParentId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> selectNextLevelSubDepartmentsByParentId(String parentId) {
        return getCurrentSession().createQuery("from OssAdminDepartment where departmentParentId=?").setString(0,
                parentId).list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectTheActiveDepartments()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> selectTheActiveDepartments() {
        return getCurrentSession().createQuery("from OssAdminDepartment where active=?").setString(0,
                Constants.DEPARTMENT_ACTIVE).list();
    }

    public List<OssAdminDepartment> getDepartments(String[] departmentIds, String orgType) {
        return getDepartments(departmentIds, orgType, null);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#getDepartments(java.lang.String[], java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> getDepartments(String[] departmentIds, String orgType, String scope) {
        List<OssAdminDepartment> departs = null;
        StringBuffer ids = new StringBuffer();
        log.error("departmentIds: " + departmentIds);
        log.error("orgType: " + orgType);
        log.error("scope: " + scope);
        for (int i = 0; (departmentIds != null) && (i < departmentIds.length); i++) {
            if (ids.length() > 0) {
                ids.append(",");
            }
            ids.append("'");
            ids.append(departmentIds[i]);
            log.error("=====================departmentIds[i]"+ departmentIds[i]);
            ids.append("'");
        }
        //
        StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
        sql.append(" DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_PARENT_ID");
        sql.append(" FROM OSS_ADMIN_DEPARTMENT");
        //
        if ("0".equals(orgType)) {
            // 取指定部門
            sql.append(" WHERE DEPARTMENT_ID IN (" + ids + ")");
        }
        else if ("1".equals(orgType)) {
            // 取指定部門以及直屬子部門
            sql.append(" WHERE DEPARTMENT_PARENT_ID IN (" + ids + ")");
        }
        else if ("2".equals(orgType)) {
            if ((scope != null) && (scope.length() > 0)) {
                sql.append(" WHERE DEPARTMENT_PARENT_ID IN (" + scope + ")");
            }
            // 取指定部門以及下屬所有子部門
            sql.append(" CONNECT BY PRIOR DEPARTMENT_ID = DEPARTMENT_PARENT_ID START WITH DEPARTMENT_ID IN(");
            sql.append(ids);
            sql.append(")");
            scope = null;
        }
        else {
            sql.append(" WHERE 1=0");
        }
        //
        if ((scope != null) && (scope.length() > 0)) {
            sql.append(" AND DEPARTMENT_ID IN (" + scope + ")");
        }
        sql.append(" ORDER BY DEPARTMENT_ID,DEPARTMENT_NAME");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = getCurrentSession().connection();
            log.error("sql: " + sql.toString());
            pstmt = conn.prepareStatement(sql.toString());
            //
            res = pstmt.executeQuery();
            departs = new ArrayList<OssAdminDepartment>();
            OssAdminDepartment depart = null;
            while (res.next()) {
                depart = new OssAdminDepartment();
                depart.setDepartmentId(res.getString("DEPARTMENT_ID"));
                depart.setDepartmentName(res.getString("DEPARTMENT_NAME"));
                depart.setDepartmentParentId(res.getString("DEPARTMENT_PARENT_ID"));
                log.error("res.getString(DEPARTMENT_ID)" + res.getString("DEPARTMENT_ID"));
                log.error("res.getString(DEPARTMENT_NAME)" + res.getString("DEPARTMENT_NAME"));
                log.error("res.getString(DEPARTMENT_PARENT_ID)" + res.getString("DEPARTMENT_PARENT_ID"));
                departs.add(depart);
            }
            log.error("departs 's size: " + departs.size());
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
        finally {
            SqlUtils.close(res);
            SqlUtils.close(pstmt);
        }
        return departs;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#getAllParentDepartments(java.lang.String)
     */
    public List getAllParentDepartments(String departmentId) {
        List departs = null;
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(" A.DEPARTMENT_ID, B.DEPARTMENT_NAME, A.DEPARTMENT_PARENT_ID");
        sql.append(" FROM TMP_DEPT_TREETEST A JOIN OSS_ADMIN_DEPARTMENT B ON A.DEPARTMENT_ID = B.DEPARTMENT_ID");
        sql.append(" WHERE  A.DEPARTMENT_ID =?");
/*        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append(" DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_PARENT_ID");
        sql.append(" FROM OSS_ADMIN_DEPARTMENT");
        sql.append(" CONNECT BY PRIOR DEPARTMENT_PARENT_ID = DEPARTMENT_ID  START WITH DEPARTMENT_ID =?");
*/        try {
            SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
            query.setString(0, departmentId);
            departs = query.list();
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
        finally {
        }
        return departs;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#getAgentDepartment()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> getAgentDepartment() {
        return getCurrentSession().createQuery(
                "from OssAdminDepartment where (departmentType=? or departmentType=?) and active=?").setString(0, "1")
                .setString(1, "1").setString(2, "1").list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#getOfficeDepartment()
     */
    @SuppressWarnings("unchecked")
    public List<OssAdminDepartment> getOfficeDepartment() {
        return getCurrentSession().createQuery("from OssAdminDepartment where departmentType=?").setString(0, "2")
                .list();
    }

    public List<OssAdminDepartment> selectDepartmentsByIds(String departmentIdsCondition) {
        return getCurrentSession().createQuery(
                "from OssAdminDepartment where departmentId in " + departmentIdsCondition).list();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#selectSubAndParentDepartmentsByDepartmentId(java.lang.String)
     */
    public List<OssAdminUser> selectSubAndParentDepartmentsByDepartmentId(List departmentId) {
        List userList = null;
        String orgIDs = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        conn = getCurrentSession().connection();
        for (int i = 0; i < departmentId.size(); i++) {
            if (orgIDs.length() > 0) {
                orgIDs += ",";
            }
            orgIDs += "'" + (String) departmentId.get(i) + "'";
        }
        StringBuffer sql = new StringBuffer("select user_id,fullname from oss_admin_user where department_id in ");
        sql.append(" (SELECT DISTINCT  DEPARTMENT_ID FROM OSS_ADMIN_DEPARTMENT CONNECT BY PRIOR");
        sql.append(" DEPARTMENT_ID = DEPARTMENT_PARENT_ID START WITH DEPARTMENT_ID IN (" + orgIDs
                + ")) order by fullname");
        try {
            pstmt = conn.prepareStatement(sql.toString());
            //
            res = pstmt.executeQuery();
            userList = new ArrayList<OssAdminDepartment>();
            OssAdminUser user = null;
            while (res.next()) {
                user = new OssAdminUser();
                user.setUserId(res.getString("user_id"));
                user.setFullname(res.getString("fullname"));
                userList.add(user);
            }
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
        finally {
            SqlUtils.close(res);
            SqlUtils.close(pstmt);
        }
        return userList;
    }

    public Map<String, String> selectDepartmentsUserAbleUserByDepartmentId(List departmentId) {
        Map<String, String> userMap = null;
        String orgIDs = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        conn = getCurrentSession().connection();
        for (int i = 0; i < departmentId.size(); i++) {
            if (orgIDs.length() > 0) {
                orgIDs += ",";
            }
            orgIDs += "'" + (String) departmentId.get(i) + "'";
        }
        StringBuffer sql =
                new StringBuffer("select user_id,fullname,active from oss_admin_user where department_id in ");
        sql.append(" (SELECT DISTINCT  DEPARTMENT_ID FROM OSS_ADMIN_DEPARTMENT CONNECT BY PRIOR");
        sql.append(" DEPARTMENT_ID = DEPARTMENT_PARENT_ID START WITH DEPARTMENT_ID IN (" + orgIDs
                + ")) order by fullname");
        try {
            pstmt = conn.prepareStatement(sql.toString());
            //
            res = pstmt.executeQuery();
            userMap = new HashMap<String, String>();
            while (res.next()) {
                if (Constants.LOGIC_TRUE.equals(res.getString("active"))) {
                    userMap.put(res.getString("user_id"), res.getString("fullname"));
                }
            }
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
        finally {
            SqlUtils.close(res);
            SqlUtils.close(pstmt);
        }
        return userMap;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#isContained(java.lang.String, java.lang.String)
     */
    public boolean isContained(String orgNo, String parentNo) {
        Object result = null;
        Query query = this.getCurrentSession().createSQLQuery("{call p_get_tree(?, ?)}");
        query.setString(0, orgNo);
        query.setString(1, parentNo);
        result = query.uniqueResult();
        return result == null ? false : true;
    }

    public List<OssAdminDepartment> selectAllDepartmentsByDpartId(String dpartId) {
        String sql =
                "SELECT T.DEPARTMENT_ID, T.DEPARTMENT_NAME, T.DEPARTMENT_PARENT_ID"
                        + " FROM OSS_ADMIN_DEPARTMENT T WHERE T.ACTIVE='1' AND T.DEPARTMENT_ID = ?";
        return getJdbcTemplate().query(sql, new DepartmentMapper(), dpartId);
    }
    private static final class DepartmentMapper implements ParameterizedRowMapper<OssAdminDepartment> {
        public OssAdminDepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
            OssAdminDepartment department = new OssAdminDepartment();
            department.setDepartmentId(rs.getString("DEPARTMENT_ID"));
            department.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
            department.setDepartmentParentId(rs.getString("DEPARTMENT_PARENT_ID"));
            return department;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.dao.DepartmentDao#checkIfSubDepartmentsByParentId(java.lang.String, java.lang.String)
     */
    public boolean checkIfSubDepartmentsByParentId(String srcDeptId, String targetDeptId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT COUNT(1) FROM (SELECT T.DEPARTMENT_ID FROM OSS_ADMIN_DEPARTMENT T ");
        sb.append("WHERE T.ACTIVE = '1' CONNECT BY PRIOR T.DEPARTMENT_ID = T.DEPARTMENT_PARENT_ID ");
        sb.append("START WITH T.DEPARTMENT_ID = ?) WHERE DEPARTMENT_ID = ? ");
        return getJdbcTemplate().queryForInt(sb.toString(), targetDeptId, srcDeptId) > 0;
    }
}
