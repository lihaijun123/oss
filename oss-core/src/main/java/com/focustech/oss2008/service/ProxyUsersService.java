package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.model.OssAdminProxyUser;

public interface ProxyUsersService {
    /**
     * 插入一個代理信息
     *
     * @param OssAdminProxyUser 代理信息
     */
    public void addProxyuser(OssAdminProxyUser OssAdminProxyUser);

    /**
     * 更新一個代理信息
     *
     * @param OssAdminProxyUser 代理信息
     */
    public void modifyProxyuser(OssAdminProxyUser OssAdminProxyUser);

    /**
     * 刪除一個代理信息
     *
     * @param OssAdminProxyUser 代理信息
     */
    public void deleteProxyuser(OssAdminProxyUser OssAdminProxyUser);

    /**
     * 獲取所有代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> getAllProxyuser();

    /**
     * 獲取還未到期的代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> getUsefulProxyuser();

    /**
     * 通過代理信息編號,獲取代理信息
     *
     * @param proxyId
     * @return
     */
    public OssAdminProxyUser getProxyuserByRecordId(String proxyId);

    /**
     * 獲取所有失效的代理信息
     *
     * @return
     */
    public List<OssAdminProxyUser> getDisabledProxyusers();
}
