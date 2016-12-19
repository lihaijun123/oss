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
 * @author jibin
 */
public class RoleHtmlElementTag extends InputTag {
    private String tagName = "";
    private long actId = -1;
    private String value = "";
    private String href = "";
    private String content = "";

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
            tagWriter.startTag(getTagName());
            writeDefaultAttributes(tagWriter);
            writeOptionalAttribute(tagWriter, ONSELECT_ATTRIBUTE, getOnselect());
            writeOptionalAttribute(tagWriter, ALT_ATTRIBUTE, getAlt());
            pageContext.getOut().write(">");
            pageContext.getOut().write(getValue());
            if (getContent() != null && getContent().length() > 0) {
                pageContext.getOut().write(getContent());
            }
            pageContext.getOut().write("</" + getTagName() + ">");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
        // return super.writeTagContent(tagWriter);
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
