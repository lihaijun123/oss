package com.focustech.oss2008.web.tags;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.HiddenInputTag;
import org.springframework.web.servlet.tags.form.TagWriter;
/**
 * 扩展spring的hidden表单控件
 * 当时对象属性有自定义属性编辑器时取自定义属性值中的getAsText值
 * 如果没有取默认值
 * *
 * @author lihaijun
 *
 */
public class SpringHiddenTagExtend extends HiddenInputTag {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

}
