package com.focustech.oss2008.bt;

import java.io.Serializable;
import java.util.Map;

import com.focustech.common.utils.StringUtils;

/**
 * <li>撞單檢查的結果</li>
 *
 * @author taofucheng 2009-6-1 下午07:21:22
 */
public class CheckCrashResult implements Serializable {
    private static final long serialVersionUID = 1710131512765639856L;
    /** 存放的是添加或修改成功後對應的客戶編號 */
    private Long accountId;
    /** 撞單的狀態︰如果是"0"，表示沒有撞單；如果是"1"，表示發生撞單；如果是"2"，表示疑似撞單 */
    private String crashStatus;
    /** 撞單提示信息︰這里是根據不同來源類型的數據，存放不同的提示信息，用于頁面提示。如詢盤客戶發生撞單或疑似撞單時，提示“此客戶給我們發送了一封聯系信。” */
    private String info;
    /** 不存在的客戶編號 */
    private String notExistAccountIds;
    /** 具體的撞單信息，存放的是被撞客戶的ID與對應的撞單信息（如與某字段相同或相似等）。 */
    private Map<String, String> crashInfo;
    /** 這里面存放的是被撞客戶對應的撞單精確度或相似度 */
    private Map<String, String> crashCluster;

    /** 撞單的狀態︰如果是"0"，表示沒有撞單；如果是"1"，表示發生撞單；如果是"2"，表示疑似撞單 */
    public String getCrashStatus() {
        return crashStatus;
    }

    /** 撞單的狀態︰如果是"0"，表示沒有撞單；如果是"1"，表示發生撞單；如果是"2"，表示疑似撞單 */
    public void setCrashStatus(String crashStatus) {
        this.crashStatus = crashStatus;
    }

    /** 撞單提示信息︰這里是根據不同來源類型的數據，存放不同的提示信息，用于頁面提示。如詢盤客戶發生撞單或疑似撞單時，提示“此客戶給我們發送了一封聯系信。” */
    public String getInfo() {
        return info;
    }

    /** 撞單提示信息︰這里是根據不同來源類型的數據，存放不同的提示信息，用于頁面提示。如詢盤客戶發生撞單或疑似撞單時，提示“此客戶給我們發送了一封聯系信。” */
    public void setInfo(String info) {
        this.info = info;
    }

    /** 具體的撞單信息，存放的是被撞客戶的ID與對應的撞單信息（如與某字段相同或相似等）。 */
    public Map<String, String> getCrashInfo() {
        return crashInfo;
    }

    /** 具體的撞單信息，存放的是被撞客戶的ID與對應的撞單信息（如與某字段相同或相似等）。 */
    public void setCrashInfo(Map<String, String> crashInfo) {
        this.crashInfo = crashInfo;
    }

    /** 這里面存放的是被撞客戶對應的撞單精確度或相似度 */
    public void setCrashCluster(Map<String, String> crashCluster) {
        this.crashCluster = crashCluster;
    }

    /** 這里面存放的是被撞客戶對應的撞單精確度或相似度 */
    public Map<String, String> getCrashCluster() {
        return crashCluster;
    }

    /** 存放的是添加或修改成功後對應的客戶編號 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /** 存放的是添加或修改成功後對應的客戶編號 */
    public Long getAccountId() {
        return accountId;
    }

    /** 不存在的客戶編號 */
    public void setNotExistAccountIds(String notExistAccountIds) {
        this.notExistAccountIds = StringUtils.notNull(notExistAccountIds);
    }

    /** 不存在的客戶編號 */
    public String getNotExistAccountIds() {
        return notExistAccountIds;
    }
}
