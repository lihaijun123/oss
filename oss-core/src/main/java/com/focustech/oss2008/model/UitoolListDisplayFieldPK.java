package com.focustech.oss2008.model;

import java.io.Serializable;

public class UitoolListDisplayFieldPK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private Long funcId;

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME + result + (fieldName == null ? 0 : fieldName.hashCode());
		result = PRIME + result + (funcId == null ? 0 : funcId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(!(obj instanceof UitoolListDisplayFieldPK)){
			return false;
		}
		UitoolListDisplayFieldPK pk = (UitoolListDisplayFieldPK)obj;
		if(fieldName.equals(pk.getFieldName()) && funcId == pk.getFuncId()){
			return true;
		}
		return false;
	}



}
