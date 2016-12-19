package com.focustech.focus3d.common.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

/**
 * pdf生成的抽象工厂
 * *
 * @author lihaijun
 *
 */
public abstract class AbstractPdfFactory {
	/**新生成的pdf存放路径*/
	private static final String OUTPUT_LOCATION = Thread.currentThread().getContextClassLoader().getResource("pdf" + File.separator).getPath() + "files" + File.separator;
	/**字体文件路径*/
	private static final String FONT_LOCATION = Thread.currentThread().getContextClassLoader().getResource("pdf" + File.separator + "fonts" + File.separator).getPath() + File.separator + "simsun.ttc";
	/**图片路径*/
	private static final String IMG_LOCATION = "file:" + Thread.currentThread().getContextClassLoader().getResource("pdf" + File.separator + "img" + File.separator).getPath() + File.separator;

	protected String outputPdfName;
	/**
	 * *
	 * @param velocityName
	 * @param dataObject
	 * @param dataKeyAry
	 * @return
	 */
	public abstract PdfEntity create(String velocityName, String outputPdfName, Object dataObject, String[] dataKeyAry);

	/**
	 * pdf创建的模板方法
	 * *
	 * @return
	 */
	protected PdfEntity create(){
		try {
			//创建存放目录
			File file = new File(OUTPUT_LOCATION);
            if (!file.exists()) {
                file.mkdir();
            }
			OutputStream outputStream = new FileOutputStream(OUTPUT_LOCATION + "/" + getOutputFileName());
			ITextRenderer renderer = new ITextRenderer();
			SetDocumentByWay(renderer);
			// 解决中文支持问题
			ITextFontResolver fontResolver = renderer.getFontResolver();
			fontResolver.addFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			// 解决图片的相对路径问题
			renderer.getSharedContext().setBaseURL(IMG_LOCATION);
			renderer.layout();
			renderer.createPDF(outputStream);
			outputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PdfEntity pdfEntity = new PdfEntity();
		pdfEntity.setName(getOutputFileName());
		pdfEntity.setSavePath(OUTPUT_LOCATION + getOutputFileName());
		pdfEntity.setContent(getPdfContent());
		return pdfEntity;
	}

	/**
	 * 内容来源
	 * *
	 * @param renderer
	 */
	protected abstract void SetDocumentByWay(ITextRenderer renderer);
	/**
	 * 输出的pdf文件名称
	 * *
	 * @return
	 */
	protected abstract String getOutputFileName();
	/**
	 * 输出的pdf的内容
	 * *
	 * @return
	 */
	protected abstract String getPdfContent();
	/**
	 * *
	 * @return
	 */
}
