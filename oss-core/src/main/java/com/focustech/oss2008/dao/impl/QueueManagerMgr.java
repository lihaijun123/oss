package com.focustech.oss2008.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.focustech.uitool.framework.bc.QueryMgr;
import com.focustech.uitool.framework.utils.DBTools;
import com.focustech.uitool.framework.utils.MapTools;
import com.focustech.uitool.framework.utils.StringTools;
import com.focustech.uitool.list.FunctionBean;
import com.focustech.uitool.list.dt.ParsedSqlBean;
import com.focustech.uitool.list.utils.UIToolExeSqlTools;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-7-22 上午10:26:05
 */
public class QueueManagerMgr extends QueryMgr {
    public static final String ROLE = QueueManagerMgr.class.getName();

    /**
     * 獲取隊列對應數據
     *
     * @param funcId 隊列對應的資源ID
     * @param reqParam 當前環境參數對象
     * @return 返回當前隊列對應結束的數量
     * @throws Exception 未知異常
     */
    public int getQueuerInfos(long funcId, Map reqParam) throws Exception {
        getLogger().info(ROLE + "::getQueuerInfos(long,Map)->Enter");
        int iRet = 0;
        FunctionBean funcBean = UIToolExeSqlTools.getFunction(funcId, "0000");
        if (funcBean == null) {
            return -1;
        }
        ParsedSqlBean psb = UIToolExeSqlTools.getParsedSql(funcBean, reqParam, null);
        if (psb == null) {
            return -1;
        }
        String sql = psb.getSql().toString();
        Object[] args = psb.getArgs();
        if(funcId!=669&&funcId!=670){
        	sql = "SELECT COUNT(*) COUNT_NUM FROM (" + sql + ")a";
        }
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = getConnection().prepareStatement(sql);
            for (int i = 1; (args != null) && (i <= args.length); i++) {
                pstmt.setObject(i, args[i - 1]);
            }
            res = pstmt.executeQuery();
            if(funcId!=669&&funcId!=670){
            if (res.next()) {
                iRet = res.getInt("COUNT_NUM");
            }
            }else{
            	iRet=res.getRow();
            }
        }
        catch (Exception e) {
            getLogger().error(
                    ROLE + "::getQueuerInfos(long,Map)->excep;ERR-SQL=[" + sql + ",ARGS="
                            + StringTools.toString(args, ",") + "]");
            throw e;
        }
        finally {
            DBTools.close(res);
            DBTools.close(pstmt);
        }
        getLogger().info(ROLE + "::getQueuerInfos(long,Map)->Exit");
        return iRet;
    }

    public List<Map> getQueuerInfos(String roleId, String nodeId) throws Exception {
        getLogger().info(ROLE + "::getQueuerInfos(String)->Enter");
        //StringBuffer sql = new StringBuffer("SELECT QUEUE_ID, QUEUE_PARENT_ID, A.RESOURCE_ID");
        /*sql.append(", QUEUE_NAME_CN, QUEUE_NAME_EN, QUEUE_NODE_FLAG, SHOW_NUM, B.RESOURCE_INTERFACE,c.role_id");
        sql.append(" FROM OSS_ADMIN_QUEUE A");
        sql.append(" LEFT JOIN OSS_ADMIN_RESOURCE B ON A.RESOURCE_ID = B.RESOURCE_ID AND B.ACTIVE = 1");
        sql.append(" LEFT JOIN OSS_ADMIN_ROLE_RESOURCE C ON B.RESOURCE_ID = C.RESOURCE_ID AND C.ROLE_ID = ?");
        sql.append(" WHERE A.ACTIVE = 1");
        sql
                .append(" AND ((A.RESOURCE_ID IS NULL AND C.ROLE_ID IS NULL) OR (A.RESOURCE_ID IS NOT NULL AND C.ROLE_ID IS NOT NULL))");
        sql.append(" CONNECT BY PRIOR QUEUE_ID = QUEUE_PARENT_ID START WITH QUEUE_ID =?");
        sql.append(" ORDER BY QUEUE_PARENT_ID,QUEUE_ORDER ");*/
        StringBuffer sql = new StringBuffer("call p_get_queue(?,?)");
        List<Map> ret = query(sql, new Object[]{roleId, nodeId});
        getLogger().info(ROLE + "::getQueuerInfos(String)->Exit");
        return ret;
    }

    /**
     * 根據隊列ID取得隊列相關信息
     *
     * @param queueId 隊列ID
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getQueueInfoByNodeId(String queueId) throws Exception {
        getLogger().info(ROLE + "::getQueueInfoByNodeId(String)->Enter");
        StringBuffer sql = new StringBuffer("SELECT QUEUE_ID, QUEUE_PARENT_ID, A.RESOURCE_ID");
        sql.append(", QUEUE_NAME_CN, QUEUE_NAME_EN, QUEUE_NODE_FLAG, SHOW_NUM, B.RESOURCE_INTERFACE ");
        sql.append(" FROM OSS_ADMIN_QUEUE A");
        sql.append(" LEFT JOIN OSS_ADMIN_RESOURCE B ON A.RESOURCE_ID = B.RESOURCE_ID AND B.ACTIVE = 1");
        sql.append(" WHERE A.ACTIVE = 1");
        sql.append(" AND A.QUEUE_ID =?");
        List<Map> ret = query(sql, new Object[]{queueId});
        getLogger().info(ROLE + "::getQueueInfoByNodeId(String)->Exit");
        return ret;
    }

    /**
     * 根據角色ID，隊列ID取得所有含有資源ID的隊列信息
     *
     * @param roleId
     * @param queueId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getQueueInfoByRoleIdAndQueueId(String roleId, String queueId) throws Exception {
        getLogger().info(ROLE + "::getQueueInfoByRoleIdAndQueueId(String, String)->Enter");
        List<Map> ret = getQueuerInfos(roleId, queueId);
        if ((ret != null) && (ret.size() > 0)) {
            for (Map item : ret) {
                String itemNodeFlag = MapTools.getString(item, "QUEUE_NODE_FLAG");
                String itemQueueId = MapTools.getString(item, "QUEUE_ID");
                if ("0".equals(itemNodeFlag) && !hasLeafNode(roleId, itemQueueId)) {
                    ret.remove(item);
                }
            }
        }
        getLogger().info(ROLE + "::getQueueInfoByRoleIdAndQueueId(String, String)->Exit");
        return ret;
    }

    /**
     * check指定的目錄節點下面是否有葉子節點(在權限範圍內)
     *
     * @param queueId
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean hasLeafNode(String roleId, String queueId) throws SQLException {
        getLogger().info(ROLE + "::hasLeafNode(String)->Enter");
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) CHILD_NUM ");
        sql.append(" FROM OSS_ADMIN_QUEUE A ");
        sql.append(" LEFT JOIN OSS_ADMIN_ROLE_RESOURCE B ON A.RESOURCE_ID = B.RESOURCE_ID AND B.ROLE_ID = ? ");
        sql.append(" WHERE A.ACTIVE = '1' ");
        sql.append(" AND A.QUEUE_NODE_FLAG = '1' ");
        sql.append(" CONNECT BY PRIOR A.QUEUE_ID = A.QUEUE_PARENT_ID START WITH A.QUEUE_ID = ? ");
        List<Map> ret = query(sql, new Object[]{roleId, queueId});
        if ((ret != null) && (ret.size() > 0)) {
            for (Map item : ret) {
                int childNum = MapTools.getInt(item, "CHILD_NUM", 0);
                if (childNum > 1) {
                    return true;
                }
            }
        }
        getLogger().info(ROLE + "::hasLeafNode(String)->Exit");
        return false;
    }

    /**
     * 根據隊列ID，以及該隊列下指定的子隊列ID，取得相關隊列信息
     *
     * @param queueId 隊列ID
     * @param includeQueueIds 指定的子隊列ID
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getQueuerInfoByQueueId(String queueId, String includeQueueIds) throws Exception {
        getLogger().info(ROLE + "::getQueuerInfoByQueueId(String, String)->Enter");
        /*StringBuffer sql = new StringBuffer("SELECT QUEUE_ID, QUEUE_PARENT_ID, A.RESOURCE_ID");
        sql.append(", QUEUE_NAME_CN, QUEUE_NAME_EN, QUEUE_NODE_FLAG, SHOW_NUM, B.RESOURCE_INTERFACE");
        sql.append(" FROM OSS_ADMIN_QUEUE A");
        sql.append(" LEFT JOIN OSS_ADMIN_RESOURCE B ON A.RESOURCE_ID = B.RESOURCE_ID AND B.ACTIVE = 1");
        sql.append(" WHERE A.ACTIVE = 1 ");
        List<Object> params = new ArrayList<Object>();
        if ((includeQueueIds != null) && (includeQueueIds.length() > 0)) {
            sql.append(" AND A.QUEUE_ID IN(");

            String[] arrIncludeQueueId = includeQueueIds.split(",");
            for (int i = 0; i < arrIncludeQueueId.length; i++) {
                String includeQueueId = arrIncludeQueueId[i];
                if (i == arrIncludeQueueId.length - 1) {
                    sql.append("?) ");
                }
                else {
                    sql.append("?,");
                }
                params.add(includeQueueId);
            }
        }

        if ((queueId != null) && (queueId.length() > 0) && !"null".equalsIgnoreCase(queueId)) {
            sql.append(" CONNECT BY PRIOR QUEUE_ID = QUEUE_PARENT_ID START WITH QUEUE_ID =? ");
            params.add(queueId);
        }

        sql.append(" ORDER BY QUEUE_PARENT_ID,QUEUE_ORDER ");
        List<Map> ret = query(sql, params.toArray());*/
        StringBuffer sql = new StringBuffer("call p_get_queueInfoByQueueId(?,?)");
        List<Map> ret = query(sql, new Object[]{queueId,includeQueueIds});
        getLogger().info(ROLE + "::getQueuerInfoByQueueId(String, String)->Exit");
        return ret;
    }

    /**
     * 根據登錄者權限，取得所有葉子節點，以及對應的父節點
     *
     * @param roleId
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAllQueueByRoleId(String roleId) throws SQLException {
        getLogger().info(ROLE + "::getAllQueueByRoleId(String)->Enter");

        List<Map> ret = new ArrayList<Map>();
        StringBuffer sql = new StringBuffer("SELECT A.QUEUE_ID, A.QUEUE_PARENT_ID, A.RESOURCE_ID, A.QUEUE_NAME_CN, ");
        sql
                .append(" A.QUEUE_NAME_CN, A.QUEUE_NAME_EN, A.QUEUE_NODE_FLAG, A.SHOW_NUM, B.RESOURCE_INTERFACE, c.role_id ");
        sql.append(" FROM OSS_ADMIN_QUEUE A, OSS_ADMIN_RESOURCE B, OSS_ADMIN_ROLE_RESOURCE C ");
        sql.append(" WHERE A.ACTIVE = '1' AND A.QUEUE_NODE_FLAG = '1' ");
        sql.append(" AND A.RESOURCE_ID = B.RESOURCE_ID AND B.ACTIVE = '1' ");
        sql.append(" AND A.RESOURCE_ID = C.RESOURCE_ID AND C.ROLE_ID = ? ");
        List<Map> lstLeafQueue = query(sql, new Object[]{roleId});

        if ((lstLeafQueue != null) && (lstLeafQueue.size() > 0)) {
            List<String> lstQueueId = new ArrayList<String>();
            for (Map item : lstLeafQueue) {
                String queueId = MapTools.getString(item, "QUEUE_ID");
                String queueParentId = MapTools.getString(item, "QUEUE_PARENT_ID");
                List<Map> lstParentQueue = getAllParentQueue(queueParentId);
                if (!lstQueueId.contains(queueId)) {
                    lstQueueId.add(queueId);
                    ret.add(item);
                }

                if ((lstParentQueue != null) && (lstParentQueue.size() > 0)) {
                    for (Map parentItem : lstParentQueue) {
                        String parentQueueId = MapTools.getString(parentItem, "QUEUE_ID");
                        if (!lstQueueId.contains(parentQueueId)) {
                            lstQueueId.add(parentQueueId);
                            ret.add(parentItem);
                        }
                    }
                }

            }
        }

        getLogger().info(ROLE + "::getAllQueueByRoleId(String)->Exit");
        return ret;
    }

    /**
     * 根據指定的節點ID，取得所有的父節點
     *
     * @param queueId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAllParentQueue(String queueId) throws SQLException {
        getLogger().info(ROLE + "::getAllParentQueue(String)->Enter");
        StringBuffer sql = new StringBuffer("SELECT QUEUE_ID, QUEUE_PARENT_ID, A.RESOURCE_ID, QUEUE_NAME_CN, ");
        sql
                .append(" QUEUE_NAME_EN, QUEUE_NODE_FLAG, SHOW_NUM, B.RESOURCE_INTERFACE, c.role_id, D.RESOURCE_ID RESOURCE_ID_EXT ");
        sql.append(" FROM OSS_ADMIN_QUEUE A ");
        sql.append(" LEFT JOIN OSS_ADMIN_RESOURCE B ON A.RESOURCE_ID = B.RESOURCE_ID AND B.ACTIVE = '1' ");
        sql.append(" LEFT JOIN OSS_ADMIN_ROLE_RESOURCE C ON B.RESOURCE_ID = C.RESOURCE_ID ");
        sql.append(" LEFT JOIN OSS_ADMIN_RESOURCE D ON A.QUEUE_NAME_CN = D.RESOURCE_NAME ");
        sql.append(" WHERE A.ACTIVE = '1' ");
        sql.append(" CONNECT BY PRIOR A.QUEUE_PARENT_ID = A.QUEUE_ID START WITH A.QUEUE_ID =? ");
        List<Map> ret = query(sql, new Object[]{queueId});
        getLogger().info(ROLE + "::getAllParentQueue(String)->Exit");
        return ret;
    }

    /**
     * check登錄者是否有指定資源的權限
     *
     * @param resourceId
     * @param roleId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public boolean hasPrivilege(String resourceId, String roleId) throws SQLException {
        getLogger().info(ROLE + "::hasPrivilege(String)->Enter");
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) REC_NUM ");
        sql.append(" FROM OSS_ADMIN_ROLE_RESOURCE A ");
        sql.append(" WHERE A.RESOURCE_ID = ? AND A.ROLE_ID = ? ");
        List<Map> ret = query(sql, new Object[]{resourceId, roleId});
        if ((ret != null) && (ret.size() > 0)) {
            for (Map item : ret) {
                int recNum = MapTools.getInt(item, "REC_NUM", 0);
                if (recNum > 0) {
                    return true;
                }
            }
        }
        getLogger().info(ROLE + "::hasPrivilege(String)->Exit");
        return false;
    }

    /**
     * 根據父隊列ID，取得權限範圍內所有子隊列ID
     *
     * @param parentQueueId
     * @param roleId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public String getAllChildQueueIdById(String parentQueueId, String roleId) throws SQLException {
        getLogger().info(ROLE + "::getAllChildQueueIdById(String, String)->Enter");
        StringBuffer sql = new StringBuffer(" SELECT QUEUE_ID, QUEUE_PARENT_ID ");
        sql.append(" FROM OSS_ADMIN_QUEUE A, OSS_ADMIN_ROLE_RESOURCE B ");
        sql.append(" WHERE A.ACTIVE = '1' AND A.QUEUE_PARENT_ID =? AND A.RESOURCE_ID = B.RESOURCE_ID ");
        sql.append(" AND B.ROLE_ID =? ORDER BY A.QUEUE_ID ");
        List<Map> ret = query(sql, new Object[]{parentQueueId, roleId});
        StringBuffer sb = new StringBuffer();
        if ((ret != null) && (ret.size() > 0)) {
            for (Map item : ret) {
                sb.append(MapTools.getString(item, "QUEUE_ID")).append(",");
            }
        }

        getLogger().info(ROLE + "::getAllChildQueueIdById(String, String)->Exit");
        return sb.toString().length() > 0 ? sb.toString().substring(0, sb.toString().length() - 1) : "";
    }

    /**
     * 根據隊列ID，取得隊列名稱
     *
     * @param queueId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public String getQueueNameById(String queueId) throws SQLException {
        getLogger().info(ROLE + "::getQueueNameById(String)->Enter");
        String queueName = null;
        String sql = " SELECT QUEUE_NAME_CN FROM OSS_ADMIN_QUEUE WHERE QUEUE_ID =? ";
        List<Map> ret = query(sql, new Object[]{queueId});
        if ((ret != null) && (ret.size() > 0)) {
            queueName = MapTools.getString(ret.get(0), "QUEUE_NAME_CN");
        }

        getLogger().info(ROLE + "::getQueueNameById(String)->Exit");
        return queueName;
    }

}
