package com.focustech.oss2008.service;

import com.focustech.oss2008.model.OssAdminUserOainfo;

/**
 * 用戶信息與OA信息同步管理UserOAInfoService.java
 *
 * @author jibin
 */
public interface UserOAInfoService {
    /**
     * 根據用戶編號查出用戶信息
     *
     * @param userId
     * @return
     */
    public OssAdminUserOainfo getUser(String userId);

    /**
     * 新增或更新用戶OA信息
     *
     * @param info
     */
    public void saveOAInfo(OssAdminUserOainfo info);
}
