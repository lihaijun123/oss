package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * <li>OSS各系統中統一數字轉換器</li>
 *
 * @author taofucheng 2009-10-30 上午09:10:52
 */
@SuppressWarnings("unchecked")
public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;

    /**
     * Create a new CustomNumberEditor instance, using the default <code>valueOf</code> methods for parsing and
     * <code>toString</code> methods for rendering.
     * <p>
     * The "allowEmpty" parameter states if an empty String should be allowed for parsing, i.e. get interpreted as
     * <code>null</code> value. Else, an IllegalArgumentException gets thrown in that case.
     *
     * @param numberClass Number subclass to generate
     * @param allowEmpty if empty strings should be allowed
     * @throws IllegalArgumentException if an invalid numberClass has been specified
     * @see org.springframework.util.NumberUtils#parseNumber(String, Class)
     * @see Integer#valueOf
     * @see Integer#toString
     */
    public CustomNumberEditor(Class numberClass, boolean allowEmpty) throws IllegalArgumentException {
        this(numberClass, null, allowEmpty);
    }

    /**
     * Create a new CustomNumberEditor instance, using the given NumberFormat for parsing and rendering.
     * <p>
     * The allowEmpty parameter states if an empty String should be allowed for parsing, i.e. get interpreted as
     * <code>null</code> value. Else, an IllegalArgumentException gets thrown in that case.
     *
     * @param numberClass Number subclass to generate
     * @param numberFormat NumberFormat to use for parsing and rendering
     * @param allowEmpty if empty strings should be allowed
     * @throws IllegalArgumentException if an invalid numberClass has been specified
     * @see org.springframework.util.NumberUtils#parseNumber(String, Class, java.text.NumberFormat)
     * @see java.text.NumberFormat#parse
     * @see java.text.NumberFormat#format
     */
    public CustomNumberEditor(Class numberClass, NumberFormat numberFormat, boolean allowEmpty)
            throws IllegalArgumentException {
        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        }
        else if (this.numberFormat != null) {
            setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
        }
        else {
            setValue(parseNumber(text, this.numberClass));
        }
    }

    public String getAsText() {
        Object value = getValue();
        if (value == null) {
            return "";
        }
        if (this.numberFormat != null) {
            return this.numberFormat.format(value);
        }
        else {
            return value.toString();
        }
    }

    /**
     * 將文字轉換為對應的10進制數字。
     *
     * @param text 文本形式的數字
     * @param targetClass 指定的轉換類
     * @return 返回轉換後的結果
     */
    protected Number parseNumber(String text, Class targetClass) {
        Assert.notNull(text, "Text must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        String trimmed = text.trim();
        if (targetClass.equals(Byte.class)) {
            return Byte.valueOf(trimmed);
        }
        else if (targetClass.equals(Short.class)) {
            return Short.valueOf(trimmed);
        }
        else if (targetClass.equals(Integer.class)) {
            return Integer.valueOf(trimmed);
        }
        else if (targetClass.equals(Long.class)) {
            return Long.valueOf(trimmed);
        }
        else if (targetClass.equals(BigInteger.class)) {
            return BigInteger.valueOf(Long.valueOf(trimmed));
        }
        else if (targetClass.equals(Float.class)) {
            return Float.valueOf(trimmed);
        }
        else if (targetClass.equals(Double.class)) {
            return Double.valueOf(trimmed);
        }
        else if (targetClass.equals(BigDecimal.class) || targetClass.equals(Number.class)) {
            return new BigDecimal(trimmed);
        }
        else {
            throw new IllegalArgumentException("Cannot convert String [" + text + "] to target class ["
                    + targetClass.getName() + "]");
        }
    }
}
