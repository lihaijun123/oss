package com.focustech.oss2008.uitool.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.focustech.oss2008.utils.RoleResourceUtils;
import com.focustech.uitool.framework.NLGlobal;
import com.focustech.uitool.framework.exception.NLAppException;
import com.focustech.uitool.framework.exception.NLRoleException;
import com.focustech.uitool.framework.utils.StringTools;
import com.focustech.uitool.list.role.UIToolRole;
import com.focustech.uitool.list.utils.UIToolConst;

public class DefaultUIToolRoleImpl implements UIToolRole {
    /**
     * {@inheritDoc}
     */
    public void checkDataPurn(HttpServletRequest request) throws NLRoleException, NLAppException {
        //
        long actId = StringTools.parseLong(request.getParameter("funcID"), -1);
        if (RoleResourceUtils.check(actId, request.getSession().getServletContext()) == false) {
            throw new NLAppException("你没有权限访问此功能或没有登录");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void checkFuncPurv(HttpServletRequest request) throws NLRoleException, NLAppException {
        try {
            request.getSession().setMaxInactiveInterval(-1);
            request.getSession().setAttribute(NLGlobal.SESSIOIN_KEY_SYSTEM_NAME, "uitool");
        }
        catch (Exception e) {
            throw new NLAppException(e);
        }
    }

    public boolean checkDataPurn(Map reqParam) throws NLAppException {
        HttpServletRequest request = (HttpServletRequest) reqParam.get(UIToolConst.REQUEST_KEY_CURR_REQUEST);
        long actId = StringTools.parseLong((String) reqParam.get(UIToolRole.CHK_FUNCID), -1);
        return RoleResourceUtils.check(actId, request.getSession().getServletContext());
    }
}
