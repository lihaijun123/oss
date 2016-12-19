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
public class FileDownloadTag extends AbstractHtmlElementTag {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String value;



	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	protected int writeTagContent(TagWriter arg0) throws JspException {
		String url = "";
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(value)){
			url = FileManageUtil.getFileURL(TCUtil.lv(value));
			if(StringUtils.isEmpty(getName())){
				this.name = FileManageUtil.getFileName(TCUtil.lv(value));
			}
			sb.append("<a href='javascript:download(\" " + url + "\", \"" + getName() + "\")'> ");
			sb.append("下载");
			sb.append("</a>");
		}
		setName("");
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

}
