package com.focustech.focus3d.common.sms.service;

import java.util.Map;
/**
 *
 * *
 * @author lihaijun
 *
 */
public interface SmsService {

	public int send(SmsSendType sendType, String mobile, Map<String, String> parame);
	public int send(SmsSendType sendType, Map<String, String> parame);
}
