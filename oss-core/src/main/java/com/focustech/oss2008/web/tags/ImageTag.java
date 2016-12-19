package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

/**
 * 跟文件服务器结合
 * *
 * @author lihaijun
 *
 */
public class ImageTag extends AbstractHtmlElementTag{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String hid;
	private String hname;

	private String src;
	private String dsrc;//默认
	private String width;
	private String height;
	private Boolean preview;//预览
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public Boolean getPreview() {
		return preview;
	}

	public void setPreview(Boolean preview) {
		this.preview = preview;
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		String url = "";
		if(StringUtils.isNotEmpty(src)){
			try {
				url = FileManageUtil.getFileURL(TCUtil.lv(src));
			} catch (Exception e) {

			}
		} else {
			if(StringUtils.isNotEmpty(dsrc)){
				url = dsrc;
			} else {
				url = "/js/uploadFile/pic120.gif";
			}
		}
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(url)){
			if(preview != null && preview){
				sb.append("<a href='" + url + "' class='fresco' data-fresco-group='focus3d' data-fresco-caption=''>");
			}
			sb.append("<img ");
			sb.append(" id=\"" + this.id + "\"");
			sb.append(" width=\"" + this.width + "\"");
			sb.append(" height=\"" + this.height + "\"");
			sb.append(" src=\"" + url + "\"");
			sb.append("/>");
			if(preview != null && preview){
				sb.append("</a>");
			}
			sb.append("<br>");
			HiddenInputTag hiddenInputTag = new HiddenInputTag(hid, hname, TCUtil.sv(this.src));
			sb.append(hiddenInputTag.toString());
		}
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
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

	public String getDsrc() {
		return dsrc;
	}

	public void setDsrc(String dsrc) {
		this.dsrc = dsrc;
	}
}
