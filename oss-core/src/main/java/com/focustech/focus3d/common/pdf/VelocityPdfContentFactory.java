package com.focustech.focus3d.common.pdf;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;

/**
 * velocity内容生成工厂 *
 *
 * @author lihaijun
 *
 */
public class VelocityPdfContentFactory extends AbstractPdfContentFactory {
	/** 模板引擎 */
	private VelocityEngine velocityEngine;
	private Object dataObject;
	private String[] dataKeyAry;
	private String velocityName;

	/**
	 * *
	 *
	 * @param velocityEngine
	 * @param dataObject
	 * @param dataKeyAry
	 * @return
	 */
	public static VelocityPdfContentFactory getInstance(VelocityEngine velocityEngine, String velocityName, Object dataObject, String[] dataKeyAry) {
		return new VelocityPdfContentFactory(velocityEngine, velocityName, dataObject, dataKeyAry);
	}

	private VelocityPdfContentFactory(VelocityEngine velocityEngine, String velocityName, Object dataObject, String[] dataKeyAry) {
		this.velocityEngine = velocityEngine;
		this.dataObject = dataObject;
		this.dataKeyAry = dataKeyAry;
		this.velocityName = velocityName;
	}

	@Override
	public AbstractPdfContent getPdfContent() {
		AbstractPdfContent pdfContent = null;
		if (dataObject.getClass().isArray()) {
			pdfContent = new VelocityPdfContent(velocityEngine, buildPdfTemplateByAry());
		} else if (dataObject instanceof Map) {
			throw new RuntimeException("暂不实现");
		} else {
			throw new RuntimeException("暂不实现");
		}
		return pdfContent;
	}

	/**
	 *
	 * *
	 *
	 * @param pdfTemplate
	 * @return
	 */
	public AbstractPdfTemplate buildPdfTemplateByAry() {
		AbstractPdfTemplate pdfTemplate = new VelocityPdfTemplate();
		Object[] value = (Object[]) this.dataObject;
		Map<String, Object> keyValueMap = new HashMap<String, Object>();
		for (int i = 0, j = dataKeyAry.length; i < j; i++) {
			keyValueMap.put(dataKeyAry[i], value[i]);
		}
		pdfTemplate.setName(velocityName);
		((VelocityPdfTemplate) pdfTemplate).setKeyValueMap(keyValueMap);
		return pdfTemplate;
	}

}
