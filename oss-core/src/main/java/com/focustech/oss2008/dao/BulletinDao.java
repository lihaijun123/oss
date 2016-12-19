package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.OssAdminBulletin;

public interface BulletinDao<T> extends BaseHibernateDao<T> {
    /**
     * 根據修改時間降序排列，獲取所有指定系統(包括整個OSS系統)的公告信息
     *
     * @param model 系統模塊名稱
     * @param maxResult 指定獲取前幾條記錄,如果該參數為0，則取所有信息
     * @return 相應的公告信息列表
     */
    public List<OssAdminBulletin> selectAllBulletins(String model, int maxResult);

    /**
     * 根據修改時間降序排列，獲取所有系統的公告信息
     *
     * @param maxResult 指定獲取前幾條記錄,如果該參數為0，則取所有信息
     * @return 相應的公告信息列表
     */
    public List<OssAdminBulletin> selectAllBulletins(int maxResult);

    /**
     * 相應模塊是否有最新的可顯示的公告
     *
     * @param model
     * @return
     */
    public boolean hasNewActiveBulletin(String model);
}
