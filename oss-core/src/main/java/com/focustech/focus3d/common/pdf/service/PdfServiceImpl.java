package com.focustech.focus3d.common.pdf.service;

import org.apache.velocity.app.VelocityEngine;

import com.focustech.focus3d.common.pdf.AbstractPdfFactory;
import com.focustech.focus3d.common.pdf.HtmlPdfFactory;
import com.focustech.focus3d.common.pdf.PdfEntity;
import com.focustech.focus3d.common.pdf.VelocityPdfFactory;

/**
 *
 * *
 * @author lihaijun
 *
 */

public class PdfServiceImpl implements PdfService{
	/**模板引擎*/
	private VelocityEngine velocityEngine;

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 *
	 * *
	 */
	@Override
	public PdfEntity createByVolicity(Object dataObject, String[] dataKeyAry, String velocityName, String outputPdfName) {
		AbstractPdfFactory pdfFactory = VelocityPdfFactory.getInstance(velocityEngine);
		PdfEntity pdfEntity = pdfFactory.create(velocityName, outputPdfName, dataObject, dataKeyAry);
		return pdfEntity;
	}

	/**
	 *
	 * *
	 */
	@Override
	public PdfEntity createByHtml(String htmlFile, String outputPdfName) {
		AbstractPdfFactory pdfFactory = HtmlPdfFactory.getInstance();
		PdfEntity pdfEntity = pdfFactory.create(htmlFile, outputPdfName, null, null);
		return pdfEntity;
	}

}
