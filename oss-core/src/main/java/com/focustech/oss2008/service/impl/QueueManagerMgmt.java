package com.focustech.oss2008.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.focustech.oss2008.dao.impl.QueueManagerMgr;
import com.focustech.uitool.framework.NLGlobal;
import com.focustech.uitool.framework.sc.NLAbstractMgmt;
import com.focustech.uitool.framework.utils.MapTools;
import com.focustech.uitool.list.utils.UIToolConst;
import com.focustech.uitool.list.utils.UIToolUtils;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-7-22 上午10:20:16
 */
public class QueueManagerMgmt extends NLAbstractMgmt {
    public List<Map> getQueuerInfos(Map reqParam) throws Exception {
        List<Map> queues = null;
        Connection conn = null;
        try {
            String uitoolDs = UIToolUtils.getDataSource(UIToolConst.SYSTEM_NAME, NLGlobal.DEFAULT_DATASOURCE);
            conn = getConnection(uitoolDs);
            QueueManagerMgr mgr = new QueueManagerMgr();
            mgr.setConnection(conn);
            mgr.setLogger(getLogger());
            String nodeId = MapTools.getString(reqParam, "nodeid");
            String roleId = MapTools.getString(reqParam, "S_ROLE_ID");

            // List<Map> qs = mgr.getQueuerInfos(roleId, nodeId);
            List<Map> qs = null;
            String includeQueueIds = MapTools.getString(reqParam, "includequeueids");
            if (StringUtils.isEmpty(includeQueueIds)) {
                qs = mgr.getQueuerInfos(roleId, nodeId);
            }
            else {
                qs = mgr.getQueuerInfoByQueueId(nodeId, includeQueueIds);
            }

            Map tmp = null;
            if (qs != null) {
                for (int i = 0; i < qs.size(); i++) {
                    tmp = qs.get(i);
                    long funcId = MapTools.getLong(tmp, "RESOURCE_ID", -1);
                    if (funcId <= 0) {
                        continue;
                    }
                    int showNum = MapTools.getInt(tmp, "SHOW_NUM", 1);
                    if (showNum < 0) {
                        tmp.put("COUNT_NUM", "xxx");
                    }
                    else if (showNum == 0) {
                        tmp.put("COUNT_NUM", "0");
                    }
                    else {
                        //
                        reqParam.put("funcid", funcId);
                        tmp.put("COUNT_NUM", String.valueOf(mgr.getQueuerInfos(funcId, reqParam)));
                    }
                }
            }
            queues = qs;
        }
        catch (Exception e) {
            rollback(conn);
            throw e;
        }
        finally {
            close(conn);
        }
        return queues;
    }

    /**
     * 根據登錄者權限，取得所有葉子節點，以及對應的父節點
     *
     * @param nodeId
     * @param roleId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAllQueuerByRoleId(String nodeId, String roleId) throws Exception {
        Connection conn = null;
        try {
            String uitoolDs = UIToolUtils.getDataSource(UIToolConst.SYSTEM_NAME, NLGlobal.DEFAULT_DATASOURCE);
            conn = getConnection(uitoolDs);
            QueueManagerMgr mgr = new QueueManagerMgr();
            mgr.setConnection(conn);
            mgr.setLogger(getLogger());
            return mgr.getQueuerInfos(roleId, nodeId);
        }
        catch (Exception e) {
            rollback(conn);
            throw e;
        }
        finally {
            close(conn);
        }
    }

    /**
     * 根據登錄者權限，取得所有葉子節點，以及對應的父節點
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map> getAllQueueByRoleId(String roleId) throws Exception {
        Connection conn = null;
        try {
            String uitoolDs = UIToolUtils.getDataSource(UIToolConst.SYSTEM_NAME, NLGlobal.DEFAULT_DATASOURCE);
            conn = getConnection(uitoolDs);
            QueueManagerMgr mgr = new QueueManagerMgr();
            mgr.setConnection(conn);
            mgr.setLogger(getLogger());
            return mgr.getAllQueueByRoleId(roleId);
        }
        catch (Exception e) {
            rollback(conn);
            throw e;
        }
        finally {
            close(conn);
        }
    }

    /**
     * 根據父隊列ID，取得權限範圍內所有子隊列ID
     *
     * @param parentQueueId
     * @param roleId
     * @return
     * @throws Exception
     */
    public String getAllChildQueueIdById(String parentQueueId, String roleId) throws Exception {
        Connection conn = null;
        try {
            String uitoolDs = UIToolUtils.getDataSource(UIToolConst.SYSTEM_NAME, NLGlobal.DEFAULT_DATASOURCE);
            conn = getConnection(uitoolDs);
            QueueManagerMgr mgr = new QueueManagerMgr();
            mgr.setConnection(conn);
            mgr.setLogger(getLogger());
            return mgr.getAllChildQueueIdById(parentQueueId, roleId);
        }
        catch (Exception e) {
            rollback(conn);
            throw e;
        }
        finally {
            close(conn);
        }
    }

    /**
     * 根據隊列ID，取得隊列名稱
     *
     * @param queueId
     * @return
     * @throws Exception
     */
    public String getQueueNameById(String queueId) throws Exception {
        Connection conn = null;
        try {
            String uitoolDs = UIToolUtils.getDataSource(UIToolConst.SYSTEM_NAME, NLGlobal.DEFAULT_DATASOURCE);
            conn = getConnection(uitoolDs);
            QueueManagerMgr mgr = new QueueManagerMgr();
            mgr.setConnection(conn);
            mgr.setLogger(getLogger());
            return mgr.getQueueNameById(queueId);
        }
        catch (Exception e) {
            rollback(conn);
            throw e;
        }
        finally {
            close(conn);
        }
    }

}
