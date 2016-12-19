package com.focustech.oss2008.acegi.ldap;

import com.focustech.oss2008.exception.service.LdapCheckedException;

/**
 * <li>LDAP服務接口</li>
 *
 * @author yangpeng 2008-12-24 下午03:07:41
 */
public interface LdapService {
    /**
     * 根據用戶登錄名，查找該用戶的賬號還有多少天到期
     *
     * @param username 用戶登錄名
     * @return 用戶賬號距離到期時間的天數
     * @throws LdapCheckedException 發生與LDAP目錄有關的異常
     */
    public long getLdapValidDay(final String username) throws LdapCheckedException;
}
