package com.focustech.oss2008.web.tags;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-3 下午04:11:23
 */
public class ErrorsTag extends org.springframework.web.servlet.tags.form.ErrorsTag {
    private static final long serialVersionUID = -5345807821730493466L;
    /**
     * 需要顯示的錯誤信息代碼
     * <p>
     * 如果有多個，請用逗號分割，例如︰required,duplicated
     */
    private String displayErrorCode;
    private String[] errorCodesAry = null;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.tags.form.ErrorsTag#renderDefaultContent(org.springframework.web.servlet.tags
     * .form.TagWriter)
     */
    protected void renderDefaultContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag(getElement());
        writeDefaultAttributes(tagWriter);
        String delimiter = ObjectUtils.getDisplayString(evaluate("delimiter", getDelimiter()));
        String[] errorMessages = getBindStatus().getErrorMessages();
        String[] errorCodes = getBindStatus().getErrorCodes();
        initErrorCodesArray();
        for (int i = 0; i < errorCodes.length; i++) {
            if (shouldDisplayThisError(errorCodes[i])) {
                String errorMessage = errorMessages[i];
                if (i > 0) {
                    tagWriter.appendValue(delimiter);
                }
                tagWriter.appendValue(getDisplayString(errorMessage));
            }
        }
        tagWriter.endTag();
    }

    protected void initErrorCodesArray() {
        if (displayErrorCode != null && StringUtils.isNotEmpty(displayErrorCode)) {
            errorCodesAry = displayErrorCode.split(",");
        }
    }

    protected boolean shouldDisplayThisError(String errorCode) {
        if (errorCodesAry != null && errorCodesAry.length > 0) {
            for (String displayErrorCode : errorCodesAry) {
                if (displayErrorCode.equals(errorCode)) {
                    return true;
                }
            }
            return false;
        }
        else
            return true;
    }

    public String getDisplayErrorCode() {
        return displayErrorCode;
    }

    public void setDisplayErrorCode(String displayErrorCode) {
        this.displayErrorCode = displayErrorCode;
    }
}
