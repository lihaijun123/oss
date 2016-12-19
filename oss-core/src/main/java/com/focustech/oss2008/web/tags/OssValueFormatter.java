package com.focustech.oss2008.web.tags;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.util.ObjectUtils;
import org.springframework.web.util.HtmlUtils;

import com.focustech.common.utils.DateUtils;
import com.focustech.oss2008.utils.DateTool;


/**
 * <li></li>
 *
 * @author yangpeng 2008-7-8 上午11:59:08
 */
public class OssValueFormatter {
    /**
     * Build the display value of the supplied <code>Object</code>, HTML escaped as required. This version is
     * <strong>not</strong> {@link PropertyEditor}-aware.
     *
     * @see #getDisplayString(Object, java.beans.PropertyEditor, boolean)
     */
    public static String getDisplayString(Object value, boolean htmlEscape) {
        String displayValue = ObjectUtils.getDisplayString(value);
        return (htmlEscape ? HtmlUtils.htmlEscape(displayValue) : displayValue);
    }

    /**
     * Build the display value of the supplied <code>Object</code>, HTML escaped as required. If the supplied value is
     * not a {@link String} and the supplied {@link PropertyEditor} is not null then the {@link PropertyEditor} is used
     * to obtain the display value.
     *
     * @see #getDisplayString(Object, boolean)
     */
    public static String getDisplayString(Object value, PropertyEditor propertyEditor, boolean htmlEscape) {
        if (propertyEditor != null && !(value instanceof String)) {
            try {
                propertyEditor.setValue(value);
                return getDisplayString(propertyEditor.getAsText(), htmlEscape);
            }
            catch (Throwable ex) {
                // The PropertyEditor might not support this value... pass through.
                return getDisplayString(value, htmlEscape);
            }
        }
        else {
            return getDisplayString(value, htmlEscape);
        }
    }

    /**
     * 得到時間的格式化輸出
     *
     * @param value 時間值
     * @param propertyEditor 值編輯器
     * @param htmlEscape
     * @param format 時間格式
     * @return
     */
    public static String getDateDisplayString(Object value, PropertyEditor propertyEditor, boolean htmlEscape,
            String format) {
        if (value != null) {
            if (value instanceof Date)
                return DateUtils.formatDate((Date) value, format);
            else {
                try {
                    Date time = DateTool.checkAndFormatStr2Date(String.valueOf(value));
                    return DateTool.formatDate(time, format);
                }
                catch (Exception e) {
                    return getDisplayString(value, htmlEscape);
                }
            }
        }
        else {
            return getDisplayString(value, htmlEscape);
        }
    }

    /**
     * 根據時間格式，得到時間的格式化輸出
     *
     * @param value
     * @param htmlEscape
     * @param format
     * @return
     */
    public static String getDateDisplayString(Object value, boolean htmlEscape, String format) {
        String displayValue = "";
        if (value instanceof Date)
            displayValue = DateUtils.formatDate((Date) value, format);
        else
            displayValue = ObjectUtils.getDisplayString(value);
        return (htmlEscape ? HtmlUtils.htmlEscape(displayValue) : displayValue);
    }
}
