/**
 *
 */
package com.focustech.oss2008.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;

import com.focustech.oss2008.service.impl.QueueManagerMgmt;
import com.focustech.uitool.list.action.UIToolAction;

/**
 * Copyright (c) 2006, focustech All rights reserved
 *
 * @author tc-hexuey
 * @version 1.0 2008-7-22 上午10:15:24
 */
public class QueueController extends UIToolAction {
    @Override
    protected void preExecute() throws Exception {
        checkPrivilege(getRequest());
    }

    /*
     * (non-Javadoc)
     * @see com.nl.framework.action.NLAction#execute()
     */
    @Override
    public ActionForward execute() throws Exception {
        QueueManagerMgmt mgmt = new QueueManagerMgmt();
        Map reqParam = getDataFromRequest();
        List<Map> lisRet = mgmt.getQueuerInfos(reqParam);
        getRequest().setAttribute("data", lisRet);
        String page = (String) getRequest().getParameter("page");
        if (StringUtils.isEmpty(page)) {
            return getMapping().findForward("list");
        }
        else {
            return getMapping().findForward(page);
        }
    }
}
