package com.focustech.focus3d.common.pdf;
/**
 * pdf内容的抽象工厂类
 * *
 * @author lihaijun
 *
 */
public abstract class AbstractPdfContentFactory {
	/**
	 * 获取pdf内容
	 * *
	 * @return
	 */
	public abstract AbstractPdfContent getPdfContent();
}
