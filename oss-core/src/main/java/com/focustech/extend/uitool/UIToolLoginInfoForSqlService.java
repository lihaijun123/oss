package com.focustech.extend.uitool;

import com.focustech.common.utils.StringUtils;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.uitool.list.spi.UIToolLoginInfoForSql;
/**
 * sql中登录用户信息管理服务
 * *
 * @author lihaijun
 *
 */
public class UIToolLoginInfoForSqlService extends AbstractServiceSupport implements UIToolLoginInfoForSql  {

	@Override
	public String replaceUserSn(String sql) {
		if(StringUtils.isNotEmpty(sql) && sql.contains(LOGIN_USER_SN)){
			if(isLoginSuccessful()){
				return sql.replace(LOGIN_USER_SN, getLoginUserId());
			}
		}
		return sql;
	}

}
