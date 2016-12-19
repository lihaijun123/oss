package com.focustech.oss2008.web.tags.impls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.FormTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * 解決對于界面上返回的問題
 *
 * @author hexuey
 */
public class FocusFormTag extends FormTag {
    public static final String REQ_HID_PARAM_ITEM = "_h_referer";
    private static final long serialVersionUID = -3626915954099714043L;

    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        super.writeTagContent(tagWriter);
        //
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String referer = request.getParameter(REQ_HID_PARAM_ITEM);
        if (referer == null) {
            referer = request.getHeader("referer");
            if (referer == null)
                referer = "";
            request.setAttribute(REQ_HID_PARAM_ITEM, referer);
        }
        tagWriter.appendValue("\r\n<input type=\"hidden\" name=\"" + REQ_HID_PARAM_ITEM + "\" id=\""
                + REQ_HID_PARAM_ITEM + "\" value=\"" + referer + "\"/>");
        return EVAL_BODY_INCLUDE;
    }
}
