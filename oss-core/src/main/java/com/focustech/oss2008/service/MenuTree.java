package com.focustech.oss2008.service;

/**
 * <li>目錄樹組件</li>
 *
 * @author yangpeng 2008-4-11 下午03:31:27
 */
public interface MenuTree {
    public String getMenu();
    public String getMenu2();

    /**
     * 刷新目錄緩存
     */
    public void refresh();

    /**
     * 初始化方法，將所有的menu文件讀入緩存
     */
    public void init();

    /**
     * 刷新一個用戶的菜單緩存
     *
     * @param userId
     */
    public void refreshOne(String userId);
}
