package com.focustech.oss2008.service;

import java.util.Date;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.common.utils.TCUtil;
import com.focustech.model.common.BaseEntity;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.exception.mvc.UnSuccessfulLoginException;
import com.focustech.oss2008.model.OssAdminUser;

/**
 * 业务支撑
 * *
 * @author lihaijun
 *
 */
public abstract class AbstractServiceSupport {
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_SERVICE);

    /**
     * 取得当前用户登录信息
     *
     * @return 当前登录用户
     * @throws UnSuccessfulLoginException
     * @see OssAdminUser
     */
    protected OssAdminUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        if (null != principal && authentication.isAuthenticated()) {
            return (OssAdminUser) principal;
        }
        else {
        	OssAdminUser user = new OssAdminUser();
        	user.setUserId("90000004");
        	user.setFullname("system");
        	return user;
        }
    }

    /**
     * 当前用户是否登录成功
     */
    protected boolean isLoginSuccessful() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        if (null != principal && authentication.isAuthenticated()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 当前登录用户ip
     *
     * @return
     */
    protected String getLoginIp() {
        if (isLoginSuccessful()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object credentials = authentication == null ? null : authentication.getDetails();
            if (credentials instanceof WebAuthenticationDetails)
                return ((WebAuthenticationDetails) credentials).getRemoteAddress();
            else
                throw new UnSuccessfulLoginException();
        }
        else
            throw new UnSuccessfulLoginException();
    }
    /**
     *
     * *
     * @return
     */
    public String getUserName(){
    	return getLoginUser().getFullname();
    }
    /**
     *
     * *
     * @return
     */
    public String getLoginUserId(){
    	return  TCUtil.sv(getLoginUser().getUserId());
    }
	 /**
     * 设置新建对象的默认属性值
     * *
     * @param baseEntity
     * @return
     */
	public BaseEntity setNewInfo(BaseEntity baseEntity){
    	String fullname = getLoginUser().getFullname();
    	Long userSn = TCUtil.lv(getLoginUser().getUserId());
    	Date dateTime = new Date();
    	try {
    		baseEntity.setAdderName(fullname);
    		baseEntity.setAdderSn(userSn);
    		baseEntity.setAddTime(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return baseEntity;
    }
    /**
     * 设置更新对象的默认属性值
     * *
     * @param baseEntity
     * @return
     */
    public BaseEntity setUpdateInfo(BaseEntity baseEntity){
    	String fullname = getLoginUser().getFullname();
    	Long userSn = TCUtil.lv(getLoginUser().getUserId());
    	Date dateTime = new Date();
    	baseEntity.setUpdaterSn(userSn);
    	baseEntity.setUpdaterName(fullname);
    	baseEntity.setUpdateTime(dateTime);
    	return baseEntity;
    }
}
