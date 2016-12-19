package com.focustech.oss2008.model;

import java.util.Date;

/**
 * OssAdminUserOainfo entity. @author MyEclipse Persistence Tools
 */
public class OssAdminUserOainfo implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5140892952866050587L;
    // Fields
    private String userId;
    private Date joinTime;
    private Date convertTime;
    private Date quitTime;
    private String userDuty;

    // Constructors
    /** default constructor */
    public OssAdminUserOainfo() {
    }

    /** minimal constructor */
    public OssAdminUserOainfo(String userId) {
        this.userId = userId;
    }

    /** full constructor */
    public OssAdminUserOainfo(String userId, Date joinTime, Date convertTime, Date quitTime, String userDuty) {
        this.userId = userId;
        this.joinTime = joinTime;
        this.convertTime = convertTime;
        this.quitTime = quitTime;
        this.userDuty = userDuty;
    }

    // Property accessors
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(Date convertTime) {
        this.convertTime = convertTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }
}
