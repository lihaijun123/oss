package com.focustech.oss2008.dao;

import java.util.List;

public interface TelephoneDao<T> extends BaseHibernateDao<T> {
    /**
     * 根據客戶ID獲取客戶基本信息中的電話
     *
     * @param accountId
     * @param type
     * @return
     */
    public List<T> getAccountTelephones(long accountId, String type);

    /**
     * 根據聯系人ID獲取聯系人信息中的電話
     *
     * @param contactId
     * @param type
     * @return
     */
    public List<T> getContactTelephones(long contactId, String type);

    /**
     * 刪除通過ID指定的電話信息。注意兩個ID之間的分隔方式
     *
     * @param idsCondition 用半角逗號分隔的ID，用于SQL中in條件的括號下
     */
    public void deleteByIds(String idsCondition);

    /**
     * 根據聯系人ID刪除所有該聯系人的電話
     *
     * @param contactId
     * @param type
     * @return
     */
    public void deleteContactTelephonesByContactId(long contactId);

    /**
     * 根據客戶ID與聯系人ID獲取指定客戶（包括聯系人）的所有電話、傳真等信息
     *
     * @param accountId 客戶編號，用于指定客戶
     * @param contactId 聯系人編號，用于排除當前操作的電話等。
     * @return 返回該客戶除頁面傳遞的其余所有電話等信息
     */
    public List<T> selectWholeAccountTelephonesByAccountId(long accountId, long contactId);

    /**
     * 獲取指定客戶的所有的電話、傳真等信息
     *
     * @param accountId 客戶編號
     * @return 返回獲取的電話信息
     */
    public List<T> selectTelephonesByAccountId(long accountId);
}
