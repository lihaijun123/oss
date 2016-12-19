package com.focustech.focus3d.common.sms.service.impl;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.focustech.common.utils.DateUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.common.sms.service.SmsSendType;
import com.focustech.focus3d.common.sms.service.SmsService;
/**
 * 短信服务
 * *
 * @author lihaijun
 *
 */
public class SmsServiceImpl implements SmsService {
	//private SmsClient smsClient;
	private String focus3dMobileList;
	private static final String PREFIX = "[小小梦想家]";
	private ExecutorService pool = Executors.newFixedThreadPool(4);

	@Override
	public int send(SmsSendType sendType, String mobileStr, Map<String, String> parame) {
		boolean isSend = true;
		int status = 0;
		if(parame != null){
			String msg = "";
			String curDate = DateUtils.getCurDate(DateUtils.DEFAULT_FORMATE_ALL);
			if(SmsSendType.TCR_USER_AUDIT.equals(sendType)){
				String sta = TCUtil.sv(parame, "status");
				String message = TCUtil.sv(parame, "message");
				msg = PREFIX + "审核通知：您的注册信息" + ("3".equals(sta) ? "审核通过，欢迎登录使用！" : ("审核拒绝，原因：" + message)) + ", 时间：" + curDate;
			}
			if(isSend){
				//发短信通知
				pool.execute(new smsTask(mobileStr, msg));
			}
		}
		return status;
	}
	/**
	 *
	 * *
	 * @author lihaijun
	 *
	 */
	class smsTask implements Runnable {

		private String mobileStr;
		private String msg;

		public smsTask(String mobileStr, String msg){
			this.mobileStr = mobileStr;
			this.msg = msg;
		}
		@Override
		public void run() {
			/*String[] split = mobileStr.split(",");
			for (String mobile : split) {
				try {
					if(StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(msg)){
						smsClient.send(mobile, msg);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}*/
		}
	}

	public String getFocus3dMobileList() {
		return focus3dMobileList;
	}
	public void setFocus3dMobileList(String focus3dMobileList) {
		this.focus3dMobileList = focus3dMobileList;
	}
	@Override
	public int send(SmsSendType sendType, Map<String, String> parame) {
		return send(sendType, "", parame);
	}
	/*public SmsClient getSmsClient() {
		return smsClient;
	}
	public void setSmsClient(SmsClient smsClient) {
		this.smsClient = smsClient;
	}*/

}
