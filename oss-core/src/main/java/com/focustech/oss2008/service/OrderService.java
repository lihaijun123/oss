/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.oss2008.service;
/**
 *  OrderService.java
 *
 * @author sunjingyu
 */
public interface OrderService {

	/************** 在線販售默認屬性 *****************/
    /** 在線販售缺省System用戶ID **/
	public static final String SYSTEM_USER_ID = "90000004";
	/** 在線販售缺省System用戶名稱  **/
	public static final String SYSTEM_USER_NAME = "System";
	/** 在線販售缺省客戶別名 **/
	public static final String EWEB_ACCOUNT_ALIAS = "在線販售客戶";




	public Long synchroContract(Object[] objs);

	public void payment(Object[] objs);
}
