package com.focustech.oss2008.bt;

import java.io.Serializable;
import java.util.List;

import com.focustech.common.utils.StringUtils;



/**
 * <li>撞單檢查時的客戶信息實體類</li><br>
 * 這個類用于撞單檢查時承載客戶信息的，承載客戶信息最多的操作為修改客戶操作。 <br>
 *
 * @author taofucheng 2009-5-31 上午10:09:09
 */
public class CrmAccountEntity implements Serializable {
    private static final long serialVersionUID = 1747688959854635520L;
    // ================下面是常量=========================
    /** 客戶來源類型︰普通 */
    public static final String ACCOUNT_ORIGIN_TYPE_COMMON = "1";
    /** 客戶來源類型︰MIC展會名片 */
    public static final String ACCOUNT_ORIGIN_TYPE_EXHIBITION = "2";
    /** 客戶來源類型︰MIC免費會員 */
    public static final String ACCOUNT_ORIGIN_TYPE_MEMBER = "3";
    /** 客戶來源類型︰外部數據（即抓取的數據） */
    public static final String ACCOUNT_ORIGIN_TYPE_FETCH = "4";
    /** 客戶來源類型︰詢盤客戶 */
    public static final String ACCOUNT_ORIGIN_TYPE_ENQUIRY = "5";
    /** 客戶來源類型︰操作聯系人 */
    public static final String ACCOUNT_ORIGIN_TYPE_CONTACT = "6";
    /** 客戶來源類型︰阿里客戶 */
    public static final String ACCOUNT_ORIGIN_TYPE_ALIBABA = "7";
    /** 客戶來源類型︰環球資源 */
    public static final String ACCOUNT_ORIGIN_TYPE_GLOBAL = "8";
    // ----------------------------------------------------
    /** 客戶的撞單狀態︰沒有發生撞單 */
    public static final String ACCOUNT_CRASH_STATUS_NO = "0";
    /** 客戶的撞單狀態︰發生撞單 */
    public static final String ACCOUNT_CRASH_STATUS_YES = "1";
    /** 客戶的撞單狀態︰疑似撞單 */
    public static final String ACCOUNT_CRASH_STATUS_MAYBE = "2";
    // -----------------------------------------------------
    /** 會員審核時的會員類型︰MIC會員公司 */
    public static final String MEMEBER_TYPE_MIC = "0";
    /** 會員審核時的會員類型︰MIC會員快速商情 */
    public static final String MEMEBER_TYPE_OFFER = "1";
    // =================下面是字段信息======================
    /** 客戶ID。當修改客戶時，這個值不能為空！ */
    private Long accountId;
    /** 相關客戶ID，可以為空 */
    private Long parentAccount;
    /** 客戶中文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    private String accountNameCn;
    /** 客戶英文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    private String accountNameEn;
    /** 客戶名稱，這個名稱是中英文合在一起的名稱，這個可以不用填寫，但在普通修改時，這個是要填寫的。 */
    private String accountName;
    /** 客戶別名︰用于普通修改時記錄對應信息的。 */
    private String accountNameAlias;
    /** 客戶類型︰0:MIC客戶；1:貿易服務客戶 */
    private String accountType;
    /** 國家 */
    private String country;
    /** 省份︰第一個字母是大寫 */
    private String province;
    /** 城市︰數據格式為︰省份_城市 */
    private String city;
    /** 城區︰數據格式為︰省份_城市_城區 */
    private String cityzone;
    /** 客戶所屬行業 */
    private String industry;
    /** 客戶級別︰9系統開放|8簽約|7待簽|6優質潛在|5一般潛在|4未聯系|3流失|2放棄|0空 */
    private String accountLevel;
    /** 客戶屬性︰0︰私有，1︰開放，2︰疑似撞單，3︰撞單，4︰集中處理 */
    private String property;
    /** 郵編 */
    private String postcode;
    /** 客戶地址 */
    private String address;
    /** 電話︰這個屬性中的值之間請用英文逗號隔開 */
    private String telephone;
    /** 傳真︰這個屬性中的值之間請用英文逗號隔開 */
    private String fax;
    /** 郵箱︰這個屬性中的值之間請用英文逗號隔開 */
    private String email;
    /** 公司主頁︰這個屬性中的值之間請用英文逗號隔開 */
    private String homepage;
    /** 公司員工數 */
    private Long employees;
    /** 聯系人姓名 */
    private String contactMan;
    /** 描述 */
    private String description;
    /** 備注 */
    private String remark;
    /** 來源類型(這個數據項不能空！)︰1︰普通，2︰MIC展會名片，3︰MIC免費會員，4︰抓取的數據，5︰詢盤客戶，6︰操作聯系人。 */
    private String originType;
    /** 來源數據對應的ID，如MIC會員ID等等 */
    private String originId;
    /**
     * 來源信息︰這個屬性目前只有三個用途，修改客戶時存儲客戶對應字段的信息和導入名片信息時存放展會名稱信息，另外還有存放MIC會員的語言狀態（0︰英文、1︰中文等等） <br>
     * 其中，當導入名片信息和MIC會員時，這個值是不能為空的。
     */
    private String originInformation;
    /** 客戶的MIC會員ID */
    private String tempMicId;
    /**
     * 客戶所有者ID。<br>
     * 在普通操作（即銷售系統中銷售人員手動操作）時，這個值是用戶傳入的值，其它情況下是進入助理可視範圍的處理方式。<br>
     * 在修改客戶時，當這個值發生變化時同時修改分配信息
     */
    private String ownerId;
    /** 當前操作用戶的ID，這個為必填信息 */
    private String currentUserId;
    /** 客戶所屬集團公司的編號 */
    private Long companyId;
    /** 標志電話號碼、傳真和手機是否是通過對象存放，默認是false（為了兼容編輯系統中的代碼） */
    private boolean isOssTelephone = false;
    /** 對象形式的電話號碼、傳真、手機 */
    private List<OssTelephone> phones;
    /** 需要刪除的電話，里面存放的是對應的序號 */
    private String delTelephone;
    /** MIC會員類型︰0-MIC會員公司；1-快速商情。默認為0，即MIC會員公司 */
    private String memberType = MEMEBER_TYPE_MIC;
    private String originStatus;
    /** 地區備注 **/
    private String areaRemark;
    /** 抓取客戶來源地址 **/
    private String originUrl;

    /** 客戶唯一編號 **/
    private String uniqueId;

    // ================================================================
    /** 客戶ID。當修改客戶時，這個值不能為空！ */
    public Long getAccountId() {
        return accountId;
    }

    /** 客戶ID。當修改客戶時，這個值不能為空！ */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /** 客戶中文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    public String getAccountNameCn() {
        return accountNameCn;
    }

    /** 客戶中文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    public void setAccountNameCn(String accountNameCn) {
        this.accountNameCn = accountNameCn;
    }

    /** 客戶英文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    public String getAccountNameEn() {
        return accountNameEn;
    }

    /** 客戶英文名稱，中文名稱與英文名稱兩者不可同時為空！ */
    public void setAccountNameEn(String accountNameEn) {
        this.accountNameEn = formatEnName(accountNameEn);
    }

    /** 客戶名稱，這個名稱是中英文合在一起的名稱，這個可以不用填寫，但在普通修改時，這個是要填寫的。 */
    public String getAccountName() {
        return accountName;
    }

    /** 客戶名稱，這個名稱是中英文合在一起的名稱，這個可以不用填寫，但在普通修改時，這個是要填寫的。 */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /** 客戶別名︰用于普通修改時記錄對應信息的。 */
    public String getAccountNameAlias() {
        return accountNameAlias;
    }

    /** 客戶別名︰用于普通修改時記錄對應信息的。 */
    public void setAccountNameAlias(String accountNameAlias) {
        this.accountNameAlias = accountNameAlias;
    }

    /** 客戶類型︰0:MIC客戶；1:貿易服務客戶 */
    public String getAccountType() {
        return accountType;
    }

    /** 客戶類型︰0:MIC客戶；1:貿易服務客戶 */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /** 國家 */
    public String getCountry() {
        return country;
    }

    /** 國家 */
    public void setCountry(String country) {
        this.country = country;
    }

    /** 省份︰第一個字母是大寫 */
    public String getProvince() {
        return province;
    }

    /** 省份︰第一個字母是大寫 */
    public void setProvince(String province) {
        this.province = province;
    }

    /** 城市︰數據格式為︰省份_城市 */
    public String getCity() {
        return city;
    }

    /** 城市︰數據格式為︰省份_城市 */
    public void setCity(String city) {
        this.city = city;
    }

    /** 城區︰數據格式為︰省份_城市_城區 */
    public String getCityzone() {
        return cityzone;
    }

    /** 城區︰數據格式為︰省份_城市_城區 */
    public void setCityzone(String cityzone) {
        this.cityzone = cityzone;
    }

    /** 客戶所屬行業 */
    public String getIndustry() {
        return industry;
    }

    /** 客戶所屬行業 */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /** 客戶級別︰9系統開放|8簽約|7待簽|6優質潛在|5一般潛在|4未聯系|3流失|2放棄|0空 */
    public String getAccountLevel() {
        return accountLevel;
    }

    /** 客戶級別︰9系統開放|8簽約|7待簽|6優質潛在|5一般潛在|4未聯系|3流失|2放棄|0空 */
    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }

    /** 客戶屬性︰0︰私有，1︰開放，2︰疑似撞單，3︰撞單，4︰集中處理 */
    public String getProperty() {
        return property;
    }

    /** 客戶屬性︰0︰私有，1︰開放，2︰疑似撞單，3︰撞單，4︰集中處理 */
    public void setProperty(String property) {
        this.property = property;
    }

    /** 郵編 */
    public String getPostcode() {
        return postcode;
    }

    /** 郵編 */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /** 客戶地址 */
    public String getAddress() {
        return address;
    }

    /** 客戶地址 */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 電話︰這個屬性中的值之間請用英文逗號隔開 */
    public String getTelephone() {
        return telephone;
    }

    /** 電話︰這個屬性中的值之間請用英文逗號隔開 */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /** 傳真︰這個屬性中的值之間請用英文逗號隔開 */
    public String getFax() {
        return fax;
    }

    /** 傳真︰這個屬性中的值之間請用英文逗號隔開 */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /** 郵箱︰這個屬性中的值之間請用英文逗號隔開 */
    public String getEmail() {
        return email;
    }

    /** 郵箱︰這個屬性中的值之間請用英文逗號隔開 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 公司主頁︰這個屬性中的值之間請用英文逗號隔開 */
    public String getHomepage() {
        return homepage;
    }

    /** 公司主頁︰這個屬性中的值之間請用英文逗號隔開 */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /** 公司員工數 */
    public Long getEmployees() {
        return employees;
    }

    /** 公司員工數 */
    public void setEmployees(Long employees) {
        this.employees = employees;
    }

    /** 聯系人姓名 */
    public String getContactMan() {
        return contactMan;
    }

    /** 聯系人姓名 */
    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    /** 公司描述信息 */
    public String getDescription() {
        return description;
    }

    /** 公司描述信息 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 備注 */
    public String getRemark() {
        return remark;
    }

    /** 備注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 來源類型(這個數據項不能空！)︰1︰普通，2︰MIC展會名片，3︰MIC免費會員，4︰抓取的數據，5︰詢盤客戶，6︰操作聯系人。 */
    public String getOriginType() {
        return originType;
    }

    /** 來源類型(這個數據項不能空！)︰1︰普通，2︰MIC展會名片，3︰MIC免費會員，4︰抓取的數據，5︰詢盤客戶，6︰操作聯系人。 */
    public void setOriginType(String originType) {
        this.originType = originType;
    }

    /** 來源數據對應的ID，如MIC會員ID等等 */
    public String getOriginId() {
        return originId;
    }

    /** 來源數據對應的ID，如MIC會員ID等等 */
    public void setOriginId(String originId) {
        this.originId = originId;
    }

    /**
     * 來源信息︰這個屬性目前只有三個用途，修改客戶時存儲客戶對應字段的信息和導入名片信息時存放展會名稱信息，另外還有存放MIC會員的語言狀態（0︰英文、1︰中文等等） <br>
     * 其中，當導入名片信息和MIC會員時，這個值是不能為空的。
     */
    public String getOriginInformation() {
        return originInformation;
    }

    /**
     * 來源信息︰這個屬性目前只有三個用途，修改客戶時存儲客戶對應字段的信息和導入名片信息時存放展會名稱信息，另外還有存放MIC會員的語言狀態（0︰英文、1︰中文等等） <br>
     * 其中，當導入名片信息和MIC會員時，這個值是不能為空的。
     */
    public void setOriginInformation(String originInformation) {
        this.originInformation = originInformation;
    }

    /**
     * 客戶所有者ID。<br>
     * 在普通操作（即銷售系統中銷售人員手動操作）時，這個值是用戶傳入的值，其它情況下是進入助理可視範圍的處理方式。<br>
     * 在修改客戶時，當這個值發生變化時同時修改分配信息
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * 客戶所有者ID。<br>
     * 在普通操作（即銷售系統中銷售人員手動操作）時，這個值是用戶傳入的值，其它情況下是進入助理可視範圍的處理方式。<br>
     * 在修改客戶時，當這個值發生變化時同時修改分配信息
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /** 當前操作用戶的ID，這個為必填信息 */
    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    /** 當前操作用戶的ID，這個為必填信息 */
    public String getCurrentUserId() {
        return currentUserId;
    }

    /** 客戶所屬集團公司的編號 */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /** 客戶所屬集團公司的編號 */
    public Long getCompanyId() {
        return companyId;
    }

    /** 標志電話號碼、傳真和手機是否是通過對象存放，默認是false（為了兼容編輯系統中的代碼） */
    public void setOssTelephone(boolean isOssTelephone) {
        this.isOssTelephone = isOssTelephone;
    }

    /** 標志電話號碼、傳真和手機是否是通過對象存放，默認是false（為了兼容編輯系統中的代碼） */
    public boolean isOssTelephone() {
        return isOssTelephone;
    }

    /** 對象形式的電話號碼、傳真、手機 */
    public void setPhones(List<OssTelephone> phones) {
        this.phones = phones;
    }

    /** 對象形式的電話號碼、傳真、手機 */
    public List<OssTelephone> getPhones() {
        return phones;
    }

    /** 需要刪除的電話，里面存放的是對應的序號 */
    public void setDelTelephone(String delTelephone) {
        this.delTelephone = delTelephone;
    }

    /** 需要刪除的電話，里面存放的是對應的序號 */
    public String getDelTelephone() {
        return delTelephone;
    }

    /**
     * 保證英文名稱的格式，如去除多余的空格等
     *
     * @param EnName
     * @return 返回格式化後的名稱
     */
    private String formatEnName(String EnName) {
        if (StringUtils.isEmpty(EnName)) {
            return "";
        }
        // 去除多余空格，注︰前面已經保證了EnName不可能為空
        EnName = EnName.replaceAll("[ ]+", " ").trim();
        return EnName;
    }

    /** 客戶的MIC會員ID */
    public void setTempMicId(String tempMicId) {
        this.tempMicId = tempMicId;
    }

    /** 客戶的MIC會員ID */
    public String getTempMicId() {
        return tempMicId;
    }

    /** MIC會員類型︰0-MIC會員公司；1-快速商情。默認為0，即MIC會員公司 */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    /** MIC會員類型︰0-MIC會員公司；1-快速商情。默認為0，即MIC會員公司 */
    public String getMemberType() {
        return memberType;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("");
        result.append("accountId︰" + (accountId == null ? "" : String.valueOf(accountId)) + "\n");
        result.append("accountNameCn︰" + StringUtils.notNull(accountNameCn) + "\n");
        result.append("accountNameEn︰" + StringUtils.notNull(accountNameEn) + "\n");
        result.append("accountName︰" + StringUtils.notNull(accountName) + "\n");
        result.append("accountNameAlias︰" + StringUtils.notNull(accountNameAlias) + "\n");
        result.append("accountType︰" + StringUtils.notNull(accountType) + "\n");
        result.append("country︰" + StringUtils.notNull(country) + "\n");
        result.append("province︰" + StringUtils.notNull(province) + "\n");
        result.append("city︰" + StringUtils.notNull(city) + "\n");
        result.append("cityzone︰" + StringUtils.notNull(cityzone) + "\n");
        result.append("industry︰" + StringUtils.notNull(industry) + "\n");
        result.append("accountLevel︰" + StringUtils.notNull(accountLevel) + "\n");
        result.append("property︰" + StringUtils.notNull(property) + "\n");
        result.append("postcode︰" + StringUtils.notNull(postcode) + "\n");
        result.append("address︰" + StringUtils.notNull(address) + "\n");
        // 對電話等信息作特殊處理。如果是以實體方式傳遞過來的，則將其轉換成字符串型
        if (isOssTelephone) {
            if ((getPhones() != null) && (getPhones().size() > 0)) {
                fax = "";
                telephone = "";
                for (OssTelephone phone : getPhones()) {
                    if (OssTelephone.OSS_TELEPHONE_TYPE_FAX.equals(phone.getTelephoneType())) {
                        fax += phone.toString() + ",";
                    }
                    else {
                        telephone += phone.toString() + ",";
                    }
                }
                if (fax.endsWith(",")) {
                    fax = fax.substring(0, fax.length() - 1);
                }
                if (telephone.endsWith(",")) {
                    telephone = telephone.substring(0, telephone.length() - 1);
                }
            }
        }
        result.append("telephone︰" + StringUtils.notNull(telephone) + "\n");
        result.append("fax︰" + StringUtils.notNull(fax) + "\n");
        result.append("email︰" + StringUtils.notNull(email) + "\n");
        result.append("homepage︰" + StringUtils.notNull(homepage) + "\n");
        result.append("employees︰" + (employees == null ? "" : String.valueOf(employees)) + "\n");
        result.append("contactMan︰" + StringUtils.notNull(contactMan) + "\n");
        result.append("description︰" + StringUtils.notNull(description) + "\n");
        result.append("remark︰" + StringUtils.notNull(remark) + "\n");
        result.append("originType︰" + StringUtils.notNull(originType) + "\n");
        result.append("originId︰" + StringUtils.notNull(originId) + "\n");
        result.append("originStatus︰" + StringUtils.notNull(originStatus) + "\n");
        result.append("originInformation︰" + StringUtils.notNull(originInformation) + "\n");
        result.append("tempMicId︰" + StringUtils.notNull(tempMicId) + "\n");
        result.append("ownerId︰" + StringUtils.notNull(ownerId) + "\n");
        result.append("currentUserId︰" + StringUtils.notNull(currentUserId) + "\n");
        result.append("companyId︰" + (companyId == null ? "" : String.valueOf(companyId)) + "\n");
        result.append("delTelephone_ids︰" + StringUtils.notNull(delTelephone));
        result.append("\n");
        result.append("memberType︰" + StringUtils.notNull(memberType) + "\n");
        result.append("areaRemark︰" + StringUtils.notNull(areaRemark));
        return result.toString();
    }

    /**
     * @return the originStatus
     */
    public String getOriginStatus() {
        return originStatus;
    }

    /**
     * @param originStatus the originStatus to set
     */
    public void setOriginStatus(String originStatus) {
        this.originStatus = originStatus;
    }

    public String getAreaRemark() {
        return areaRemark;
    }

    public void setAreaRemark(String areaRemark) {
        this.areaRemark = areaRemark;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @param parentAccount the parentAccount to set
     */
    public void setParentAccount(Long parentAccount) {
        this.parentAccount = parentAccount;
    }

    /**
     * @return the parentAccount
     */
    public Long getParentAccount() {
        return parentAccount;
    }
}
