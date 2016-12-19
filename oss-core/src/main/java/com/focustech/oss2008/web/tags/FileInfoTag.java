package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

/**
 * 文件服务器上面文件信息标签
 * *
 * @author lihaijun
 *
 */
public class FileInfoTag extends AbstractHtmlElementTag {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String value;
	private String hid;
	private String hname;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getHid() {
		return hid;
	}


	public void setHid(String hid) {
		this.hid = hid;
	}


	public String getHname() {
		return hname;
	}


	public void setHname(String hname) {
		this.hname = hname;
	}


	@Override
	protected int writeTagContent(TagWriter arg0) throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<span ");
		sb.append(" id=\"" + id + "\" >");
		String fileName = "";
		if(StringUtils.isNotEmpty(value)){
			fileName = FileManageUtil.getFileName(TCUtil.lv(value));
		}
		sb.append(fileName);
		sb.append("</span>");
		HiddenInputTag hiddenInputTag = new HiddenInputTag(hid, hname, value);
		sb.append(hiddenInputTag.toString());
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

}
