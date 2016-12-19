package com.focustech.oss2008.bt;

import java.util.HashMap;
import java.util.Map;

import com.focustech.search.common.CheckConstant;

/**
 * Copyright (c) 2006, focustech All rights reserved 客戶基本信息以及相關信息數據結構
 *
 * @author tc-hexuey
 * @version 1.0 2008-6-30 上午09:51:12
 */
public class CustomerBt implements CheckConstant {
    /** 客戶編號 */
    private String id = "";
    /** 客戶名稱 */
    private String name = "";
    /** 客戶聯系人電話 */
    private String mobile = "";
    /** 客戶聯系電話 */
    private String phone = "";
    /** 客戶Email */
    private String email = "";
    /** 客戶主頁 */
    private String url = "";
    /***/
    private String member;

    //
    public Map<String, String> toMap() {
        Map<String, String> m = new HashMap<String, String>(7);
        m.put(ID, id);
        m.put(NAME, name);
        m.put(MOBILE, mobile);
        m.put(PHONE, phone);
        m.put(EMAIL, email);
        m.put(URL, url);
        m.put(MEMBER, member);
        return m;
    }

    //
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
