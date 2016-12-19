package com.focustech.oss2008.uitool.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.focustech.oss2008.service.RoleService;
import com.focustech.uitool.framework.utils.MapTools;
import com.focustech.uitool.framework.utils.StringTools;
import com.focustech.uitool.list.ExeSqlBean;
import com.focustech.uitool.list.UIToolExeSqlCreate;
import com.focustech.uitool.list.utils.UIToolConst;
import com.focustech.uitool.list.utils.UIToolUtils;

/**
 * Copyright (c) 2006, focustech All rights reserved 根據當前系統登錄人員的角色對應的當前訪問的功能的訪問數據權限來返回對應的數據訪問權限定義SQL或範圍
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-23 下午02:44:30
 */
public class Oss2008RoleDataRangeImpl implements UIToolExeSqlCreate {
    /*
     * (non-Javadoc)
     * @see com.nl.uitool.UIToolExeSqlCreate#create(java.util.Map)
     */
    public String create(Map reqData) {
        try {
            String funcId = MapTools.getString(reqData, "funcid");
            String roleId = MapTools.getString(reqData, "S_ROLE_ID");
            HttpServletRequest request = (HttpServletRequest) reqData.get(UIToolConst.REQUEST_KEY_CURR_REQUEST);
            WebApplicationContext content =
                    WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            RoleService role = (RoleService) content.getBean("roleServiceImpl");
            String scope = role.getUrlScope(StringTools.parseLong(roleId, -1), StringTools.parseLong(funcId, -1));
            ExeSqlBean sqlBean = (ExeSqlBean) reqData.get(UIToolConst.CTXT_CURR_EXESQL_BEAN);
            String sql = "";
            if (sqlBean != null) {
                sql = sqlBean.getSql();
            }
            if (sql == null) {
                sql = "";
            }
            //
            if (scope == null) {
                // 沒有權限範圍控制
                scope = sql.replaceAll("##", "''");
            }
            else if (scope.length() <= 0) {
                // 可以查詢所有對象
                scope = "1=1";
            }
            else {
                scope = sql.replaceAll("##", scope);
            }
            return scope;
        }
        catch (Throwable e) {
            UIToolUtils.getLogger().error(e);
            return "";
        }
    }
}
