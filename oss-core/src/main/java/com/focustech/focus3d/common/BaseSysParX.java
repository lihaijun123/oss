/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.focus3d.common;


/**
 * BaseSysParX.java
 *
 * @author liujincheng
 */
public class BaseSysParX {
	private Integer parId;
	private String parType;
	private String parKey;
	private String parValue;
	private String parentParKey;
	private Integer parSort;

	public Integer getParId() {
		return parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getParType() {
		return parType;
	}

	public void setParType(String parType) {
		this.parType = parType;
	}

	public String getParKey() {
		return parKey;
	}

	public void setParKey(String parKey) {
		this.parKey = parKey;
	}

	public String getParValue() {
		return parValue;
	}

	public void setParValue(String parValue) {
		this.parValue = parValue;
	}

	public String getParentParKey() {
		return parentParKey;
	}

	public void setParentParKey(String parentParKey) {
		this.parentParKey = parentParKey;
	}

	public Integer getParSort() {
		return parSort;
	}

	public void setParSort(Integer parSort) {
		this.parSort = parSort;
	}

}
