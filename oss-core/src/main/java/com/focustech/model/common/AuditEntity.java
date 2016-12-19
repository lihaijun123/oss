package com.focustech.model.common;

import java.util.Date;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface AuditEntity {

	public void setAuditRemark(String auditRemark);

	public void setAuditorSn(Long auditorSn);

	public void setAuditorName(String auditorName);

	public void setAuditTime(Date auditTime);
}
