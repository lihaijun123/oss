package com.focustech.oss2008.service.impl;

import org.apache.commons.lang.StringUtils;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.service.AbstractServiceSupport;


/**
 * <li>範圍權限處理幫助類</li>
 *
 * @author yangpeng 2008-6-27 下午02:01:11
 */
public class ScopeAuthorityHelper extends AbstractServiceSupport {
    public static final String RESOURCE_SCOPE_SQL = "sql://";
    public static final String RESOURCE_SCOPE_JAVA = "java://";

    /**
     * 取範圍權限配置的sql語句 如scope為sql://select * from dual，則返回select * from dual
     *
     * @param scope 範圍權限配置
     * @throws IllegalArgumentException 參數錯誤
     */
    public static String analysisSql(String scope) {
        if (StringUtils.isNotEmpty(scope)) {
            if (isSqlScope(scope)) {
                return scope.substring(RESOURCE_SCOPE_SQL.length(), scope.length());
            }
            else {
                throw new IllegalArgumentException(MessageUtils.getExceptionValue(ExceptionConstants.RESOURCE_ID_ERROR));
            }
        }
        else
            return scope;
    }

    /**
     * 判斷是否擁有完全權限
     *
     * @param scope 範圍權限
     * @return
     */
    public static boolean hasFullAuthority(String scope) {
        if ("".equals(scope))
            return true;
        else
            return false;
    }

    /**
     * 判斷是否是sql配置的數據範圍權限
     */
    public static boolean isSqlScope(String scope) {
        return scope.startsWith(RESOURCE_SCOPE_SQL);
    }

    /**
     * 判斷是否是java配置的數據範圍權限
     */
    public static boolean isJavaScope(String scope) {
        return scope.startsWith(RESOURCE_SCOPE_JAVA);
    }
}
