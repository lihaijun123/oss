package com.focustech.oss2008.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Entity
@Table(name = "uitool_func_display_info")
public class UitoolFuncDisplayInfo {
	@Id
	private long funcId;
	private String funcTitle;
	private String displayCss;
	private String displayJs;
	private String displayPage;
	private Integer likeType;
	private Integer pageMax;
	private Integer totalPageLimit;
	private Integer totalLimit;
	@Transient
	private List<UitoolListDisplayField> displayFields = new AutoArrayList<UitoolListDisplayField>(UitoolListDisplayField.class);
	public long getFuncId() {
		return funcId;
	}
	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}
	public String getFuncTitle() {
		return funcTitle;
	}
	public void setFuncTitle(String funcTitle) {
		this.funcTitle = funcTitle;
	}
	public String getDisplayCss() {
		return displayCss;
	}
	public void setDisplayCss(String displayCss) {
		this.displayCss = displayCss;
	}
	public String getDisplayJs() {
		return displayJs;
	}
	public void setDisplayJs(String displayJs) {
		this.displayJs = displayJs;
	}
	public String getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(String displayPage) {
		this.displayPage = displayPage;
	}
	public Integer getLikeType() {
		return likeType;
	}
	public void setLikeType(Integer likeType) {
		this.likeType = likeType;
	}
	public Integer getPageMax() {
		return pageMax;
	}
	public void setPageMax(Integer pageMax) {
		this.pageMax = pageMax;
	}
	public Integer getTotalPageLimit() {
		return totalPageLimit;
	}
	public void setTotalPageLimit(Integer totalPageLimit) {
		this.totalPageLimit = totalPageLimit;
	}
	public Integer getTotalLimit() {
		return totalLimit;
	}
	public void setTotalLimit(Integer totalLimit) {
		this.totalLimit = totalLimit;
	}
	public List<UitoolListDisplayField> getDisplayFields() {
		return displayFields;
	}
	public void setDisplayFields(List<UitoolListDisplayField> displayFields) {
		this.displayFields.clear();
		this.displayFields.addAll(displayFields);
	}

}
