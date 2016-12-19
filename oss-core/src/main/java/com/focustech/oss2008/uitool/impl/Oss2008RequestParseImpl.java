package com.focustech.oss2008.uitool.impl;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import com.focustech.oss2008.model.OssAdminRole;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.uitool.list.interfaces.UIToolRequestParser;
import com.focustech.uitool.list.utils.UIToolConst;

/**
 * Copyright (c) 2006, focustech All rights reserved 對于oss 2008系統中使用uitool工具的時候需求一些系統的上下文變量
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-23 下午02:38:57
 */
public class Oss2008RequestParseImpl implements UIToolRequestParser {
    public void parser(Map reqParam, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        if (null != principal && authentication.isAuthenticated()) {
            OssAdminUser user = (OssAdminUser) principal;
            reqParam.put("OWNER_ID", user.getUserId());
            reqParam.put("S_ORG_ID", user.getOssAdminDepartment().getDepartmentId());
            Set<OssAdminRole> roles = user.getRoles();
            if (roles != null) {
                for (OssAdminRole role : roles) {
                    reqParam.put("S_ROLE_ID", String.valueOf(role.getRoleId()));
                    break;
                }
            }
        }
        reqParam.put(UIToolConst.REQUEST_KEY_CURR_REQUEST, request);
    }
}
