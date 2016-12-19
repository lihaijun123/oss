package com.focustech.focus3d.common.pdf;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * velocity的模板生成的pdf内容
 * *
 * @author lihaijun
 *
 */
public class VelocityPdfContent extends AbstractPdfContent {
	private static final String ENCODE_UTF_8 = "UTF-8";
	/**模板信息封装对象*/
	private AbstractPdfTemplate velocityPdfTemplate;
	/**模板引擎*/
	private VelocityEngine velocityEngine;
	/**
	 * *
	 * @param velocityEngine
	 * @param velocityPdfTemplate
	 */
	public VelocityPdfContent(VelocityEngine velocityEngine, AbstractPdfTemplate velocityPdfTemplate){
		this.velocityEngine = velocityEngine;
		this.velocityPdfTemplate = velocityPdfTemplate;
	}

	@Override
	public String buildContent() {
		try {
			return VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine,
						velocityPdfTemplate.getName(),
						ENCODE_UTF_8,
						((VelocityPdfTemplate)velocityPdfTemplate).getKeyValueMap()
					);
		}
		catch (VelocityException e) {
			e.printStackTrace();
		}
		return "";
	}
}
