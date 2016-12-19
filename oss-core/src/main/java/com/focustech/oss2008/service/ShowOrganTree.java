package com.focustech.oss2008.service;

/**
 * <li>機構樹組件</li>
 *
 * @author jibin 2008-11-20 上午10:26:26
 */
public interface ShowOrganTree {
    /**
     * 根據機構號取其及其子部門機構樹
     *
     * @return
     */
    public String getOrganByDeptId(String dpartId);
}
