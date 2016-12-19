package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
/**
 * cief 图片标签
 * *
 * @author lihaijun
 *
 */
public class CiefImgTag extends AbstractHtmlElementTag{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String width;

	private String height;

	private String src;

	private String value;//cfs文件编号



	@Override
	protected int writeTagContent(TagWriter tagwriter) throws JspException {
		if(StringUtils.isEmpty(id)){
			throw new IllegalArgumentException("标签id不能为空");
		}
		if(StringUtils.isEmpty(name)){
			throw new IllegalArgumentException("标签name不能为空");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<img ");
		sb.append(" id='" + id + "'");
		sb.append(" name='" + name + "'");
		if(StringUtils.isNotEmpty(width)){
			sb.append(" width='" + width + "'");
		}
		if(StringUtils.isNotEmpty(height)){
			sb.append(" height='" + height + "'");
		}
		if(StringUtils.isNotEmpty(value)){
			String fileURL = FileManageUtil.getFileURL(TCUtil.lv(value));
			if(StringUtils.isNotEmpty(fileURL)){
				src = fileURL;
			}
		}
		sb.append(" src='" + src + "'");
		sb.append("/>");
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getWidth() {
		return width;
	}



	public void setWidth(String width) {
		this.width = width;
	}



	public String getHeight() {
		return height;
	}



	public void setHeight(String height) {
		this.height = height;
	}



	public String getSrc() {
		return src;
	}



	public void setSrc(String src) {
		this.src = src;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}

}
