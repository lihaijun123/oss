package com.focustech.oss2008.web.tags;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

/**
 *
 * *
 * @author lihaijun
 *
 */
public class HiddenInputTag {
	private String hid;
	private String hname;
	private String hvalue;
	public HiddenInputTag(String hid, String hname, String hvalue){
		this.hid = hid;
		this.hname = hname;
		this.hvalue = hvalue;
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
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type=\"hidden\" ");
		if(StringUtils.isNotEmpty(hid)){
			sb.append(" id=\"" + hid + "\" ");
		}
		if(StringUtils.isNotEmpty(hname)){
			sb.append(" name=\"" + hname + "\" ");
		}
		sb.append(" value=\"" + TCUtil.sv(this.hvalue) + "\"/>");
		return sb.toString();
	}

}
