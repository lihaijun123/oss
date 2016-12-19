package com.focustech.oss2008.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 *
 * *
 * @author lihaijun
 *
 */
@Entity
@Table(name = "uitool_list_display_field")
@IdClass(UitoolListDisplayFieldPK.class)
public class UitoolListDisplayField implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String fieldName;
	@Id
	private Long funcId;
	private String displayText;
	private String url;
	private Integer displayIndex;
	private String isDisplay;
	private String isOrder;
	private String isFind;
	private String fetchValueType;
	private String titleUrl;
	private String fieldEvent;
	private String fieldFormat;
	private Integer isExpand;
	private Integer findType;
	private String numTotalDef;
	private Integer rowAttr;
	@Transient
	private String remarks;

	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public String getIsFind() {
		return isFind;
	}
	public void setIsFind(String isFind) {
		this.isFind = isFind;
	}
	public String getFetchValueType() {
		return fetchValueType;
	}
	public void setFetchValueType(String fetchValueType) {
		this.fetchValueType = fetchValueType;
	}
	public String getTitleUrl() {
		return titleUrl;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	public String getFieldEvent() {
		return fieldEvent;
	}
	public void setFieldEvent(String fieldEvent) {
		this.fieldEvent = fieldEvent;
	}
	public String getFieldFormat() {
		return fieldFormat;
	}
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}
	public Integer getIsExpand() {
		return isExpand;
	}
	public void setIsExpand(Integer isExpand) {
		this.isExpand = isExpand;
	}
	public Integer getFindType() {
		return findType;
	}
	public void setFindType(Integer findType) {
		this.findType = findType;
	}
	public String getNumTotalDef() {
		return numTotalDef;
	}
	public void setNumTotalDef(String numTotalDef) {
		this.numTotalDef = numTotalDef;
	}
	public Integer getRowAttr() {
		return rowAttr;
	}
	public void setRowAttr(Integer rowAttr) {
		this.rowAttr = rowAttr;
	}
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + new Long(funcId).hashCode();
		result = PRIME * result + (fieldName == null ? 0 : fieldName.hashCode());
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
		if(!(obj instanceof UitoolListDisplayField)){
			return false;
		}
		UitoolListDisplayField objKey = (UitoolListDisplayField)obj;
		if(funcId == objKey.getFuncId() && fieldName.equalsIgnoreCase(objKey.getFieldName())){
			return true;
		}
		return false;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
