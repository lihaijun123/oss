package com.focustech.oss2008.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uitool_func_table")
public class UitoolFuncTable {
	@Id
	private long sn;
	private long funcId;
	private String tableName;
	public long getSn() {
		return sn;
	}
	public void setSn(long sn) {
		this.sn = sn;
	}

	public long getFuncId() {
		return funcId;
	}
	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
