package com.focustech.oss2008.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

/**
 * <li></li>
 *
 * @author yangpeng 2009-2-13 下午02:29:41
 */
public abstract class ServletAnnotationMappingUtils {
    /**
     * Check whether the given request matches the specified request methods.
     *
     * @param methods the HTTP request methods to check against
     * @param request the current HTTP request to check
     */
    public static boolean checkRequestMethod(RequestMethod[] methods, HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(methods)) {
            boolean match = false;
            for (RequestMethod method : methods) {
                if (method.toString().equals(request.getMethod().toUpperCase())) {
                    match = true;
                }
            }
            if (!match) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the given request matches the specified parameter conditions.
     *
     * @param params the parameter conditions, following
     *            {@link org.springframework.web.bind.annotation.RequestMapping#params()}
     * @param request the current HTTP request to check
     */
    public static boolean checkParameters(String[] params, HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(params)) {
            for (String param : params) {
                int separator = param.indexOf('=');
                if (separator == -1) {
                    if (param.startsWith("!")) {
                        if (WebUtils.hasSubmitParameter(request, param.substring(1))) {
                            return false;
                        }
                    }
                    else if (!WebUtils.hasSubmitParameter(request, param)) {
                        return false;
                    }
                }
                else {
                    String key = param.substring(0, separator);
                    String value = param.substring(separator + 1);
                    if (!value.equals(request.getParameter(key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
