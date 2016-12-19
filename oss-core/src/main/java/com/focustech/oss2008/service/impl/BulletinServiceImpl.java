package com.focustech.oss2008.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.BulletinDao;
import com.focustech.oss2008.model.OssAdminBulletin;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.BulletinService;

@Service
public class BulletinServiceImpl extends AbstractServiceSupport implements BulletinService {
    @Autowired
    private BulletinDao<OssAdminBulletin> bulletinDao;

    public OssAdminBulletin getBulletinById(String bulletinId) {
        return bulletinDao.select(Long.valueOf(bulletinId));
    }

    public void add(OssAdminBulletin bulletin) {
        fillAddInfo(bulletin);
        bulletinDao.insert(bulletin);
    }

    public List<OssAdminBulletin> getTopBulletins(String n, String model) {
        int top = getValidateNumber(n);
        if (StringUtils.isEmpty(model))
            return bulletinDao.selectAllBulletins(top);
        else
            return bulletinDao.selectAllBulletins(model, top);
    }

    public void modify(OssAdminBulletin bulletin) {
        OssAdminBulletin bulletin1 = bulletinDao.select(bulletin.getBulletinId());
        bulletin1.setBulletinModel(bulletin.getBulletinModel());
        bulletin1.setBulletinTitle(bulletin.getBulletinTitle());
        bulletin1.setBulletinContent(bulletin.getBulletinContent());
        bulletin1.setRelatedUrl(bulletin.getRelatedUrl());
        bulletin1.setActive(bulletin.getActive());
        fillModifyInfo(bulletin1);
        bulletinDao.update(bulletin1);
    }

    public boolean hasNewActiveBulletin(String model) {
        return bulletinDao.hasNewActiveBulletin(model);
    }

    /**
     * 填充添加時的必要信息
     *
     * @param bulletin 公告信息
     */
    private void fillAddInfo(OssAdminBulletin bulletin) {
        bulletin.setCreatedTime(new Date());
        bulletin.setCreater(getLoginUser());
        fillModifyInfo(bulletin);
    }

    /**
     * 填充修改時的必要信息
     *
     * @param bulletin 公告信息
     */
    private void fillModifyInfo(OssAdminBulletin bulletin) {
        bulletin.setModifiedTime(new Date());
        bulletin.setModifier(getLoginUser());
    }

    /**
     * 驗證數字是否合法, 並返回對應的數字.如果數字是合法的,則返回相應的數字;如果不合法,則返回默認數字5;如果n為空,則返回0;如果n是負數,則返回5
     *
     * @param n
     * @return
     */
    private int getValidateNumber(String n) {
        int i = 5;// 默認取前5
        n = StringUtils.trim(n);
        if (StringUtils.isEmpty(n)) {
            i = 0;
        }
        else if (StringUtils.isNumeric(n))
            i = Integer.parseInt(n);
        return i >= 0 ? i : 5;
    }
}
