package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.model.OssAdminBulletin;

/**
 * 公告欄事務接口
 *
 * @author taofucheng
 */
public interface BulletinService {
    /**
     * 添加一條公告信息
     *
     * @param bulletin 公告信息
     */
    public void add(OssAdminBulletin bulletin);

    /**
     * 修改一條公告信息
     *
     * @param bulletin 公告信息
     */
    public void modify(OssAdminBulletin bulletin);

    /**
     * 根據修改時間降序排列，取前N條指定系統(包括整個OSS系統)的公告信息.<br>
     * 如果N為0或空,則返回所有指定系統的可顯示記錄;如果model為null,則獲取所有系統的指定記錄<br>
     * 如果n是合法的數字,則返回相應的數字;如果不合法,則返回默認數字5;如果n為空,則返回0;如果n是負數,則返回5<br>
     *
     * @param n 指定的記錄數
     * @param model 系統模塊名稱.當model為null時取所有系統
     * @return 相應的公告信息列表
     */
    public List<OssAdminBulletin> getTopBulletins(String n, String model);

    /**
     * 根據編號獲取指定的公告信息
     *
     * @param bulletinId 公告編號
     * @return
     */
    public OssAdminBulletin getBulletinById(String bulletinId);

    /**
     * 相應模塊是否有最新的可顯示的公告
     *
     * @param model
     * @return
     */
    public boolean hasNewActiveBulletin(String model);
}
