package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.focustech.common.utils.DateUtils;
import com.focustech.oss2008.Constants;


/**
 * <li></li>
 *
 * @author yangpeng 2008-7-23 下午06:29:15
 */
public class DateTypeEditor extends PropertyEditorSupport {
    private final boolean allowEmpty;

    public DateTypeEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        }
        else if (text != null) {
            Date date = DateUtils.checkAndFormatString2Date(text);
            setValue(date);
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? DateUtils.formatDate(value, Constants.DATETIMEPATTERN_SEP) : "");
    }
}
