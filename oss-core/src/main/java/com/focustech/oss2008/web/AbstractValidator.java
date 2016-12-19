package com.focustech.oss2008.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.ExceptionConstants;
import com.focustech.oss2008.ValidateConstants;
import com.focustech.oss2008.exception.mvc.ValidationException;
import com.focustech.oss2008.utils.DateTool;
import com.focustech.oss2008.utils.GenericsUtils;


/**
 * <li></li>
 *
 * @author yangpeng 2008-4-23 下午02:37:07
 */
public abstract class AbstractValidator {
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_OSS);
    /** 必填項驗證碼 */
    public static final String ERROR_CODE_REQUIRED = "required";
    public static final String ERROR_CODE_DUPLICATED = "duplicated";
    public static final String ERROR_CODE_FORMAT_ERROR = "format_error";
    /** 電話或傳真驗證 **/
    // 國際區號
    public static final Pattern P_NALTIONL_NUM = Pattern.compile("[0-9]{1,4}");
    // 國內區號
    public static final Pattern P_COUNTRY_NUM = Pattern.compile("[0-9]{1,4}");
    // 電話或傳真
    public static final Pattern P_TELEPHONE_NUM = Pattern.compile("[0-9]{7,9}");
    // 手機號
    public static final Pattern P_MOBILE_NUM = Pattern.compile("[0-9]{11}");
    // 驗證手機號碼的區號
    public static final Pattern P_MOBILE_COUNTRY_NUM = Pattern.compile("[0]{0,4}");
    // 分機號
    public static final Pattern P_EXT_NUM = Pattern.compile("[0-9]{0,6}");
    // URL匹配
    public static final Pattern P_URL =
            Pattern.compile("^(http://)?(www\\.)?[-\\w]+(\\.[-/\\w]+){1,}", Pattern.CASE_INSENSITIVE);
    // 數字
    public static final Pattern P_EN_NUM = Pattern.compile("[0-9]+");
    // 中文字和中文括號
    public static final Pattern P_CHINESE = Pattern.compile("[\\u4E00-\\u9FA5|（|）]+");
    // 分隔符
    public static final Pattern P_REG = Pattern.compile("(,|-)");
    private Object form;
    private Errors error;
    private static List<String> needHiddenErrorCode = new ArrayList<String>();
    static {
        needHiddenErrorCode.add(TypeMismatchException.ERROR_CODE);
    }

    /**
     * @param form
     * @param error
     */
    public AbstractValidator(Object form, Errors error) {
        Assert.notNull(error);
        Assert.notNull(form);
        this.form = form;
        this.error = error;
    }

    /**
     * 檢查是否為空字符串
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkEmputyString(String field, String errorMessage) {
        Object value = getValue(field);
        if ((null == value) || !(value instanceof String) || StringUtils.isEmpty((String) value)) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
        }
    }

    /**
     * 檢查是否為空字符串，如果為空，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkEmputyString(String field) {
        checkEmputyString(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
    }

    /**
     * 檢查是否為null
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkEmputyObj(String field, String errorMessage) {
        Object value = getValue(field);
        if (null == value) {
            FieldError fe = error.getFieldError(field);
            if (fe == null) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
            else {
                //
            }
        }
    }

    /**
     * 檢查是否為null，如果為空，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkEmputyObj(String field) {
        checkEmputyObj(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
    }

    /**
     * 功能︰檢查數字小數點後不能大于3位，如大于，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkDecimalLength(String field) {
        checkDecimalLength(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_DECIMALLENGTH)));
    }

    /**
     * 功能︰檢查數字小數點後不能大于3位，如大于，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws errorMessage 驗證異常
     */
    public void checkDecimalLength(String field, String errorMessage) {
        Object value = getValue(field);
        if (null != value) {
            // if (value instanceof Number)
            // {
            String s = (String) value;
            s = s.substring(s.indexOf(".") + 1);
            if (s.length() > 3) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                // }
            }
        }
    }

    /**
     * 檢查 時間 是否在當前時間6月內
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkCompareDateMonth(String field, String errorMessage) {
        Object value = getValue(field);
        if (null == value) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, "不能為空");
        }
        else {
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.add(Calendar.MONTH, 6);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) value);
            if (calendarNow.before(calendar) || calendar.getTime().before(new Date())) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }

    /**
     * 檢查newsletter与productalert產品預定時間的合法性
     *
     * @param field1
     * @param field2
     */
    /*
    public void checkDateValidate(String field1, String field2) {
        String time = (String) getValue(field1);
        String issue = (String) getValue(field2);
        CrmResourceBooking resourceBooking = (CrmResourceBooking) form;
        Calendar calendar = new GregorianCalendar();
        Date nowTime = new Date();
        Date preDate;
        if ("".equals(time)) {
            error.rejectValue(field1, ERROR_CODE_REQUIRED, "不能為空！");
        }
        else if ("".equals(issue)) {
            error.rejectValue(field2, ERROR_CODE_REQUIRED, "不能為空！");
        }
        else {
            Date endDate = DateTool.getDate(time, "yyyyMM");
            int day = DateTool.getFirstFriOfMonth(endDate);
            if (issue.equals(OrderConstant.FIRST_ISSUE)) {
                if (Constants.SITE_TYPE_MIC_EN.equals(resourceBooking.getProductType().getWebsite())) {
                    preDate = DateTool.getDateOfDay(endDate, day - 6);
                }
                else {
                    preDate = DateTool.getDateOfDay(endDate, day - 1);
                }
                if (nowTime.after(preDate)) {
                    error.rejectValue(field1, ERROR_CODE_REQUIRED, "第一期預定時間必須為當月的第一個星期五或者第二個星期五一星期之前");
                }
            }
            else if (issue.equals(OrderConstant.SECOND_ISSUE)) {
                if (Constants.SITE_TYPE_MIC_EN.equals(resourceBooking.getProductType().getWebsite())) {
                    preDate = DateTool.getDateOfDay(endDate, day + 6);
                }
                else {
                    preDate = DateTool.getDateOfDay(endDate, day + 13);
                }
                if (nowTime.after(preDate)) {
                    error.rejectValue(field1, ERROR_CODE_REQUIRED, "第二期預定時間必須為當月的第三個星期五或者第四個星期五一星期之前");
                }
            }
            else {
                endDate = DateTool.getDate(time, "yyyyMMdd");
                calendar.setTime(endDate);
                if (calendar.get(Calendar.DAY_OF_WEEK) != 4) {
                    error.rejectValue(field1, ERROR_CODE_REQUIRED, "所選時間應該為星期三！！");
                }
                else {
                    preDate = DateTool.getDateOfDay(endDate, -7);
                    if (nowTime.after(preDate)) {
                        error.rejectValue(field1, ERROR_CODE_REQUIRED, "預定時間必須為預定星期三時間的一星期之前");
                    }
                }
            }
        }

    }
	*/
    /**
     * 檢查 時間 是否在當前時間六月內
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkCompareDateMonth(String field) {
        checkCompareDateMonth(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DATE_MUST_INNER_MONTH)));
    }

    /**
     * 功能︰檢查是否為數字(不能為空)
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsNumeric(String field, String errorMessage) {
        checkIsNumeric(field, true, errorMessage);
    }

    /**
     * 功能︰檢查是否為數字
     *
     * @param field 需要驗證的字段
     * @param isNec 是否不能為空 TRUE:不能為空 FALSE:可為空
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsNumeric(String field, boolean isNec, String errorMessage) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if (null == value) {
                if (isNec) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
                }
            }
            else {
                if (!(value instanceof Number)) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                }
                // boolean isNum = isNumeric(value + "");
                // if (!isNum)
                // {
                // error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                // }
            }
        }
    }

    /**
     * 功能︰檢查是否為數字(不能為空)，如果不是，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsNumeric(String field) {
        checkIsNumeric(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISNUMERIC)));
    }

    /**
     * 功能︰檢查是否為數字，如果不是，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param isNec 是否不能為空 TRUE:不能為空 FALSE:可為空
     * @throws ValidationException 驗證異常
     */
    public void checkIsNumeric(String field, boolean isNec) {
        checkIsNumeric(field, isNec, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISNUMERIC)));
    }

    /**
     * 功能︰驗證密碼 (規則︰1、長度不小于6位,不大于20位 2、不能純是數字或字母，必須是兩者都有 3、不能是相同的數字、字母 4、不能是連續的數字、字母)
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkPassword(String field) {
        Object value = getValue(field);
        if (null == value) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                    MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
        }
        else {
            String str = value + "";
            if ((str.length() <= 6) || (str.length() > 20)) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.PASSWORD_ERROR_LENGTH)));
            }
            else if (!(isContainNum(str) && isContainChar(str))) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.PASSWORD_ERROR_ISHASNUMANDCHAR)));
            }
            else {
            }
        }
    }

    /**
     * 功能︰檢查str1,str2是否相同
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsSameness(String field, String str1, String str2) {
        if (!str1.equals(str2)) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                    MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_NOTSAMENESS)));
        }
    }

    /**
     * 功能︰檢查str1,str2是否相同
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsSameness(String field, String str1, String str2, String errorMessage) {
        if (!str1.equals(str2)) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
        }
    }

    /**
     * 功能︰檢查是否為非負整數(不能為空)
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIntegerWithoutMinus(String field) {
        checkIntegerWithoutMinus(field, false);
    }

    /**
     * 功能︰檢查是否為非負整數(能為空)
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIntegerWithoutMinusConsEmpty(String field) {
        checkIntegerWithoutMinus(field, true);
    }

    /**
     * 功能︰檢查是否為非負整數
     *
     * @param field 需要驗證的字段
     * @param isNull 是否為空
     * @throws ValidationException 驗證異常
     */
    public void checkIntegerWithoutMinus(String field, boolean isNull) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if (null != value) {
                try {
                    long num = Long.parseLong(value + "");
                    if (num < 0) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISMINUSINTEGER)));
                    }
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISMINUSINTEGER)));
                }
            }
            else {
                if (!isNull) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
                }
            }
        }
    }

    /**
     * 功能︰檢查是否為非負整數
     *
     * @param field 需要驗證的字段
     * @param isNull 是否為空
     * @throws ValidationException 驗證異常
     */
    public void checkIntegerCanEmpty(String field) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if ((null != value) && !"".equals(value)) {
                try {
                    long num = Long.parseLong(value + "");
                    if (num < 0) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISMINUSINTEGER)));
                    }
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISMINUSINTEGER)));
                }
            }
        }
    }

    /**
     * 功能︰檢查是否為整數
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsInteger(String field, String errorMessage) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if (null != value) {
                try {
                    Long.parseLong(value + "");
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                }
            }
        }
    }

    /**
     * 功能︰檢查是否為整數，如果不是，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsInteger(String field) {
        checkIsInteger(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISINTEGER)));
    }

    /**
     * 功能︰檢查是否為double
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsDouble(String field, String errorMessage) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if (null != value) {
                try {
                    Double.parseDouble(value + "");
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                }
            }
            else {
                if (error.hasFieldErrors(field)) {
                }
                else {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
                }
            }
        }
    }

    /**
     * 功能︰檢查是否為double(不能為空)，如果不是，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsDouble(String field) {
        checkIsDouble(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDOUBLE)));
    }

    /**
     * 功能︰檢查是系統中 金額（默認︰不能為空，不可為負）
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkMoney(String field) {
        checkMoney(field, true, true);
    }

    /**
     * 功能︰檢查是系統中 金額
     *
     * @param field 需要驗證的字段
     * @param isNull 是否為空 TRUE:不能為空 FALSE:可為空
     * @param isMinus 是否為負 TRUE:不能為負 FALSE:可為負
     * @throws ValidationException 驗證異常
     */
    public void checkMoney(String field, boolean isNull, boolean isMinus) {
        checkMoney(field, isNull, isMinus, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISNUMERIC)));
    }

    /**
     * 功能︰檢查是系統中 金額
     *
     * @param field 需要驗證的字段
     * @param isNull 是否為空 TRUE:不能為空 FALSE:可為空
     * @param isMinus 是否為負 TRUE:不能為負 FALSE:可為負
     * @throws ValidationException 驗證異常
     */
    public void checkMoney(String field, boolean isNull, boolean isMinus, String errorMessage) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if ((null != value) && !"".equals(value)) {
                try {
                    double dou = Double.parseDouble(value + "");
                    if (isMinus && (dou < 0)) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISMINUS)));
                    }
                    //
                    if (dou >= 1E8) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, "小數點前位數不能大于8位！");
                    }
                    //
                    String s = String.valueOf(value);
                    String[] money = s.split("\\.");
                    if ((s.indexOf("E") < 0) && (money != null) && (money.length > 1)) {
                        if (money[1].length() > 2) {
                            error.rejectValue(field, ERROR_CODE_REQUIRED, "小數點後位數不能大于2位！");
                        }
                    }
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                }
            }
            else {
                if (error.hasFieldErrors(field)) {
                }
                else {
                    if (isNull) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
                    }
                }
            }
        }
    }

    /**
     * 功能︰檢查是否為Float
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsFloat(String field, String errorMessage) {
        if (!hasUnOperateError(field)) {
            Object value = getValue(field);
            if (null != value) {
                try {
                    Float.parseFloat(value + "");
                }
                catch (NumberFormatException e) {
                    error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
                }
            }
            else {
                error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
            }
        }
    }

    /**
     * 功能︰檢查是否為Float，如果不是，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsFloat(String field) {
        checkIsFloat(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISFLOAT)));
    }

    /**
     * 功能︰檢查是否含有漢字，如有，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsContainChinese(String field) {
        checkIsContainChinese(field, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISCONTAINCHINESE)));
    }

    /**
     * 功能︰檢查是否含有漢字
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsContainChinese(String field, String errorMessage) {
        Object value = getValue(field);
        if (null != value) {
            if (isContainChinese(value + "")) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }

    /**
     * 功能︰檢查是否含有兩個或兩個以上的漢字（用于客戶中文名稱驗證）
     *
     * @param field 需要驗證的字段
     */
    public void checkIsMoreThanTwoCNCharactor(String field) {
        checkIsMoreThanTwoCNCharactor(field, getFieldDisplay(field).concat(
                MessageUtils.getExceptionValue(ExceptionConstants.DEFAULT_ERROR_LESSTHAN2CHARS)));
    }

    /**
     * 功能︰檢查是否含有兩個或兩個以上的漢字（用于客戶中文名稱驗證）
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsMoreThanTwoCNCharactor(String field, String errorMessage) {
        Object value = getValue(field);
        if (null != value) {
            if (!isMoreThanTwoCNCharactor(value + "")) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }

    /**
     * 功能︰檢查是否含有非法字符,默認非法字符集 '<>\\#%&"
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsConsDefaultChar(String field, String errorMessage) {
        Object value = getValue(field);
        if (null != value) {
            boolean isInCoret =
                    isInCorrectChar(value + "", MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_INCORRECTCHAR));
            if (isInCoret) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }

    /**
     * 功能︰檢查是否含有非法字符
     *
     * @param field 需要驗證的字段
     * @param inCoretStr 不能含有的非法字符集，如 '<>\\#%&"
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsContainInCoretChar(String field, String inCoretStr, String errorMessage) {
        Object value = getValue(field);
        if (null != value) {
            boolean isInCoret = isInCorrectChar(value + "", inCoretStr);
            if (isInCoret) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }

    /**
     * 功能︰檢查是否含有非法字符'<>\\#%&" ,如有，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsConsDefaultChar(String field) {
        checkIsContainInCoretChar(field, MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_INCORRECTCHAR),
                getFieldDisplay(field).concat(ValidateConstants.DEFAULT_ERROR_ISINCORRECT));
    }

    /**
     * 功能︰檢查是否含有非法字符，如有，返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param inCoretStr 不能含有的非法字符集，如 '<>\\#%&"
     * @throws ValidationException 驗證異常
     */
    public void checkIsContainInCoretChar(String field, String inCoretStr) {
        checkIsContainInCoretChar(field, inCoretStr, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISINCORRECT)));
    }

    /**
     * 檢查是email格式是否正確
     *
     * @param field 需要驗證的字段
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmail(String field, String errorMessage) {
        checkIsEmail(field, true, errorMessage);
    }

    /**
     * 檢查是email格式是否正確
     *
     * @param field 需要驗證的字段
     * @param isNec 該字段是否必填
     * @param regex 分隔符
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmail(String field, boolean isNec, String regex, String errorMessage) {
        Object value = getValue(field);
        if ((null == value) || !(value instanceof String) || StringUtils.isEmpty((String) value)) {
            if (isNec) {
                error.rejectValue(field, ERROR_CODE_REQUIRED, getFieldDisplay(field).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
            }
        }
        else {
            String[] emails = Pattern.compile("[,|，]").split(String.valueOf(value));
            for (int i = 0; i < emails.length; i++) {
                if (StringUtils.isNotEmpty(emails[i])) {
                    // 去除前後空格的影響
                    emails[i] = StringUtils.trim(emails[i]);
                    String IdxMsg = i > 0 ? ("第" + (i + 1) + "個") : "";
                    boolean isEmail = isEmail(emails[i] + "", regex);
                    if (!isEmail) {
                        error.rejectValue(field, ERROR_CODE_REQUIRED, IdxMsg + errorMessage);
                    }
                }
            }
        }
        if (!error.hasErrors()) {
            setValue(field, String.valueOf(value).toLowerCase());
        }
    }

    /**
     * 檢查是email格式是否正確
     *
     * @param field 需要驗證的字段
     * @param isNec 該字段是否必填
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmail(String field, boolean isNec, String errorMessage) {
        checkIsEmail(field, isNec, null, errorMessage);
    }

    /**
     * 檢查email格式是否正確，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmail(String field) {
        checkIsEmail(field, true);
    }

    /**
     * 檢查email格式是否正確，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param isNec 該字段是否必填
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmail(String field, boolean isNec) {
        checkIsEmail(field, isNec, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISEMAIL)));
    }

    /**
     * 檢查email格式是否正確，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param isNec 該字段是否必填
     * @param regex 分隔符
     * @throws ValidationException 驗證異常
     */
    public void checkIsEmailStr(String field, boolean isNec, String regex) {
        checkIsEmail(field, isNec, regex, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISEMAIL)));
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param minLength 最小長度
     * @param maxLength 最大長度
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkStringLength(String field, int minLength, int maxLength, String errorMessage) {
        Object value = getValue(field);
        if ((null == value) || !(value instanceof String) || (minLength > StringUtils.length((String) value))
                || (maxLength < StringUtils.length((String) value))) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
        }
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param minLength 最小長度
     * @param maxLength 最大長度
     * @throws ValidationException 驗證異常
     */
    public void checkStringLength(String field, int minLength, int maxLength) {
        checkStringLength(field, minLength, maxLength, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_STRINGLENGTH)));
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param minLength 最小長度
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkStringLengthMax(String field, int maxLength, String errorMessage) {
        checkStringLength(field, 0, maxLength, errorMessage);
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param maxLength 最大長度
     * @throws ValidationException 驗證異常
     */
    public void checkStringLengthMax(String field, int maxLength) {
        checkStringLength(field, 0, maxLength, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_STRINGLENGTH)));
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param minLength 最小長度
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkStringLengthMin(String field, int minLength, String errorMessage) {
        Object value = getValue(field);
        if ((null == value) || !(value instanceof String) || (minLength > StringUtils.length((String) value))) {
            error.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
        }
    }

    /**
     * 檢查字符串長度是否在範圍內，如果錯誤,返回默認報錯信息
     *
     * @param field 需要驗證的字段
     * @param minLength 最小長度
     * @throws ValidationException 驗證異常
     */
    public void checkStringLengthMin(String field, int minLength) {
        checkStringLengthMin(field, minLength, getFieldDisplay(field).concat(
                MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_STRINGLENGTH)));
    }

    /**
     * 預先檢查驗證字段的合法性
     */
    protected void preCheck(String field) {
        if (StringUtils.isEmpty(field)) {
            throw new IllegalArgumentException(MessageUtils
                    .getValidatorValue(ExceptionConstants.FIELD_ISNULL_EXCEPTION));
        }
    }

    /**
     * 取當前對象給定字段的值
     *
     * @throws ValidationException 驗證異常
     */
    protected Object getValue(String field) {
        preCheck(field);
        try {
            String[] fields = field.split("\\.");
            Object obj = form;
            for (int i = 0; (fields != null) && (i < fields.length - 1); i++) {
                String method = "get".concat(fields[i].substring(0, 1).toUpperCase().concat(fields[i].substring(1)));
                obj = obj.getClass().getMethod(method).invoke(obj);
            }
            if ((fields != null) && (fields.length > 0)) {
                field = fields[fields.length - 1];
            }
            field = "get".concat(field.substring(0, 1).toUpperCase().concat(field.substring(1)));
            return obj.getClass().getMethod(field).invoke(obj);
        }
        catch (IllegalArgumentException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (SecurityException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (IllegalAccessException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (InvocationTargetException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (NoSuchMethodException e) {
            log.error(e);
            throw new ValidationException();
        }
    }

    /**
     * 給當前對象指定的屬性設置值
     *
     * @param field
     */
    public void setValue(String field, Object value) {
        preCheck(field);
        try {
            String[] fields = field.split("\\.");
            Object obj = form;
            for (int i = 0; (fields != null) && (i < fields.length - 1); i++) {
                String method = "get".concat(fields[i].substring(0, 1).toUpperCase().concat(fields[i].substring(1)));
                obj = obj.getClass().getMethod(method).invoke(obj);
            }
            if ((fields != null) && (fields.length > 0)) {
                field = fields[fields.length - 1];
            }
            field = "set".concat(field.substring(0, 1).toUpperCase().concat(field.substring(1)));
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(field)) {
                    method.invoke(obj, value);
                    break;
                }
            }
            // obj.getClass().getMethod(field).invoke(obj, value);
        }
        catch (IllegalArgumentException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (SecurityException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (IllegalAccessException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (InvocationTargetException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (NoSuchMethodException e) {
            log.error(e);
            throw new ValidationException();
        }
    }

    /**
     * 取給定字段的默認顯示名稱，如果沒有，則返回空字符串
     *
     * @throws ValidationException 驗證異常
     */
    protected String getFieldDisplay(String field) {
        try {
            return GenericsUtils.getFieldDisplayAnnotation(form, field);
        }
        catch (IllegalArgumentException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (SecurityException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (IllegalAccessException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (InvocationTargetException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (NoSuchMethodException e) {
            log.error(e);
            throw new ValidationException();
        }
        catch (NoSuchFieldException e) {
            log.error(e);
            throw new ValidationException();
        }
    }

    /**
     * 功能︰判斷字符串是否為數字
     *
     * @param str
     * @return
     */
    protected boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(-{0,1}|\\+{0,1})[0-9]+(\\.{0,1}[0-9]+)*$");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 功能︰判斷字符串是否為Email
     *
     * @param str
     * @param regex 分隔符
     * @return
     */
    private boolean isEmail(String str, String regex) {
        if (regex != null) {
            String[] strEmail = str.split(regex);
            for (String ema : strEmail) {
                if (StringUtils.isNotEmpty(ema) && !isEmail(ema)) {
                    return false;
                }
            }
        }
        else {
            return isEmail(str);
        }
        return true;
    }

    /**
     * 功能︰判斷字符串是否為Email
     *
     * @param str
     * @return
     */
    private boolean isEmail(String str) {
        String check =
                "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2,10})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        if (isMatched) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 功能︰判斷字符串是否含有漢字
     *
     * @param str
     * @return
     */
    private boolean isContainChinese(String str) {
        String check = "[\u4E00-\u9FA5]+"; // gb2312
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.find();
    }

    /**
     * 功能︰判斷字符串是否含有兩個或兩個以上的漢字（用于客戶中文名稱的驗證）
     *
     * @param str
     * @return
     */
    public static boolean isMoreThanTwoCNCharactor(String str) {
        String check = "[\u4E00-\u9FA5].*[\u4E00-\u9FA5]"; // gb2312
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.find();
    }

    /**
     * 功能︰判斷字符串是否含有數字0123456789
     *
     * @param str
     * @return
     */
    private boolean isContainNum(String str) {
        String check = "[0-9]+";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.find();
    }

    /**
     * 功能︰判斷字符串是否含有字母及_
     *
     * @param str
     * @return
     */
    private boolean isContainChar(String str) {
        String check = "[a-z_A-Z]+";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.find();
    }

    /**
     * 功能︰判斷字符串是否含有非法字符
     *
     * @param str
     * @param inCoretStr 非法字符集，如 "'<>\\#%&" + '"'
     * @return
     */
    private boolean isInCorrectChar(String str, String inCoretStr) {
        inCoretStr = (null == inCoretStr) ? "" : inCoretStr;
        String strTemp = inCoretStr;
        if (str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (strTemp.indexOf(str.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比較日期大小，返回 是否符合條件
     *
     * @param firstDate 開始時間
     * @param nextDate 結束時間
     * @param compare 關系 例如>,<,>=,=,<=
     */
    private boolean compareDate(Date firstDate, Date nextDate, String compare) {
        boolean bool = true;
        int i = firstDate.compareTo(nextDate);
        if (i > 0) {
            if (compare.trim().equals("<") || compare.trim().equals("<=")) {
                bool = false;
            }
        }
        else if (i < 0) {
            if (compare.trim().equals(">") || compare.trim().equals(">=")) {
                bool = false;
            }
        }
        else {
            if (compare.trim().equals(">") || compare.trim().equals("<")) {
                bool = false;
            }
        }
        return bool;
    }

    /**
     * 比較日期大小，返回錯誤信息
     *
     * @param field1 需要驗證的字段
     * @param field2 需要驗證的字段
     * @param compare 關系 例如>,<,>=,=,<=
     * @param errorMessage 錯誤信息
     * @param beNull true︰可以為空，false︰做空檢查，如果為空則給不做日期檢查
     * @throws ValidationException 驗證異常
     */
    public void checkCompareDate(String field1, String field2, String compare, String errorMessage, boolean beNull) {
        if (beNull == false) {
            checkEmputyObj(field1);
            checkEmputyObj(field2);
        }
        if ((error.getFieldError(field1) == null) && (error.getFieldError(field2) == null)) {
            checkCompareDate(field1, field2, compare, errorMessage);
        }
    }

    /**
     * 比較日期大小，返回錯誤信息
     *
     * @param field1 需要驗證的字段
     * @param field2 需要驗證的字段
     * @param compare 關系 例如>,<,>=,=,<=
     * @param errorMessage 錯誤信息
     * @throws ValidationException 驗證異常
     */
    public void checkCompareDate(String field1, String field2, String compare, String errorMessage) {
        Object value1 = getValue(field1);
        Object value2 = getValue(field2);
        //
        if (value1 instanceof String) {
            try {
                value1 = DateTool.checkAndFormatStr2Date(String.valueOf(value1));
            }
            catch (Exception e) {
                log.error("", e);
                error.rejectValue(field1, ERROR_CODE_FORMAT_ERROR, getFieldDisplay(field1).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDATE)));
            }
        }
        if (value2 instanceof String) {
            try {
                value2 = DateTool.checkAndFormatStr2Date(String.valueOf(value2));
            }
            catch (Exception e) {
                log.error("", e);
                error.rejectValue(field2, ERROR_CODE_FORMAT_ERROR, getFieldDisplay(field2).concat(
                        MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDATE)));
            }
        }
        //
        if ((null == value1) || !(value1 instanceof Date)) {
            error.rejectValue(field1, ERROR_CODE_REQUIRED, getFieldDisplay(field1).concat(
                    MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
        }
        if ((null == value2) || !(value2 instanceof Date)) {
            error.rejectValue(field2, ERROR_CODE_REQUIRED, getFieldDisplay(field2).concat(
                    MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_MESSAGE_REQUIRED)));
        }
        boolean bool = compareDate((Date) value1, (Date) value2, compare);
        if (!bool) {
            error.rejectValue(field2, ERROR_CODE_REQUIRED, errorMessage);
        }
    }

    /**
     * 比較日期大小，返回默認報錯信息
     *
     * @param field1 需要驗證的字段
     * @param field2 需要驗證的字段
     * @param compare 關系 例如>,<,>=,=,<=
     * @throws ValidationException 驗證異常
     */
    public void checkCompareDate(String field1, String field2, String compare) {
        String str = "";
        if (compare != null) {
            if (compare.trim().equals(">")) {
                str = ValidateConstants.DEFAULT_MUST_BIGGER;
            }
            else if (compare.trim().equals(">=")) {
                str = ValidateConstants.DEFAULT_CANNOT_LESS;
            }
            else if (compare.trim().equals("<")) {
                str = ValidateConstants.DEFAULT_MUST_LESS;
            }
            else if (compare.trim().equals("<=")) {
                str = ValidateConstants.DEFAULT_CANNOT_BIGGER;
            }
            else if (compare.trim().equals("=")) {
                str = ValidateConstants.DEFAULT_MUST_EQUAL;
            }
            else {
            }
            checkCompareDate(field1, field2, compare, getFieldDisplay(field1).concat(str).concat(
                    getFieldDisplay(field2)));
        }
    }

    /**
     * <pre>
     * 檢查當前字段是否有類型轉換上的錯誤
     * 注:這個方法只適合于要做類型轉換的字段上，如int，double，long，date....
     * </pre>
     *
     * @param fieldName 要檢查的字段
     * @return true︰有，false沒有
     */
    @SuppressWarnings("unchecked")
    protected boolean hasUnOperateError(String fieldName) {
        if (error.hasFieldErrors(fieldName)) {
            for (FieldError fieldError : (List<FieldError>) error.getFieldErrors(fieldName)) {
                if (needHiddenErrorCode.contains(fieldError.getCode())) {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * 檢查當前檢查字段是否被檢查出有其它錯誤
     *
     * @param fieldName 檢查字段
     * @return true:有，false︰沒有
     */
    @SuppressWarnings("unchecked")
    protected boolean hasError(String fieldName) {
        return error.hasFieldErrors(fieldName);
    }

    /**
     * 檢查是否為中文字符且不包含數字
     *
     * @param text
     * @return
     */
    protected boolean isCN(String text) {
        int txtLength = text.length();
        int bLength = text.getBytes().length;
        if (txtLength * 2 == bLength || txtLength * 3 == bLength) {
            Matcher m = P_CHINESE.matcher(text);
            if (m.matches()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 檢查是否為英文字符且不包含數字
     *
     * @param text
     * @return
     */
    protected boolean isEn(String text) {
        int txtLength = text.length();
        int bLength = text.getBytes().length;
        if (txtLength == bLength) {
            Matcher m = P_EN_NUM.matcher(text);
            int numLen = 0;
            while (m.find()) {
                numLen += m.end() - m.start();
            }
            if (numLen > 10) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // @SuppressWarnings("unchecked")
    // protected boolean getErrorFromResult(String fieldName, String errorCode, String errorMessage)
    // {
    // if (error.hasFieldErrors(fieldName))
    // {
    // for (FieldError fieldError : (List<FieldError>) error.getFieldErrors(fieldName))
    // {
    // if (needHiddenErrorCode.contains(fieldError.getCode()))
    // {
    // error.rejectValue(fieldName, ERROR_CODE_FORMAT_ERROR, fieldError.getArguments(), errorMessage);
    // reConstructResult();
    // return true;
    // }
    // }
    // }
    // return false;
    // }
    // @SuppressWarnings("unchecked")
    // public void reConstructResult()
    // {
    // BeanPropertyBindingResult oldError = null;
    // if (error instanceof BeanPropertyBindingResult)
    // {
    // oldError = (BeanPropertyBindingResult) error;
    // }
    // else
    // {
    // throw new IllegalArgumentException("could not reconstruct error.target error type is:" + error.getClass());
    // }
    // BeanPropertyBindingResult newErrors = new BeanPropertyBindingResult(oldError.getTarget(), oldError
    // .getObjectName());
    // for (Iterator iterator = error.getAllErrors().iterator(); iterator.hasNext();)
    // {
    // FieldError fieldError = (FieldError) iterator.next();
    // if (!needHiddenErrorCode.contains(fieldError.getCode()))
    // {
    // newErrors.addError(fieldError);
    // }
    // }
    // error = newErrors;
    // }
    /**
     * 功能︰判斷時間是否正確格式
     *
     * @param field
     * @param format 希望輸入的日期格式 如"yyyy-MM-dd"，如果null則不做等值檢查
     */
    public void checkDate(String field, String format) {
        String value = String.valueOf(getValue(field));
        try {
            Date date = DateTool.checkAndFormatStr2Date(value);
            if (format != null) {
                String newValue = DateTool.formatDate(date, format);
                if (!newValue.equals(value)) {
                    error.rejectValue(field, ERROR_CODE_FORMAT_ERROR, getFieldDisplay(field).concat(
                            MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDATE)));
                }
            }
        }
        catch (Exception e) {
            log.error("", e);
            error.rejectValue(field, ERROR_CODE_FORMAT_ERROR, getFieldDisplay(field).concat(
                    MessageUtils.getValidatorValue(ValidateConstants.DEFAULT_ERROR_ISDATE)));
        }
    }

    /**
     * 檢查電話格式是否正確
     */
    /*
    protected boolean valid(String name, CrmAccountTelephone tel) {
        Matcher m;
        Matcher m2;
        if ("".equals(tel.getTelephone())) {
            return true;
        }
        m = P_NALTIONL_NUM.matcher(tel.getNationalNo());
        if (!m.matches()) {
            error.rejectValue(name, ERROR_CODE_REQUIRED, "國家區號必須為數字!");
            return false;
        }
        m = P_COUNTRY_NUM.matcher(tel.getCountryNo());
        if (!m.matches()) {
            error.rejectValue(name, ERROR_CODE_REQUIRED, "國內區號必須為1~4位數字!");
            return false;
        }
        m = P_MOBILE_NUM.matcher(tel.getTelephone());
        if (m.matches()) {
            m = P_MOBILE_COUNTRY_NUM.matcher(tel.getCountryNo());
            if (!m.matches()) {
                error.rejectValue(name, ERROR_CODE_REQUIRED, "手機號的國內區號不需要填寫或只填'0'!");
                return false;
            }
        }
        m = P_TELEPHONE_NUM.matcher(tel.getTelephone());
        m2 = P_MOBILE_NUM.matcher(tel.getTelephone());
        if (!m.matches() && !m2.matches()) {
            error.rejectValue(name, ERROR_CODE_REQUIRED, "電話或傳真號碼必須為7∼9位或11位的數字!");
            return false;
        }
        m = P_EXT_NUM.matcher(tel.getTelephoneExt());
        if (!m.matches()) {
            error.rejectValue(name, ERROR_CODE_REQUIRED, "分機號必須為6位以內的數字!");
            return false;
        }
        m = P_REG.matcher(tel.getRemark());
        if (m.find()) {
            error.rejectValue(name, ERROR_CODE_REQUIRED, "備注中不可以含有‘,’和‘-’分隔符!");
            return false;
        }
        return true;
    }
	*/
    public Object getForm() {
        return form;
    }

    public Errors getError() {
        return error;
    }
    private final String REGEX_PATTERN_NUMERIC = "^(([1-9]\\d*)|(\\d))$";

    /**
     * 验证是否为正整数字
     *
     * @param field 属性名
     * @param errorMessage 异常/错误信息
     */
    protected void checkObjectIsNumeric(String field, String errorMessage) {
        Object value = getValue(field);
        Errors err = getError();
        if (null != value) {
            boolean flag = false;
            if (value instanceof String) {
                flag = Pattern.matches(REGEX_PATTERN_NUMERIC, (String) value);
            }
            if (value instanceof Integer) {
                flag = Pattern.matches(REGEX_PATTERN_NUMERIC, String.valueOf(value));
            }
            if (!flag) {
                err.rejectValue(field, ERROR_CODE_REQUIRED, errorMessage);
            }
        }
    }
}
