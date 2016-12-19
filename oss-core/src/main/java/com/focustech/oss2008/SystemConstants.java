package com.focustech.oss2008;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.focustech.oss2008.service.EnvironmentParameter;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-14 上午11:45:53
 */
public class SystemConstants {
    /**
     * 得到當前項目的根路徑
     * <p>
     * 例如︰項目在D:\resin-3.0.25\deploy\oss2008,則得到的結果為︰ D:\resin-3.0.25\deploy\oss2008
     *
     * @return
     */
    public static String getWebRootPath() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
    }

    /** 根據 得到系統環境參數變量 */
    public static String getEnvironmentParameter(String key, ServletContext context) {
        try {
            WebApplicationContext content = WebApplicationContextUtils.getWebApplicationContext(context);
            EnvironmentParameter ep = (EnvironmentParameter) content.getBean("environmentParameterImpl");
            String value = ep.getStringValue(key);
            return value == null ? key : value;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
