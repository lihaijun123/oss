package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.focustech.oss2008.model.OssAdminBulletin;
import com.focustech.oss2008.service.BulletinService;

public class BulletinEditor extends PropertyEditorSupport {
    private BulletinService bulletinService;

    public BulletinEditor(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        OssAdminBulletin bulletin = new OssAdminBulletin();
        if (StringUtils.isEmpty(text)) {
            bulletin = null;
        }
        else {
            bulletin = bulletinService.getBulletinById(text);
        }
        super.setValue(bulletin);
    }
}
