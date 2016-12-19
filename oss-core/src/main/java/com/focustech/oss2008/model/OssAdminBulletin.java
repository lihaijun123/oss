package com.focustech.oss2008.model;

import java.util.Date;

public class OssAdminBulletin implements java.io.Serializable {
    private static final long serialVersionUID = -5887443531241599483L;
    private Long bulletinId;
    private String bulletinModel;
    private String bulletinTitle;
    private String bulletinContent;
    private String relatedUrl;
    private String active;
    private OssAdminUser creater;
    private Date createdTime;
    private OssAdminUser modifier;
    private Date modifiedTime;

    public Long getBulletinId() {
        return this.bulletinId;
    }

    public void setBulletinId(Long bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getBulletinModel() {
        return this.bulletinModel;
    }

    public void setBulletinModel(String bulletinModel) {
        this.bulletinModel = bulletinModel;
    }

    public String getBulletinTitle() {
        return this.bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle;
    }

    public String getBulletinContent() {
        return this.bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public OssAdminUser getCreater() {
        return creater;
    }

    public void setCreater(OssAdminUser creater) {
        this.creater = creater;
    }

    public OssAdminUser getModifier() {
        return modifier;
    }

    public void setModifier(OssAdminUser modifier) {
        this.modifier = modifier;
    }

    public String getRelatedUrl() {
        return relatedUrl;
    }

    public void setRelatedUrl(String relatedUrl) {
        this.relatedUrl = relatedUrl;
    }
}
