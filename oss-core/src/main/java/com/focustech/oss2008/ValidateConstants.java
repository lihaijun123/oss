package com.focustech.oss2008;

/**
 * <li>驗證常量</li>
 *
 * @author renxincai 2008-4-30 15:00:12
 */
public class ValidateConstants {
    /** 必填項默認報錯信息 ，從資源文件讀取,對應validate.properties(key) */
    public static final String DEFAULT_ERROR_MESSAGE_REQUIRED = "DEFAULT_ERROR_MESSAGE_REQUIRED"; // "不能為空!";
    public static final String DEFAULT_ERROR_ISNUMERIC = "DEFAULT_ERROR_ISNUMERIC"; // "不是數字!";
    public static final String DEFAULT_ERROR_ISINTEGER = "DEFAULT_ERROR_ISINTEGER"; // "不是整型!";
    public static final String DEFAULT_ERROR_ISMINUSINTEGER = "DEFAULT_ERROR_ISMINUSINTEGER"; // 不是非負整數!;
    public static final String DEFAULT_ERROR_ISDOUBLE = "DEFAULT_ERROR_ISDOUBLE"; // "不是double型!";
    public static final String DEFAULT_ERROR_ISFLOAT = "DEFAULT_ERROR_ISFLOAT"; // "不是float型!";
    public static final String DEFAULT_ERROR_ISDATE = "DEFAULT_ERROR_ISDATE"; // "時間格式錯誤!";
    public static final String DEFAULT_ERROR_ISCONTAINCHINESE = "DEFAULT_ERROR_ISCONTAINCHINESE"; // "不能含有漢字!";
    public static final String DEFAULT_ERROR_ISEMAIL = "DEFAULT_ERROR_ISEMAIL"; // "Emai格式不正確!";
    public static final String DEFAULT_ERROR_ISINCORRECT = "DEFAULT_ERROR_ISINCORRECT"; // "不能含有非法字符!";
    public static final String DEFAULT_ERROR_STRINGLENGTH = "DEFAULT_ERROR_STRINGLENGTH"; // "字數不在規定範圍內!";
    public static final String DEFAULT_ERROR_NOTSAMENESS = "DEFAULT_ERROR_NOTSAMENESS"; // 不相同!
    /** 小數點後位數不能大于3 */
    public static final String DEFAULT_ERROR_DECIMALLENGTH = "DEFAULT_ERROR_DECIMALLENGTH";
    /** 密碼長度須在6-20位間! */
    public static final String PASSWORD_ERROR_LENGTH = "PASSWORD_ERROR_LENGTH";
    /** 密碼必須同時含有數字與字母! */
    public static final String PASSWORD_ERROR_ISHASNUMANDCHAR = "PASSWORD_ERROR_ISHASNUMANDCHAR";
    /** 默認非法字符 */
    public static final String DEFAULT_INCORRECTCHAR = "DEFAULT_INCORRECTCHAR"; // "'<>\\#%&"
    /** 結束時間不能小于開始時間 */
    public static final String TODATE_NOTLESS_FROMDATE = "TODATE_NOTLESS_FROMDATE";
    /** 必須大于 */
    public static final String DEFAULT_MUST_BIGGER = "DEFAULT_MUST_BIGGER";
    /** 不能小于 */
    public static final String DEFAULT_CANNOT_LESS = "DEFAULT_CANNOT_LESS";
    /** 必須小于 */
    public static final String DEFAULT_MUST_LESS = "DEFAULT_MUST_LESS";
    /** 不能大于 */
    public static final String DEFAULT_CANNOT_BIGGER = "DEFAULT_CANNOT_BIGGER";
    /** 必須等于 */
    public static final String DEFAULT_MUST_EQUAL = "DEFAULT_MUST_EQUAL";
    /** 時間不在當前時間一月內 */
    public static final String DATE_MUST_INNER_MONTH = "DATE_MUST_INNER_MONTH";
    /** 數值不能超過9位! */
    public static final String VALUE_IS_OVER_BOUND = "VALUE_IS_OVER_BOUND";
    /** 不能為負! */
    public static final String DEFAULT_ERROR_ISMINUS = "DEFAULT_ERROR_ISMINUS";
    /** 提醒標題最大不能超過250個漢字 */
    public static final String REMIND_TITLE_MAXLENGTH = "REMIND_TITLE_MAXLENGTH";
    /** 提醒內容最大不能超過2000個漢字 */
    public static final String REMIND_CONTENT_MAXLENGTH = "REMIND_CONTENT_MAXLENGTH";
    /** 提醒標題 */
    public static final String REMIND_TITLE_NOT_NULL = "REMIND_TITLE_NOT_NULL";
    /** 提醒時間 */
    public static final String REMIND_TIME_NOT_NULL = "REMIND_TIME_NOT_NULL";
    /** 產品類型名稱不能為空 */
    public static final String PRODUCT_TYPE_NAME_NOT_NULL = "PRODUCT_TYPE_NAME_NOT_NULL";
    /** 產品類型標簽不能為空 */
    public static final String PRODUCT_TYPE_LABLE_NOT_NULL = "PRODUCT_TYPE_LABLE_NOT_NULL";
}
