package com.focustech.oss2008.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.focustech.oss2008.dao.OssAdminPortletDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
import com.focustech.oss2008.model.OssAdminPortlet;

/**
 * <li>Portlet DAO實現</li>
 *
 * @author pangyihong
 */
@Repository
public class OssAdminPortletDaoImpl extends OssHibernateDaoSupport<OssAdminPortlet> implements
        OssAdminPortletDao<OssAdminPortlet> {

    /**
     * 根據用戶ID取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserId(String userId) {
        String sql =
                " SELECT A.*, B.QUEUE_NAME_CN FROM OSS_ADMIN_PORTLET A LEFT JOIN OSS_ADMIN_QUEUE B ON A.QUEUE_ID = B.QUEUE_ID WHERE A.USER_ID =? ORDER BY A.ROW_INDEX ";
        return getJdbcTemplate().query(sql, new PortletMapper(), userId);
    }

    /**
     * 根據用戶ID和Portlet類型取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param type Portlet類型
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserIdAndType(String userId, String type) {
        String sql =
                " SELECT A.*, B.QUEUE_NAME_CN FROM OSS_ADMIN_PORTLET A LEFT JOIN OSS_ADMIN_QUEUE B ON A.QUEUE_ID = B.QUEUE_ID WHERE A.USER_ID = ? AND A.PORTLET_TYPE = ? ORDER BY A.ROW_INDEX ";
        return getJdbcTemplate().query(sql, new PortletMapper(), userId, type);
    }

    /**
     * 取得用戶設置的列模式
     *
     * @param userId 用戶ID
     * @return 自定義的Portlet列表
     */
    public String selectPortletColumnMode(String userId) {
        String sql = " SELECT COUNT(*) FROM OSS_ADMIN_PORTLET_COLUMN WHERE USER_ID =? ";
        int count = getJdbcTemplate().queryForInt(sql, userId);
        if (count == 1) {
            sql = " SELECT COLUMN_TYPE FROM OSS_ADMIN_PORTLET_COLUMN WHERE USER_ID =? ";
            return getJdbcTemplate().queryForObject(sql, String.class, userId);
        }
        else {
            return OssAdminPortlet.COLUMN_TYPE_TWO;
        }
    }

    /**
     * 根據用戶ID和Portlet類型列表取得該用戶自定義的Portlet列表
     *
     * @param userId 用戶ID
     * @param types Portlet類型列表
     * @return 自定義的Portlet列表
     */
    public List<OssAdminPortlet> selectPortletByUserIdAndTypes(String userId, String[] types) {
        if ((types == null) || (types.length == 0)) {
            return null;
        }

        StringBuffer sql =
                new StringBuffer(
                        " SELECT A.*, B.QUEUE_NAME_CN FROM OSS_ADMIN_PORTLET A LEFT JOIN OSS_ADMIN_QUEUE B ON A.QUEUE_ID = B.QUEUE_ID WHERE A.USER_ID = ? AND A.PORTLET_TYPE IN(");
        List<Object> params = new ArrayList<Object>();
        params.add(userId);
        for (int i = 0; i < types.length; i++) {
            if (i == types.length - 1) {
                sql.append("?) ");
            }
            else {
                sql.append("?,");
            }
            params.add(types[i]);
        }
        sql.append(" ORDER BY A.ROW_INDEX ");

        return getJdbcTemplate().query(sql.toString(), new PortletMapper(), params.toArray());
    }

    /**
     * 保存用戶自定義的Portlet列表，如果Portlet列表為空，則刪除該用戶原來所有指定類型Portlet
     *
     * @param userId 用戶ID
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param portletType 指定要刪除的Portlet類型，如果為空，則刪除簡單配置的Portlet信息
     * @param portletType 指定要保存的列模式
     * @return 更新記錄數
     */
    public int[] savePortlets(String userId, List<OssAdminPortlet> lstPortlet, String portletType, String columnType) {

        String sql = null;

        // 刪除該用戶原來自定義的Portlet(簡單配置的Portlet)
        if (StringUtils.isEmpty(portletType)) {
            sql = " DELETE FROM OSS_ADMIN_PORTLET WHERE USER_ID = ? AND PORTLET_TYPE IN('1', '2') ";
            getJdbcTemplate().update(sql, userId);
        }
        else {
            sql = " DELETE FROM OSS_ADMIN_PORTLET WHERE USER_ID = ? AND PORTLET_TYPE = ?";
            getJdbcTemplate().update(sql, userId, portletType);
        }

        // 更新列模式
        updatePortletColumnMode(lstPortlet.get(0).getUserId(), columnType);

        if ((lstPortlet == null) || (lstPortlet.size() == 0)) {
            return null;
        }

        // 保存該用戶新的自定義的Portlet
        sql =" INSERT INTO OSS_ADMIN_PORTLET (USER_ID,QUEUE_ID,INCLUDE_QUEUE_ID,ROW_INDEX,COLUMN_INDEX,PORTLET_TYPE,PORTLET_TITLE) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                //" INSERT INTO OSS_ADMIN_PORTLET (PORTLET_ID,USER_ID,QUEUE_ID,INCLUDE_QUEUE_ID,ROW_INDEX,COLUMN_INDEX,PORTLET_TYPE,PORTLET_TITLE) VALUES (S_OSS_ADMIN_PORTLET.nextval, ?, ?, ?, ?, ?, ?, ?)";

        List<Object[]> params = new ArrayList<Object[]>();
        for (OssAdminPortlet portlet : lstPortlet) {
            Object[] values =
                    new Object[]{userId, portlet.getQueueId(), portlet.getIncludeQueueId(), portlet.getRowIndex(),
                            portlet.getColumnIndex(), portlet.getPortletType(), portlet.getPortletTitle()};
            params.add(values);
        }

        return getJdbcTemplate().batchUpdate(sql, params);

    }

    private static final class PortletMapper implements ParameterizedRowMapper<OssAdminPortlet> {
        public OssAdminPortlet mapRow(ResultSet rs, int rowNum) throws SQLException {
            OssAdminPortlet portlet = new OssAdminPortlet();
            portlet.setPortletId(rs.getLong("PORTLET_ID"));
            portlet.setUserId(rs.getString("USER_ID"));
            portlet.setQueueId(rs.getString("QUEUE_ID"));
            portlet.setIncludeQueueId(rs.getString("INCLUDE_QUEUE_ID"));
            portlet.setRowIndex(rs.getLong("ROW_INDEX"));
            portlet.setColumnIndex(rs.getLong("COLUMN_INDEX"));
            portlet.setQueueName(rs.getString("QUEUE_NAME_CN"));
            portlet.setPortletType(rs.getString("PORTLET_TYPE"));
            portlet.setPortletTitle(rs.getString("PORTLET_TITLE"));

            return portlet;
        }
    }

    /**
     * 插入指定的Portlet列表
     *
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param isDeleteAll 新增前是否刪除原有記錄
     * @return 更新結果
     */
    public int[] insertPortlets(List<OssAdminPortlet> lstPortlet, boolean isDeleteAll) {
        if ((lstPortlet == null) || (lstPortlet.size() == 0)) {
            return null;
        }

        String sql = null;
        if (isDeleteAll) {
            sql = " DELETE FROM OSS_ADMIN_PORTLET WHERE USER_ID = ? ";
            getJdbcTemplate().update(sql, lstPortlet.get(0).getUserId());
        }

        sql =
                " INSERT INTO OSS_ADMIN_PORTLET (USER_ID,QUEUE_ID,INCLUDE_QUEUE_ID,ROW_INDEX,COLUMN_INDEX,PORTLET_TYPE,PORTLET_TITLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        " INSERT INTO OSS_ADMIN_PORTLET (PORTLET_ID,USER_ID,QUEUE_ID,INCLUDE_QUEUE_ID,ROW_INDEX,COLUMN_INDEX,PORTLET_TYPE,PORTLET_TITLE) VALUES (S_OSS_ADMIN_PORTLET.nextval, ?, ?, ?, ?, ?, ?, ?)";
        List<Object[]> params = new ArrayList<Object[]>();
        for (OssAdminPortlet portlet : lstPortlet) {
            Object[] values =
                    new Object[]{portlet.getUserId(), portlet.getQueueId(), portlet.getIncludeQueueId(),
                            portlet.getRowIndex(), portlet.getColumnIndex(), portlet.getPortletType(),
                            portlet.getPortletTitle()};
            params.add(values);
        }

        return getJdbcTemplate().batchUpdate(sql, params);
    }

    /**
     * 更新指定的Portlet列表的顯示順序
     *
     * @param lstPortlet 用戶自定義的Portlet列表
     * @param columnType 待更新的列模式,如果不更新列模式，該值為空
     * @return 更新結果
     */
    public int[] updatePortletsOrder(List<OssAdminPortlet> lstPortlet, String columnType) {
        if ((lstPortlet == null) || (lstPortlet.size() == 0)) {
            return null;
        }

        // 更新列模式
        updatePortletColumnMode(lstPortlet.get(0).getUserId(), columnType);

        // 更新Portlet列表的顯示順序
        String sql = " UPDATE OSS_ADMIN_PORTLET SET ROW_INDEX = ?, COLUMN_INDEX = ? WHERE PORTLET_ID = ? ";
        List<Object[]> params = new ArrayList<Object[]>();
        for (OssAdminPortlet portlet : lstPortlet) {
            Object[] values = new Object[]{portlet.getRowIndex(), portlet.getColumnIndex(), portlet.getPortletId()};
            params.add(values);
        }

        return getJdbcTemplate().batchUpdate(sql, params);
    }

    /**
     * 更新列模式
     *
     * @param userId
     * @param columnType
     * @return
     */
    private void updatePortletColumnMode(String userId, String columnType) {
        if (!StringUtils.isEmpty(columnType)) {
            String sql = " DELETE FROM OSS_ADMIN_PORTLET_COLUMN WHERE USER_ID = ? ";
            getJdbcTemplate().update(sql, userId);
            sql = " INSERT INTO OSS_ADMIN_PORTLET_COLUMN (USER_ID,COLUMN_TYPE) VALUES (?, ?) ";
            getJdbcTemplate().update(sql, userId, columnType);
        }
    }
}
