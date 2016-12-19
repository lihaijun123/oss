package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
/**
 * 请求重定向消息
 * *
 * @author lihaijun
 *
 */
public class RedirectAttributesTag extends AbstractHtmlElementTag{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String flashAttribute;

	public String getFlashAttribute() {
		return flashAttribute;
	}

	public void setFlashAttribute(String flashAttribute) {
		this.flashAttribute = flashAttribute;
	}

	@Override
	protected int writeTagContent(TagWriter tagwriter) throws JspException {
		if(StringUtils.isEmpty(flashAttribute)){
			flashAttribute = "message";
		}
		HttpSession session = pageContext.getSession();
		Object value = session.getAttribute(flashAttribute);
		StringBuffer html = new StringBuffer();
		if(value != null){
			html.append("<font color='red'>");
			html.append(TCUtil.sv(value));
			html.append("</font>");
			session.removeAttribute(flashAttribute);
		}
		try {
			pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

}
