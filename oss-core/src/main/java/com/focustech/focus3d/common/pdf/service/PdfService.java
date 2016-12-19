package com.focustech.focus3d.common.pdf.service;

import com.focustech.focus3d.common.pdf.PdfEntity;

/**
 * 提供给业务调用的接口
 * *
 * @author lihaijun
 *
 */
public interface PdfService {
	/**
	 * 从velocity构建
	 * *
	 * @param dataObject 数据对象
	 * @param dataKeyAry 数据对象对应的velocity里面的变量名称
	 * @param velocityName velocity名称
	 * @param outputPdfName 输出pdf的名称
	 * @return
	 */
	public PdfEntity createByVolicity(Object dataObject, String[] dataKeyAry, String velocityName, String outputPdfName);
	/**
	 * 从html构建
	 * *
	 * @param dataObject
	 * @param dataKeyAry
	 * @param htmlFile
	 * @return
	 */
	public PdfEntity createByHtml(String htmlFile, String outputPdfName);
}
