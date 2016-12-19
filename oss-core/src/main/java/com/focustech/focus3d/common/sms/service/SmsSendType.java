package com.focustech.focus3d.common.sms.service;
/**
 *
 * *
 * @author lihaijun
 *
 */
public enum SmsSendType {
	TCR_USER_AUDIT(1, "老师系统审核通知");

	private int type;
	private String name;

	SmsSendType(int type, String name){
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * *
	 * @param type
	 * @return
	 */
	public static SmsSendType valueOf(int type){
		for(SmsSendType t : SmsSendType.values()){
			if(t.getType() == type){
				return t;
			}
		}
		return null;
	}

	public boolean equals(SmsSendType sendType){
		boolean flag = false;
		if(sendType != null){
			flag = sendType.getType() == this.getType();
		}
		return flag;
	}
}
