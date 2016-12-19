package com.focustech.focus3d.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
/**
 * http相关的工具类 *
 * 
 * @author lihaijun
 */
public class HttpUtil {
    /**
     * 获取请求url的根路径 *
     * 
     * @param requestUrl
     * @return
     */
    public static String getRootPath(String requestUrl) {
        if (!StringUtils.isEmpty(requestUrl)) {
            int lastIndexOf = requestUrl.lastIndexOf("/");
            if (lastIndexOf < 0) {
                lastIndexOf = requestUrl.lastIndexOf("\\");
            }
            return requestUrl.substring(0, lastIndexOf + 1);
        }
        return "";
    }

    /**
     * *
     */
    public static Long lv(HttpServletRequest req, String paramKey) {
        return TCUtil.lv(req.getParameter(paramKey));
    }

    /**
     * *
     * 
     * @param req
     * @param paramKey
     * @return
     */
    public static String sv(HttpServletRequest req, String paramKey) {
        return TCUtil.sv(req.getParameter(paramKey));
    }

    /**
     * *
     * 
     * @param seesion
     * @param paramKey
     * @return
     */
    public static String sv(HttpSession seesion, String paramKey) {
        return TCUtil.sv(seesion.getAttribute(paramKey));
    }

    /**
     * @param req
     * @param paramKey
     * @return
     */
    public static int iv(HttpServletRequest req, String paramKey) {
        return TCUtil.iv(req.getParameter(paramKey));
    }
}
