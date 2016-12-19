package com.focustech.oss2008.model;

import java.io.Serializable;

/**
 * 用戶于部門和用戶標簽傳數據用
 *
 * @author tc-hexuey 2008-6-10 上午10:47:07
 */
public class DepartmentUserBt implements Serializable {
    private String departmentId = "";
    private String orgType = "";
    private String orgRange = "";
    private String defOrgId = "";
    private String userId = "";
    private String userType = "";
    private String defUserId = "";
    private String funcId = "";

    //
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getDefOrgId() {
        return defOrgId;
    }

    public void setDefOrgId(String defOrgId) {
        this.defOrgId = defOrgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDefUserId() {
        return defUserId;
    }

    public void setDefUserId(String defUserId) {
        this.defUserId = defUserId;
    }

    public String getOrgRange() {
        return orgRange;
    }

    public void setOrgRange(String orgRange) {
        this.orgRange = orgRange;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }
}
