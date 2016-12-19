package com.focustech.oss2008.service;

import com.focustech.oss2008.model.OssAdminUser;

/**
 * <li>目錄樹組件</li>
 *
 * @author jibin 2008-4-11 下午03:31:27
 */
public interface ShowMenuTree {
    /**
     * 通過角色取菜單樹
     *
     * @return
     */
    public String getMenuByRole(Long roleId);

    /**
     * 通過用戶取菜單樹
     *
     * @return
     */
    public String getMenuByUser(OssAdminUser user);
}
