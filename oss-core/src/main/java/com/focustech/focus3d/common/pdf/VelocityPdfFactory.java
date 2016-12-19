package com.focustech.focus3d.common.pdf;

import org.apache.velocity.app.VelocityEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;


/**
 * pdf生成工厂类
 * *
 * @author lihaijun
 *
 */
public class VelocityPdfFactory extends AbstractPdfFactory{
	/**pdf内容*/
	private String pdfContent;
	/**模板引擎*/
	private VelocityEngine velocityEngine;
	/**
	 * *
	 * @param velocityEngine
	 * @return
	 */
	public static VelocityPdfFactory getInstance(VelocityEngine velocityEngine){
		return new VelocityPdfFactory(velocityEngine);
	}
	/**
	 * *
	 * @param velocityEngine
	 */
	private VelocityPdfFactory(VelocityEngine velocityEngine){
		this.velocityEngine = velocityEngine;
	}
	@Override
	protected String getOutputFileName() {
		return this.outputPdfName;
	}
	@Override
	protected String getPdfContent() {
		return this.pdfContent;
	}
	@Override
	protected void SetDocumentByWay(ITextRenderer renderer) {
		renderer.setDocumentFromString(getPdfContent());
	}
	@Override
	public PdfEntity create(String velocityName, String outputPdfName, Object dataObject, String[] dataKeyAry) {
		AbstractPdfContentFactory pdfContentFactory = VelocityPdfContentFactory.getInstance(velocityEngine, velocityName, dataObject, dataKeyAry);
		this.pdfContent = pdfContentFactory.getPdfContent().buildContent();
		this.outputPdfName = outputPdfName;
		return create();
	}
}
