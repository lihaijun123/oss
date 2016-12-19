package com.focustech.oss2008.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.dao.ResourceDao;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.OssAdminRole;

@Repository
public class ResourceDaoImpl extends OssHibernateDaoSupport<OssAdminResource>
		implements ResourceDao<OssAdminResource> {
	@SuppressWarnings("unchecked")
	public List<OssAdminResource> selectAllResources() {
		return getCurrentSession().createQuery("from OssAdminResource").list();
	}

	@SuppressWarnings("unchecked")
	public List<OssAdminResource> selectResourceByType(String type) {
		return getCurrentSession()
				.createQuery("from OssAdminResource where resourceType= ?")
				.setString(0, type).list();
	}
	@SuppressWarnings("unchecked")
	public List<OssAdminResource> selectResourceByType(String type, String active) {
		return getCurrentSession()
		.createQuery("from OssAdminResource where resourceType= ? and active=?")
		.setString(0, type).setString(1, active).list();

	}

	public List<OssAdminResource> selectResourceByTypeAndUser(String resourceType, Long role, String userId) {
        /*String sql = "";
        sql = "select * from oss_role_resource" + role + " WHERE RESOURCE_TYPE =? AND active=1";// AND active=1
*/
		String sql = "";
		sql = "select * from oss_admin_role_resource role_resource "
			+ " left join oss_admin_resource resource "
			+ " on role_resource.resource_id= resource.resource_id "
			+ " where resource.active = 1 "
			+ " and role_resource.role_id = " + role
			+ " and resource.resource_type=?";


        return getJdbcTemplate().query(sql, new ResourceMapper(), resourceType);
    }

	public List<OssAdminResource> selectResourceByTypeAndUser_bak(
			String resourceType, Long role, String userId) {
		/*
		 * List<OssAdminResource> list = null ; try{ // Query query =
		 * this.getCurrentSession().getNamedQuery("p_get_resourceid");
		 * query.setString(0, resourceType); query.setLong(1, role);
		 * query.setString(2, userId);
		 *
		 * list = getCurrentSession(); }catch(Exception ex){
		 * ex.printStackTrace();
		 *
		 * }finally { return list; }
		 */

		List<OssAdminResource> list = new ArrayList<OssAdminResource>();
		try {
			/*
			 * Query query = this.getCurrentSession().createSQLQuery(
			 * "{call p_get_resourceid(?, ?, ?)}"); query.setString(0,
			 * resourceType); query.setLong(1, role); query.setString(2,
			 * userId); query.uniqueResult(); String sql = ""; sql +=
			 * "SELECT A.*, if(B.RESOURCE_ID is null, '', A.RESOURCE_INTERFACE) FLAG FROM"
			 * ; sql +=
			 * " (select distinct * from tmp_menu_sum) A LEFT outer JOIN"; sql
			 * +=
			 * "	  (SELECT RESOURCE_ID FROM OSS_ADMIN_ROLE_RESOURCE C WHERE ROLE_ID = 41 UNION SELECT RESOURCE_ID FROM OSS_ADMIN_USER_RESOURCE D WHERE USER_ID = '00000001') B"
			 * ; sql += "	  ON A.RESOURCE_ID = B.RESOURCE_ID";
			 *
			 * list = getJdbcTemplate().query(sql, new ResourceMapper());
			 */

			CallableStatement callStmt = getCurrentSession().connection()
					.prepareCall("{call p_get_resourceid(?, ?, ?)}");
			callStmt.setString(1, resourceType);
			callStmt.setLong(2, role);
			callStmt.setString(3, userId);
			callStmt.execute();
			ResultSet rs = (ResultSet)callStmt.getResultSet();
			while(rs.next()){
				OssAdminResource ossAdminResource = new OssAdminResource();
				ossAdminResource.setResourceId(rs.getLong(1));
				ossAdminResource.setResourceName(rs.getString(2));
				ossAdminResource.setResourceParentId(rs.getLong(3));
				ossAdminResource.setResourceType(rs.getString(4));
				ossAdminResource.setResourceInterface(rs.getString(5));
				ossAdminResource.setResourceDisplay(rs.getString(6));
				ossAdminResource.setDescription(rs.getString(7));
				ossAdminResource.setActive(rs.getString(8));
				ossAdminResource.setResourceOrder(rs.getLong(9));
				ossAdminResource.setFlag(rs.getString(10));

				list.add(ossAdminResource);
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return list;
		}
		/*
		 * SELECT A.*, DECODE(B.RESOURCE_ID, NULL, '', A.RESOURCE_INTERFACE) FLAG
  FROM (SELECT DISTINCT *
          FROM OSS_ADMIN_RESOURCE
         WHERE RESOURCE_TYPE = 1
           AND ACTIVE = '1'
        CONNECT BY PRIOR RESOURCE_PARENT_ID = RESOURCE_ID
         START WITH RESOURCE_ID IN (SELECT RESOURCE_ID
                                      FROM OSS_ADMIN_ROLE_RESOURCE
                                     WHERE ROLE_ID = 41)) A,
       (SELECT RESOURCE_ID FROM OSS_ADMIN_ROLE_RESOURCE B WHERE ROLE_ID = 41) B
 WHERE A.RESOURCE_ID = B.RESOURCE_ID(+);
		 * getJdbcTemplate().query(sql, new ResourceMapper(), resourceType,
		 * role, userId, role, userId);
		 */}

	public List<OssAdminResource> selectResourceByTypeAndRole(
			String resourceType, Long role) {
		String sql = "select * from oss_admin_resource resource"
			+ " left join oss_admin_role_resource role_resource"
			+ " on resource.resource_id = role_resource.resource_id"
			+ " where resource.active=1 and resource.resource_type = ? and role_id = ?";
		return getJdbcTemplate().query(sql, new ResourceMapper(), resourceType,
				role);
	}

	private static final class ResourceMapper implements
			ParameterizedRowMapper<OssAdminResource> {
		public OssAdminResource mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			OssAdminResource resource = new OssAdminResource();
			resource.setDescription(rs.getString("DESCRIPTION"));
			resource.setResourceDisplay(rs.getString("RESOURCE_DISPLAY"));
			resource.setResourceId(rs.getLong("RESOURCE_ID"));
			resource.setResourceInterface(rs.getString("FLAG"));
			resource.setResourceName(rs.getString("RESOURCE_NAME"));
			resource.setResourceOrder(rs.getLong("RESOURCE_ORDER"));
			resource.setResourceParentId(rs.getLong("RESOURCE_PARENT_ID"));
			resource.setRoles(new HashSet<OssAdminRole>());
			return resource;
		}
	}

	@Override
	public long getNewResourceId() {
		String sql = "SELECT max(resource_id) from oss_admin_resource";
        long id = TCUtil.lv(getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
        return ++id;
	}

	@Override
	public void deleteResource(OssAdminResource adminResource) {
		Long resourceId = adminResource.getResourceId();
		String sql_1 = "delete  from oss_admin_resource where resource_id=" + resourceId;
		String sql_2 = "delete  from oss_admin_role_resource where resource_id=" + resourceId;
		getCurrentSession().createSQLQuery(sql_2).executeUpdate();
		getCurrentSession().createSQLQuery(sql_1).executeUpdate();
	}

	@Override
	public String exportDeleteListSql(Long resoruceId, Long roleId) {
		String sql1 = "delete from oss_admin_role_resource where resource_id=" + resoruceId + " and role_id=" + roleId + ";";
		String sql2 = "delete from oss_admin_resource where resource_id = " + resoruceId + ";";
		return sql1 + "\n" + sql2;
	}
	@Override
	public String exportInsertListSql(Long resoruceId, Long roleId) {
		String sql1 = "insert into `oss_admin_role_resource` (`role_id`, `resource_id`, `data_scope_formula`) values('" + roleId + "','" + resoruceId + "',NULL);";
		OssAdminResource resoruce = select(resoruceId);
		String sql2 = "insert into `oss_admin_resource` (`resource_id`, `resource_name`, `resource_parent_id`, `resource_type`, `resource_interface`, `resource_display`, `description`, `active`, `resource_order`, `flag`)"
			+ " values('" + resoruceId + "','" + resoruce.getResourceName() + "','" + resoruce.getResourceParentId() + "','" + resoruce.getResourceType() + "','" + resoruce.getResourceInterface() + "','" + resoruce.getResourceDisplay() + "','" + resoruce.getDescription() + "','" + resoruce.getActive() + "','" + resoruce.getResourceOrder() + "','" + resoruce.getFlag() + "');";
		return sql1 + "\n" + sql2;
	}
}
