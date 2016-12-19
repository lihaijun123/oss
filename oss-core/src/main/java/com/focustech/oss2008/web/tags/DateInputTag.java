package com.focustech.oss2008.web.tags;

import java.beans.PropertyEditor;

import org.springframework.web.servlet.tags.form.InputTag;

import com.focustech.oss2008.Constants;

/**
 * <li>時間輸出tag</li>
 *
 * @author yangpeng 2008-7-8 上午11:46:57
 */
public class DateInputTag extends InputTag {
    private static final long serialVersionUID = 2142260710854972885L;
    private String format = Constants.DATETIMEPATTERN_SEP;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    protected String getDisplayString(Object value, PropertyEditor propertyEditor) {
        return OssValueFormatter.getDateDisplayString(value, propertyEditor, isHtmlEscape(), getFormat());
    }
}
