package com.focustech.focus3d.common.excel;

import java.util.Map;


public class SheetEnttiy {

	// 工作表名
	private String sheetName;
	// 工作表表头字段编号
	private String[] sheetHead;
	// 工作表表头名
	private Map<String, String> headNameMap;
	// 工作表数据
	private Map<String, Object> detailMap;
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the sheetHead
	 */
	public String[] getSheetHead() {
		return sheetHead;
	}
	/**
	 * @param sheetHead the sheetHead to set
	 */
	public void setSheetHead(String[] sheetHead) {
		this.sheetHead = sheetHead;
	}

	/**
	 * @return the headNameMap
	 */
	public Map<String, String> getHeadNameMap() {
		return headNameMap;
	}
	/**
	 * @param headNameMap the headNameMap to set
	 */
	public void setHeadNameMap(Map<String, String> headNameMap) {
		this.headNameMap = headNameMap;
	}
	/**
	 * @return the detailMap
	 */
	public Map<String, Object> getDetailMap() {
		return detailMap;
	}
	/**
	 * @param detailMap the detailMap to set
	 */
	public void setDetailMap(Map<String,Object> detailMap) {
		this.detailMap = detailMap;
	}


}
