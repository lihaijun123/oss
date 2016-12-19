package com.focustech.oss2008.bt;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class OssTelephone implements Serializable {
    /***/
    private static final long serialVersionUID = 439906135891270012L;
    // ==============常量==================
    /** 電話號碼類型︰電話 */
    public static final String OSS_TELEPHONE_TYPE_TEL = "1";
    /** 電話號碼類型︰傳真 */
    public static final String OSS_TELEPHONE_TYPE_FAX = "2";
    /** 電話號碼類型︰手機 */
    public static final String OSS_TELEPHONE_TYPE_MOBILE = "3";
    // ================值屬性================
    /** 電話號碼對應的編號︰如果有，則表示修改；如果沒有，則表示添加 */
    private Long telephoneId;
    /** 電話類型，可以有電話號碼、傳真、手機。具體的值請用該類中對應的常量 */
    private String telephoneType;
    /** 國際區號︰默認86 */
    private String nationalNo = "86";
    /** 國內區號︰默認000 */
    private String countryNo = "000";
    /** 電話號碼 */
    private String telephone;
    /** 電話分機號碼 */
    private String telephoneExtend;
    /** 記錄用戶自定義的說明信息，如指明公司是“家或公司”等 */
    private String extendInfo;

    // ===============
    /** 電話號碼對應的編號︰如果有，則表示修改；如果沒有，則表示添加 */
    public void setTelephoneId(Long telephoneId) {
        this.telephoneId = telephoneId;
    }

    /** 電話號碼對應的編號︰如果有，則表示修改；如果沒有，則表示添加 */
    public Long getTelephoneId() {
        return telephoneId;
    }

    /** 電話類型，可以有電話號碼、傳真、手機。具體的值請用該類中對應的常量 */
    public String getTelephoneType() {
        return telephoneType;
    }

    /** 電話類型，可以有電話號碼、傳真、手機。具體的值請用該類中對應的常量 */
    public void setTelephoneType(String telephoneType) {
        this.telephoneType = telephoneType;
    }

    /** 國際區號︰默認86 */
    public String getNationalNo() {
        return nationalNo;
    }

    /** 國際區號︰默認86 */
    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    /** 國內區號︰默認000 */
    public String getCountryNo() {
        return countryNo;
    }

    /** 國內區號︰默認000 */
    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }

    /** 電話號碼 */
    public String getTelephone() {
        return telephone;
    }

    /** 電話號碼 */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /** 電話分機號碼 */
    public String getTelephoneExtend() {
        return telephoneExtend;
    }

    /** 電話分機號碼 */
    public void setTelephoneExtend(String telephoneExtend) {
        this.telephoneExtend = telephoneExtend;
    }

    /** 記錄用戶自定義的說明信息，如指明公司是“家或公司”等 */
    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    /** 記錄用戶自定義的說明信息，如指明公司是“家或公司”等 */
    public String getExtendInfo() {
        return extendInfo;
    }

    public String toString() {
        String result = "";
        result += getNationalNo() + "-" + getCountryNo() + "-" + getTelephone();
        if (StringUtils.isNotEmpty(getTelephoneExtend())) {
            result += "-" + getTelephoneExtend();
        }
        if (StringUtils.isNotEmpty(getExtendInfo())) {
            result += "-" + getExtendInfo();
        }
        return result;
    }
}
