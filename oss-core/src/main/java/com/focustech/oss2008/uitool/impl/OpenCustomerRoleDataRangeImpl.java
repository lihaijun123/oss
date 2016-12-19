package com.focustech.oss2008.uitool.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.service.DepartmentService;
import com.focustech.uitool.framework.utils.MapTools;
import com.focustech.uitool.list.UIToolExeSqlCreate;
import com.focustech.uitool.list.utils.UIToolConst;
import com.focustech.uitool.list.utils.UIToolUtils;

/**
 * OpenCustomerRoleDataRangeImpl.java
 *
 * @author jibin
 */
public class OpenCustomerRoleDataRangeImpl implements UIToolExeSqlCreate {
    /*
     * (non-Javadoc)
     * @see com.nl.uitool.UIToolExeSqlCreate#create(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public String create(Map reqData) {
        try {
            String deptID = MapTools.getString(reqData, "S_ORG_ID");
            HttpServletRequest request = (HttpServletRequest) reqData.get(UIToolConst.REQUEST_KEY_CURR_REQUEST);
            WebApplicationContext content =
                    WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            DepartmentService deptService = (DepartmentService) content.getBean("departmentServiceImpl");
            String scope = null;
            if (deptService.checkIfSubDepartmentsByParentId(deptID, Constants.SAL_DIRECT_DEPARTMENT_NO)) {
                scope = "2";
            }
            else {
                scope = "1";
            }
            return scope;
        }
        catch (Throwable e) {
            UIToolUtils.getLogger().error(e);
            return "";
        }
    }
}
