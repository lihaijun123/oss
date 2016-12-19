package com.focustech.oss2008.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.InputTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.focustech.oss2008.utils.RoleResourceUtils;

/**
 * 角色權限範圍內的按鈕或連接顯示處理 根據當前用戶的權限範圍來決定當前指定的actId對應的動作是否有權限。
 *
 * @author tc-hexuey
 */
public class RoleInputTag extends InputTag {
    private long actId = -1;
    private String type = "";
    private String value = "";
    private String href = "";

    //
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        try {
            boolean scope = RoleResourceUtils.check(actId, pageContext);
            if (scope == false) {
                pageContext.getOut().write("");
                return EVAL_PAGE;
            }
            // -------------
            if ("a".equalsIgnoreCase(getType())) {
                tagWriter.startTag("a");
                writeDefaultAttributes(tagWriter);
                writeOptionalAttribute(tagWriter, ONSELECT_ATTRIBUTE, getOnselect());
                writeOptionalAttribute(tagWriter, ALT_ATTRIBUTE, getAlt());
                if (getHref() != null && getHref().length() > 0) {
                    tagWriter.writeAttribute("href", getHref());
                }
                pageContext.getOut().write(">");
                pageContext.getOut().write(getValue());
                pageContext.getOut().write("</a>");
            }
            else {
                tagWriter.startTag("input");
                tagWriter.writeAttribute("type", getType());
                writeDefaultAttributes(tagWriter);
                writeValue(tagWriter);
                // custom optional attributes
                writeOptionalAttribute(tagWriter, SIZE_ATTRIBUTE, getSize());
                writeOptionalAttribute(tagWriter, ALT_ATTRIBUTE, getAlt());
                writeOptionalAttribute(tagWriter, ONSELECT_ATTRIBUTE, getOnselect());
                tagWriter.endTag();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
        // return super.writeTagContent(tagWriter);
    }

    protected void writeValue(TagWriter tagWriter) throws JspException {
        if (getValue() == null || getValue().length() <= 0)
            tagWriter.writeAttribute("value", getDisplayString(getBoundValue(), getPropertyEditor()));
        else
            tagWriter.writeAttribute("value", getValue());
    }

    protected String getPropertyPath() throws JspException {
        return getPath();
    }

    protected String resolveCssClass() throws JspException {
        return ObjectUtils.getDisplayString(evaluate("cssClass", getCssClass()));
    }

    //
    public long getActId() {
        return actId;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public String getType() {
        if (type == null)
            return super.getType();
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
