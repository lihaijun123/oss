package com.focustech.oss2008.utils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.focustech.oss2008.service.UserService;

/**
 * Copyright (c) 2008, focustech All rights reserved 角色與資源處理公共類
 *
 * @author tc-hexuey
 * @version 1.0 2008-7-24 上午10:40:48
 */
public class RoleResourceUtils {
    /**
     * 檢查指定角色權限中是否有指定動作資源id對應的動作權限
     *
     * @param actId 資源動作id
     * @param pageContext 當前上下文件環境
     * @return 返回true :有權限；false︰沒有權限
     */
    public static boolean check(long actId, PageContext pageContext) {
        return check(actId, pageContext.getServletContext());
    }

    public static boolean check(long actId, ServletContext context) {
        try {
            WebApplicationContext content = WebApplicationContextUtils.getWebApplicationContext(context);
            UserService user = (UserService) content.getBean("userServiceImpl");
            return user.hasResourceRole4CurrUser(actId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
