package com.focustech.focus3d.common.pdf;

import java.io.File;
import java.net.MalformedURLException;

import org.xhtmlrenderer.pdf.ITextRenderer;
/**
 * html转pdf
 * *
 * @author lihaijun
 *
 */
public class HtmlPdfFactory extends AbstractPdfFactory {

	private String htmlFile;

	public static HtmlPdfFactory getInstance(){
		return new HtmlPdfFactory();
	}

	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}

	@Override
	public PdfEntity create(String htmlFile, String outputPdfName, Object dataObject, String[] dataKeyAry) {
		this.htmlFile = htmlFile;
		this.outputPdfName = outputPdfName;
		return create();
	}

	@Override
	protected void SetDocumentByWay(ITextRenderer renderer) {
		try {
			String url = new File(htmlFile).toURI().toURL().toString();
			renderer.setDocument(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getOutputFileName() {
		//后续改为业务需求的名称
		return this.outputPdfName;
	}

	@Override
	protected String getPdfContent() {
		//提取html页面内容
		return htmlFile;
	}

}
