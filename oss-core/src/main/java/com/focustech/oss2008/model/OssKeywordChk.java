package com.focustech.oss2008.model;

import java.util.Date;

/**
 * OssKeywordChk entity. @author MyEclipse Persistence Tools
 */
public class OssKeywordChk implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Fields
    private Long recId;
    private String keyword;
    private String checkStatus;
    private Long exportCount;
    private String checkerId;
    private Date checkTime;
    private String creatorId;
    private Date createdTime;
    private String modifierId;
    private Date lastModifiedTime;
    private Long searchCount;

    // Constructors
    /** default constructor */
    public OssKeywordChk() {
    }

    /** minimal constructor */
    public OssKeywordChk(String keyword, String checkStatus, Long exportCount, String creatorId, Date createdTime,
            String modifierId, Date lastModifiedTime, Long searchCount) {
        this.keyword = keyword;
        this.checkStatus = checkStatus;
        this.exportCount = exportCount;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.modifierId = modifierId;
        this.lastModifiedTime = lastModifiedTime;
        this.searchCount = searchCount;
    }

    /** full constructor */
    public OssKeywordChk(String keyword, String checkStatus, Long exportCount, String checkerId, Date checkTime,
            String creatorId, Date createdTime, String modifierId, Date lastModifiedTime, Long searchCount) {
        this.keyword = keyword;
        this.checkStatus = checkStatus;
        this.exportCount = exportCount;
        this.checkerId = checkerId;
        this.checkTime = checkTime;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.modifierId = modifierId;
        this.lastModifiedTime = lastModifiedTime;
        this.searchCount = searchCount;
    }

    // Property accessors
    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getExportCount() {
        return exportCount;
    }

    public void setExportCount(Long exportCount) {
        this.exportCount = exportCount;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Long searchCount) {
        this.searchCount = searchCount;
    }
}
