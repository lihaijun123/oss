package com.focustech.focus3d.web.interceptor;

import com.focustech.cief.cop.ws.client.cxf.auth.UserPasswordManager;
import com.focustech.focus3d.service.AbstractCiefService;
import com.focustech.focus3d.utils.EncryptUtil;

public class CiefUserPasswordManager extends AbstractCiefService implements UserPasswordManager{

	@Override
	public String getUserName() {
		return getLoginUser().getFullname();
	}

	@Override
	public String getUserSn() {
		return EncryptUtil.encode(getLoginUser().getUserId());
	}

}
