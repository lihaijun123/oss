package com.focustech.extend.spring.aspect;

import java.lang.reflect.Field;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.cief.ibatis.domain.EntityDelete;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.model.common.AuditEntity;
import com.focustech.model.common.BaseEntity;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.DBCommonService;

/**
 * 数据库操作信息切面
 * 管理字段
 * adderSn,adderName,addTime,updaterSn,updaterName,updateTime
 * 设置默认值，业务代码中不需要再赋值
 * *
 * @author lihaijun
 *
 */
@Aspect
public class DBRecordAspect extends AbstractServiceSupport{
	public static final String PATH = "com.focustech.oss2008.dao.AbstractHibernateDaoSupport";
	public static final String METHOD_INSERT = "insert";
	public static final String METHOD_UPDATE = "update";
	public static final String METHOD_DELETE = "delete";
	public static final String METHOD_INSERTORUPDATE = "insertOrUpdate";

	@Autowired
	private DBCommonService<BaseEntity> baseEntityService;

	@Pointcut(value = "execution(* " + PATH + "." + METHOD_INSERT + "(..))")
	public void insertPointCut(){

	}
	@Pointcut(value = "execution(* " + PATH + "." + METHOD_UPDATE + "(..))")
	public void updatePointCut(){

	}
	@Pointcut(value = "execution(* " + PATH + "." + METHOD_DELETE + "(..))")
	public void deletePointCut(){

	}
	@Pointcut(value = "execution(* " + PATH + "." + METHOD_INSERTORUPDATE + "(..))")
	public void insertOrUpdatePointCut(){

	}

	@Before(value = "insertPointCut() || updatePointCut() || deletePointCut() || insertOrUpdatePointCut()")
	public void setDefaultValue(JoinPoint jp){
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		Object obj = args[0];
		if(obj instanceof BaseEntity){
			if(METHOD_INSERT.equals(methodName) || METHOD_INSERTORUPDATE.equals(methodName)){
				setNew((BaseEntity)obj);
				setUpdate((BaseEntity)obj);
			}
			if(METHOD_UPDATE.equals(methodName)){
				setUpdate((BaseEntity)obj);
			}
			if(METHOD_DELETE.equals(methodName)){
				setDelete(obj, 1);
				setUpdate((BaseEntity)obj);
			}
		}
	}
	 /**
     * 设置新建对象的默认属性值
     * *
     * @param baseEntity
     * @return
     */
	private BaseEntity setNew(BaseEntity baseEntity){
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
    private BaseEntity setUpdate(BaseEntity baseEntity){
    	try {
    		BaseEntity brother = baseEntityService.get(baseEntity);
    		boolean isModify = true;
    		if(brother != null){
    			Long adderSn = brother.getAdderSn();
    			String adderName = brother.getAdderName();
    			Date addTime = brother.getAddTime();
    			if(adderSn != null && StringUtils.isNotEmpty(adderName) && addTime != null){
    				baseEntity.setAdderSn(adderSn);
    				baseEntity.setAdderName(adderName);
    				baseEntity.setAddTime(addTime);
    			} else {
    				setNew(baseEntity);
    			}
    			Long updaterSn = brother.getUpdaterSn();
    			String updaterName = brother.getUpdaterName();
    			Date updateTime = brother.getUpdateTime();
    			if(updaterSn != null && StringUtils.isNotEmpty(updaterName) && updateTime != null){
    				baseEntity.setUpdaterSn(updaterSn);
    				baseEntity.setUpdaterName(updaterName);
    				baseEntity.setUpdateTime(updateTime);
    			}
    			Class<? extends BaseEntity> baseEntityClas = baseEntity.getClass();
    			Field[] declaredFields = baseEntityClas.getDeclaredFields();
    			for (Field field : declaredFields) {
    				if(field.getName().equals("auditorSn")){
    					isModify = false;
    					break;
    				}
    			}
    		}
    		if(isModify){
    			baseEntity.setUpdaterName(getLoginUser().getFullname());
    			baseEntity.setUpdaterSn(TCUtil.lv(getLoginUser().getUserId()));
    			baseEntity.setUpdateTime(new Date());
    		}
    		if(baseEntity instanceof AuditEntity){
    			AuditEntity auditEntity = (AuditEntity)baseEntity;
    			auditEntity.setAuditorSn(TCUtil.lv(getLoginUser().getUserId()));
    			auditEntity.setAuditorName(getLoginUser().getFullname());
    			auditEntity.setAuditTime(new Date());
    		}
    		setDelete(baseEntity, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return baseEntity;
    }

    private void setDelete(Object obj, int code) {
		if(obj instanceof EntityDelete){
			EntityDelete entityDelete = (EntityDelete)obj;
			entityDelete.setIsDelete(code);
		}
	}
}
