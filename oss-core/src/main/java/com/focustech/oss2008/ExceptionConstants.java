package com.focustech.oss2008;

/**
 * <li>異常常量</li>
 *
 * @author renxincai 2008-4-30 15:01:00
 */
public class ExceptionConstants {
    /** 必填項默認異常信息 ，從資源文件讀取,對應exception.properties(key) */
    public static final String INIT_EXCEPTION = "INIT_EXCEPTION"; // 初始化異常;
    /** 異常 用戶名不能為空 */
    public static final String USER_NOTNULL_EXCEPTION = "USER_NOTNULL_EXCEPTION";
    /** 該登錄名已經存在! */
    public static final String USER_HASEXIET_EXCEPTION = "USER_HASEXIET_EXCEPTION";
    /** 該機構名已經存在! */
    public static final String ORGAN_HASEXIET_EXCEPTION = "ORGAN_HASEXIET_EXCEPTION";
    /** 該角色名已經存在! */
    public static final String ROLE_HASEXIET_EXCEPTION = "ROLE_HASEXIET_EXCEPTION";
    /** 試圖對不存在的用戶進行操作 */
    public static final String UNUSER_OPERATION_EXCEPTION = "UNUSER_OPERATION_EXCEPTION";
    /** 試圖對不存在的機構進行操作 */
    public static final String UNORGAN_OPERATION_EXCEPTION = "UNORGAN_OPERATION_EXCEPTION";
    /** 試圖對不存在的角色進行操作 */
    public static final String UNROLE_OPERATION_EXCEPTION = "UNROLE_OPERATION_EXCEPTION";
    /** 試圖對不存在的產品進行操作 */
    public static final String UNPRODUCT_OPERATION_EXCEPTION = "UNPRODUCT_OPERATION_EXCEPTION";
    /** 試圖對不存在的產品類型進行操作 */
    public static final String UNPRODUCT_TYPE_OPERATION_EXCEPTION = "UNPRODUCT_TYPE_OPERATION_EXCEPTION";
    /** 驗證字段為空! */
    public static final String FIELD_ISNULL_EXCEPTION = "FIELD_ISNULL_EXCEPTION";
    /** 試圖對不存在的代理信息進行操作 */
    public static final String UNPROXY_OPERATION_EXCEPTION = "UNPROXY_OPERATION_EXCEPTION";
    /** 驗證碼輸入不正確! */
    public static final String VALIDATE_UNCORRECT_EXCEPTION = "VALIDATE_UNCORRECT_EXCEPTION";
    /** 對不起,您沒有登陸當前系統的權限! */
    public static final String INSUFFICIENT_AUTHENTICATION_EXCEPTION = "INSUFFICIENT_AUTHENTICATION_EXCEPTION";
    /** 數據驗證時取值異常! */
    public static final String VALIDATE_GETVALUE_EXCEPTION = "VALIDATE_GETVALUE_EXCEPTION";
    /** 當前用戶未成功登陸! */
    public static final String LOGON_FAIL_EXCEPTION = "LOGON_FAIL_EXCEPTION";
    /** 該資源名已經存在！ */
    public static final String RESOURCE_HASEXIET_EXCEPTION = " RESOURCE_HASEXIET_EXCEPTION";
    /** 該資源不存在！ */
    public static final String RESOURCE_NOT_EXIST_EXCEPTION = "RESOURCE_NOT_EXIST_EXCEPTION";
    /** 該產品名已經存在! */
    public static final String PRODUCT_HASEXIET_EXCEPTION = "PRODUCT_HASEXIET_EXCEPTION";
    /** 該產品分類名已經存在! */
    public static final String PRODUCT_TYPE_HASEXIET_EXCEPTION = "PRODUCT_TYPE_HASEXIET_EXCEPTION";
    /** 該產品標簽名已經存在! */
    public static final String PRODUCT_TAG_HASEXIET_EXCEPTION = "PRODUCT_TAG_HASEXIET_EXCEPTION";
    /** 該產品分類名稱已存在 */
    public static final String PRODUCT_CATEGORY_HASEXIET_EXCEPTION = "PRODUCT_CATEGORY_HASEXIET_EXCEPTION";
    /** 該控制信息已存在！ */
    public static final String COUNT_LIMIT_HASEXIET_EXCEPTION = "COUNT_LIMIT_HASEXIET_EXCEPTION";
    /** 試圖對不存在的客戶信息進行操作！ */
    public static final String UNCUSTOMER_OPERATION_EXCEPTION = "UNCUSTOMER_OPERATION_EXCEPTION";
    /** 試圖對不存在的信息進行操作！ */
    public static final String UNINFO_OPERATION_EXCEPTION = "UNINFO_OPERATION_EXCEPTION";
    /** 不能對不存在的客戶添加聯系人信息！ */
    public static final String UNEXIST_CUSTOMER_EXCEPTION = "UNEXIST_CUSTOMER_EXCEPTION";
    /** 試圖對不存在的客戶聯系人進行操作！ */
    public static final String UNCONTACT_OPERATE_EXCEPTION = "UNCONTACT_OPERATE_EXCEPTION";
    /** 附件編號不正確 */
    public static final String ATTAMENT_UNCORRECT_EXCEPTION = "ATTAMENT_UNCORRECT_EXCEPTION";
    /** 試圖對不存在的附件進行操作！ */
    public static final String UNCONTACTATTAMENT_OPERATE_EXCEPTION = "UNCONTACTATTAMENT_OPERATE_EXCEPTION";
    /** 該項站點下此賬號已存在 */
    public static final String MEMBER_HASEXIET_EXCEPTION = "MEMBER_HASEXIET_EXCEPTION";
    /** 將該客戶開放出去! */
    public static final String CUSTOMER_HAS_OPEN = "CUSTOMER_HAS_OPEN";
    /** 將該客戶加為自己的私有客戶! */
    public static final String CUSTOMER_HAS_CLOSE = "CUSTOMER_HAS_CLOSE";
    /** 請選擇銷售人員! */
    public static final String CUSTOMER_CHOOSE_SALER = "CUSTOMER_CHOOSE_SALER";
    /** 添加會員賬號 */
    public static final String CUSTOMER_ADD_MEMBER = "CUSTOMER_ADD_MEMBER";
    /** 添加附件 */
    public static final String ADD_ATTACHMENT_INFO = "ADD_ATTACHMENT_INFO";
    /** 修改附件 */
    public static final String MODIFY_ATTACHMENT_INFO = "MODIFY_ATTACHMENT_INFO";
    /** 刪除附件 */
    public static final String DELETE_ATTACHMENT_INFO = "DELETE_ATTACHMENT_INFO";
    /** 已刪除該客戶信息! */
    public static final String CUSTOMER_HAS_DELETE = "CUSTOMER_HAS_DELETE";
    /** 代理商帳戶不存在! */
    public static final String AGENTACCOUNT_NOTEXIST_EXCEPTION = "AGENTACCOUNT_NOTEXIST_EXCEPTION";
    /** 代理商帳戶余額不足! */
    public static final String AGENTBALANCE_NOTENOUGH_EXCEPTION = "AGENTBALANCE_NOTENOUGH_EXCEPTION";
    /** 刪除該聯系人 */
    public static final String CONTACT_HAS_DELETE = "CONTACT_HAS_DELETE";
    /** 刪除會員 */
    public static final String CUSTOMER_DELETE_MEMBER = "CUSTOMER_DELETE_MEMBER";
    /** 修改會員 */
    public static final String CUSTOMER_MODIFY_MEMBER = "CUSTOMER_MODIFY_MEMBER";
    /** 產品不存在 */
    public static final String PRODUCT_NOTEXIST_EXCEPTION = "PRODUCT_NOTEXIST_EXCEPTION";
    /** 產品類型不存在 */
    public static final String PRODUCT_TYPE_NOTEXIST_EXCEPTION = "PRODUCT_TYPE_NOTEXIST_EXCEPTION";
    /** 附件不能為空！ */
    public static final String ATTACHMENT_UNEXIST_EXCEPTION = "ATTACHMENT_UNEXIST_EXCEPTION";
    /** 分配失敗!(對客戶進行了重復分配) */
    public static final String DUBLICATED_ALLOT_CUSTOMER_EXCEPTION = "DUBLICATED_ALLOT_CUSTOMER_EXCEPTION";
    /** 成功分配以上客戶! */
    public static final String SUCCESSED_ALLOT_CUSTOMER_EXCEPTION = "SUCCESSED_ALLOT_CUSTOMER_EXCEPTION";
    /** 不能少于兩個漢字！ */
    public static final String DEFAULT_ERROR_LESSTHAN2CHARS = "DEFAULT_ERROR_LESSTHAN2CHARS";
    /** 客戶數量已經達到了最大限制,不能繼續添加客戶! */
    public static final String CUSTOMER_OVER_UPPPER_LIMIT_EXCEPTION = "CUSTOMER_OVER_UPPPER_LIMIT_EXCEPTION";
    /** 客戶有資源預定，不能開放！ */
    public static final String UNOPEN_HAS_RESOURCE_BOOKING_EXCEPTION = "UNOPEN_HAS_RESOURCE_BOOKING_EXCEPTION";
    /** 添加客戶基本信息成功！ */
    public static final String CUSTOMER_SUCC_ADD_BASE = "CUSTOMER_SUCC_ADD_BASE";
    /** 創建財務信息成功！ */
    public static final String CUSTOMER_SUCC_ADD_FINANCE = "CUSTOMER_SUCC_ADD_FINANCE";
    /** 成功刪除該條信息！ */
    public static final String SUCC_DELETE_MESSAGE = "SUCC_DELETE_MESSAGE";
    /** 分配失敗！ */
    public static final String FAILED_ALLOT_CUSTOMER = "FAILED_ALLOT_CUSTOMER";
    /** 成功將該客戶分配給 */
    public static final String SUCC_ALLOT_CUSTOMER_TO = "SUCC_ALLOT_CUSTOMER_TO";
    /** 操作成功 */
    public static final String SUCC_OPERATE = "SUCC_OPERATE";
    /** 操作失敗 */
    public static final String FAILED_OPERATE = "FAILED_OPERATE";
    /** 已經成功地將該客戶設置為撞單！ */
    public static final String SUCC_SET_CUSTOMER_CROSS = "SUCC_SET_CUSTOMER_CROSS";
    /** 中文 */
    public static final String LANGUAGE_CN = "LANGUAGE_CN";
    /** 英文 */
    public static final String LANGUAGE_EN = "LANGUAGE_EN";
    /** 成功將該會員公司加入到銷售系統中 */
    public static final String SUCC_ADD_MEMBER_TO_SAL = "SUCC_ADD_MEMBER_TO_SAL";
    /** 對不起，你不能對其他用戶的信息進行操作！ */
    public static final String FAILED_MODIFY_OTHER_INFO = "FAILED_MODIFY_OTHER_INFO";
    /** 原密碼不正確！ */
    public static final String FAILED_MODIFY_PASSWORD_INCORRECT = "FAILED_MODIFY_PASSWORD_INCORRECT";
    /** 添加成功！ */
    public static final String SUCC_ADD = "SUCC_ADD";
    /** 添加失敗！ */
    public static final String FAILED_ADD = "FAILED_ADD";
    /** 修改成功！ */
    public static final String SUCC_MODIFY = "SUCC_MODIFY";
    /** 修改失敗！ */
    public static final String FAILED_MODIFY = "FAILED_MODIFY";
    /** 該客戶發生了撞單 */
    public static final String CUSTOMER_HAS_CROSS = "CUSTOMER_HAS_CROSS";
    // --------------------------------------合同部分-----------------------------------
    /** 試圖對不存在的合同進行操作 */
    public static final String UNCONTRACT_OPERATION_EXCEPTION = "UNCONTRACT_OPERATION_EXCEPTION";
    /** 試圖對不存在的合同或者為非正式合同進行操作 */
    public static final String UNCONTRACT_OR_INFORMAL_NOPERATION_EXCEPTION =
            "UNCONTRACT_OR_INFORMAL_NOPERATION_EXCEPTION";
    /** 關聯合同不存在! */
    public static final String CONTRACT_RELATED_NOTEXIST_EXCEPTION = "CONTRACT_RELATED_NOTEXIST_EXCEPTION";
    /** 添加合同產品 */
    public static final String ADD_CONTRACTPRODUCT = "ADD_CONTRACTPRODUCT";
    /** 添加合同變更產品 */
    public static final String ADD_CONTRACTALTERPRODUCT = "ADD_CONTRACTALTERPRODUCT";
    /** 合同產品不存在！ */
    public static final String CONTRACTPRODUCT_NOTEXIET_EXCEPTION = "CONTRACTPRODUCT_NOTEXIET_EXCEPTION";
    /** 請先添加合同產品! */
    public static final String ADD_CONTRACTPRODUCT_EXCEPTION = "ADD_CONTRACTPRODUCT_EXCEPTION";
    /** 合同總額未設定! */
    public static final String CONTRACTMONEY_NOTEXIET_EXCEPTION = "CONTRACTMONEY_NOTEXIET_EXCEPTION";
    /** 合同狀態已經改變！ */
    public static final String CONTRACTSTATE_HASCHANGED_EXCEPTION = "CONTRACTSTATE_HASCHANGED_EXCEPTION";
    /** 試圖對不存在的合同收款批次進行操作！ */
    public static final String UNCONTACTRECEIVABLE_OPERATE_EXCEPTION = "UNCONTACTRECEIVABLE_OPERATE_EXCEPTION";
    /** 試圖對不存在的合同財務信息進行操作！ */
    public static final String UNCONTACTFINANCE_OPERATE_EXCEPTION = "UNCONTACTFINANCE_OPERATE_EXCEPTION";
    /** 應收款批次與合同總額不等！ */
    public static final String CONTRACTRECEIVE_MONEY_EXCEPTION = "CONTRACTRECEIVE_MONEY_EXCEPTION";
    /** 付款信息的通知發送成功 */
    public static final String SEND_BILL_ADIVE_SUCCESS = "SEND_BILL_ADIVE_SUCCESS";
    /** 關聯合同不能為空! */
    public static final String RELATED_CONTRACT_CANNOTNULL_EXCEPTION = "RELATED_CONTRACT_CANNOTNULL_EXCEPTION";
    /** 關聯合同所屬客戶與該合同不一致! */
    public static final String RELATED_CONTRACT_ACCOUNT_UNACCORD_EXCEPTION =
            "RELATED_CONTRACT_ACCOUNT_UNACCORD_EXCEPTION";
    /** 請選擇產品! */
    public static final String CONTRACT_CHOOSE_PRODUCT = "CONTRACT_CHOOSE_PRODUCT";
    /** 請選擇客戶! */
    public static final String CONTRACT_CHOOSE_ACCOUNT = "CONTRACT_CHOOSE_ACCOUNT";
    // ----------------------------- 資源預定部分 ----------------------------------
    /** 預定資源無效! */
    public static final String RESOURCEBOOKING_INVALIDATION_EXCEPTION = "RESOURCEBOOKING_INVALIDATION_EXCEPTION";
    /** 沒有可用的預定資源! */
    public static final String RESOURCEBOOKING_NEED_EXCEPTION = "RESOURCEBOOKING_NEED_EXCEPTION";
    /** 當前資源範圍選擇配置有誤 */
    public static final String RESOURCE_ID_ERROR = "RESOURCE_ID_ERROR";
    /** 資源不能預定 */
    public static final String RESOURCE_NOT_BOOK_EXCEPTION = "RESOURCE_NOT_BOOK_EXCEPTION";
    /** 預定資源已被使用! */
    public static final String RESOURCEBOOKING_HAS_USED_EXCEPTION = "RESOURCEBOOKING_HAS_USED_EXCEPTION";
    /** 該資源已被釋放! */
    public static final String RESOURCEBOOKING_RELEASE_EXCEPTION = "RESOURCEBOOKING_RELEASE_EXCEPTION";
    /** 預定資源不能重復使用! */
    public static final String BOOKING_CANNOT_REPEAT_USED_EXCEPTION = "BOOKING_CANNOT_REPEAT_USED_EXCEPTION";
    // ----------------------------- 產品標簽部分 ----------------------------------
    public static final String PRODUCT_TYPE_DISABLED_EXCEPTION = "PRODUCT_TYPE_DISABLED_EXCEPTION";
    /** 產品標簽不存在 */
    public static final String PRODUCT_LABEL_NOEXIT_EXCEPTION = "PRODUCT_LABEL_NOEXIT_EXCEPTION";
    /** 添加客戶聯系人 */
    public static final String ADD_CUSTOMER_CONTACT = "ADD_CUSTOMER_CONTACT";
    /** 修改客戶聯系人 */
    public static final String MODIFY_CUSTOMER_CONTACT = "MODIFY_CUSTOMER_CONTACT";
    /** 添加業務聯系記錄 */
    public static final String ADD_CUSTOMER_RECORD = "ADD_CUSTOMER_RECORD";
    /** 修改業務聯系記錄 */
    public static final String MODIFY_CUSTOMER_RECORD = "MODIFY_CUSTOMER_RECORD";
    /** 合同付款信息不存在 */
    public static final String CONTRACT_PAYMENT_INFO = "CONTRACT_PAYMENT_INFO";
    /** 財務付款信息不存在 */
    public static final String FINANCE_PAYMENT_INFO = "FINANCE_PAYMENT_INFO";
    /** 合同付款信息不存在或合同總金額不存在 */
    public static final String CONTRACT_PAYMENT_OR_TOTAL_MONEY_EXCEPTION = "CONTRACT_PAYMENT_OR_TOTAL_MONEY_EXCEPTION";
    // ----------------------------- 代理商折扣部分 ----------------------------------
    /** 代理商折扣不存在 */
    // public static final String AGENT_DISCOUNT_NOEXIT_EXCEPTION = "";
    /** 開始時間不能早于簽約時間! */
    public static final String START_CANNOT_BEFORE_SINGN = "START_CANNOT_BEFORE_SINGN";
    /** 結束時間不能早于開始時間! */
    public static final String END_CANNOT_BEFORE_START = "END_CANNOT_BEFORE_START";
    /** 開始時間不能早于原產品開始時間! */
    public static final String STARTTIME_CANNOT_BEFORE_ORIGINALLY = "STARTTIME_CANNOT_BEFORE_ORIGINALLY";
    /** 合同簽約時間不能早于關聯合同簽約時間! */
    public static final String SIGNTIME_CANNOT_BEFORE_RELATED = "SIGNTIME_CANNOT_BEFORE_RELATED";
    /** 該產品已被變更,不能修改! */
    public static final String PRODUCT_ALTERED_CANNOT_MODIFY = "PRODUCT_ALTERED_CANNOT_MODIFY";
    /** 該產品已被變更,不能刪除! */
    public static final String PRODUCT_ALTERED_CANNOT_DELETE = "PRODUCT_ALTERED_CANNOT_DELETE";
    /** 應收款金額不能大于合同剩余應收金額! */
    public static final String RECEIVABLE_CANNOT_LARGER_REMNANT = "RECEIVABLE_CANNOT_LARGER_REMNANT";
    /** 總金額應大于成本! */
    public static final String TOTAL_MUST_LARGER_COMMION = "TOTAL_MUST_LARGER_COMMION";
    /** 總金額應大于折扣值! */
    public static final String TOTAL_MUST_LARGER_DISCOUNT = "TOTAL_MUST_LARGER_DISCOUNT";
    /** 總金額應大于成本與折扣值之和! */
    public static final String TOTAL_MUST_LARGER_COMMION_AND_DISCOUNT = "TOTAL_MUST_LARGER_COMMION_AND_DISCOUNT";
    /** 開始執行時間應該晚于 變更產品開始時間 */
    public static final String STARTTIME_SHOULD_AFTER_ALTERSTARTTIME = "STARTTIME_SHOULD_AFTER_ALTERSTARTTIME";
    // ----------------------------- 自定義提醒標簽部分 ----------------------------------
    /** 自定義提醒不存在 */
    public static final String REMIND_NOEXIT_EXCEPTION = "REMIND_NOEXIT_EXCEPTION";
    /** 付款信息不存在 */
    public static final String FINANCE_PAYMENT_NOEXIT_EXCEPTION = "FINANCE_PAYMENT_NOEXIT_EXCEPTION";
    /** 對不起,您沒有權限訪問! */
    public static final String ACCESS_DENIED_EXCEPTION = "ACCESS_DENIED_EXCEPTION";
    /** 附件不存在！ */
    public static final String ATTACHMENT_NOT_EXIST = "ATTACHMENT_NOT_EXIST";
    // -------------------------------- 郵件部分 --------------------------------------
    /** 郵件主題已經不存在！ */
    public static final String MAIL_SUBJECT_NOT_EXIST = "MAIL_SUBJECT_NOT_EXIST";
    /** 此產品類型的預定排名第一已被使用 */
    public static final String PRODUCT_TYPE_ONE_USED = "PRODUCT_TYPE_ONE_USED";
    /** 試圖對不存在的SGS配置信息進行操作 */
    public static final String SGS_PAYMENT_CONFIG_NOT_EXIST = "SGS_PAYMENT_CONFIG_NOT_EXIST";
    /** 發送郵件信息不存在! */
    public static final String SEND_MAIL_LOG_NOT_EXIST = "SEND_MAIL_LOG_NOT_EXIST";
    // --------------------------------客戶郵寄信息管理---------------------------------
    /** 選中的客戶中存在已經處理過的數據！ */
    public static final String CUSTOMER_MAIL_HAS_EXIST = "CUSTOMER_MAIL_HAS_EXIST";
    /** 選擇的客戶都是非潛在客戶 */
    public static final String CUSTOMER_MAIL_ILLEGAL_DATA = "CUSTOMER_MAIL_ILLEGAL_DATA";
    /** 審核數量已超過上限！ */
    public static final String CUSTOMER_MAIL_OVER_UPPER_LIMIT = "CUSTOMER_MAIL_OVER_UPPER_LIMIT";
    /** 請選擇市場活動客戶再進行操作！ */
    public static final String CUSTOMER_MAIL_EXPECTED_SELECT = "CUSTOMER_MAIL_EXPECTED_SELECT";
    /** 已經審核XX條，還可以繼續審核XX條！ */
    public static final String CUSTOMER_MAIL_CHECKED_INFO = "CUSTOMER_MAIL_CHECKED_INFO";
    /** 所選的客戶信息都是已審核信息！ */
    public static final String CUSTOMER_MAIL_SELECTED_ALL_CHECKED = "CUSTOMER_MAIL_SELECTED_ALL_CHECKED";
    /** 請檢查所選潛在客戶的數據是否完整！ */
    public static final String CUSTOMER_MAIL_EXPECTED_CHECK_DATA = "CUSTOMER_MAIL_EXPECTED_CHECK_DATA";
    /** 請選擇正確的操作！ */
    public static final String CUSTOMER_MAIL_EXPECTED_CORRECT_OPRATE = "CUSTOMER_MAIL_EXPECTED_CORRECT_OPRATE";
    // --------------------------------撞單信息部分--------------------------------------------
    /** 撞單信息不存在 */
    public static final String CRASH_MESSAGE_NOT_EXIST = "CRASH_MESSAGE_NOT_EXIST";
}
