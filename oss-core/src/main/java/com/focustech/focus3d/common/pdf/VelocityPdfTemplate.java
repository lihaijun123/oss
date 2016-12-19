package com.focustech.focus3d.common.pdf;

import java.util.Map;

/**
 * 用velocity实现的模板 *
 * 
 * @author lihaijun
 * 
 */
public class VelocityPdfTemplate extends AbstractPdfTemplate {
	/** 模板参数 */
	private Map<String, Object> keyValueMap;

	public Map<String, Object> getKeyValueMap() {
		return keyValueMap;
	}

	public void setKeyValueMap(Map<String, Object> keyValueMap) {
		this.keyValueMap = keyValueMap;
	}
}
