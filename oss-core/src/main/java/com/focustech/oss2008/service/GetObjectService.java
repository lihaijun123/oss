package com.focustech.oss2008.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface GetObjectService {
    /**
     * 根據產品ID、客戶ID，取得產品信息、資源預定列表
     *
     * @param productId 產品ID
     * @param accountId 客戶ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getProductResource(long productId, long accountId) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 根據產品ID、客戶ID、合同ID，取得產品信息、資源預定列表
     *
     * @param productId 產品ID
     * @param accountId 客戶ID
     * @param contractId 合同ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getProductResource(long productId, long accountId, long contractId) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 根據合同產品ID，取得合同產品信息、資源預定信息
     *
     * @param contractProductId 合同產品ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getAlterProduct(long contractProductId) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根據客戶ID,得到客戶信息
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getCustomer(long accountId) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根據產品ID、客戶ID，取得產品信息、資源預定列表
     *
     * @param productId 產品ID
     * @param accountId 客戶ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getBookingList(long productId, long accountId) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根據產品ID、客戶ID，取得產品信息、資源預定列表
     *
     * @param productId 產品ID
     * @param accountId 客戶ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getBookingListForModify(long conProId, long productId, long accountId) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 根據合同產品ID，取得產品信息、資源預定列表
     *
     * @param contractProductId 產品ID
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map getChildProducts(long contractProductId) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根據附件ID串,得到附件列表
     */
    public Map getAttachmentList(String attachmentStr) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根據合同編號,得到 所屬銷售人員Email
     */
    public Map getEmailByContractId(long contractId);

    /**
     * 修改產品 選擇資源
     *
     * @param conProdId 修改的合同產品列表
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map getSelBookingList(long conProdId) throws IllegalAccessException, InvocationTargetException;
}
