package com.focustech.oss2008;

/**
 * <li>OSS2008常量</li>
 *
 * @author yangpeng 2008-4-9 下午05:21:12 <a href="mailto:ypypnj@gmail.com">contact yang peng</a>
 */
public class Constants {
    // --------------------------管理員角色---------------------------
    /** 管理員角色 */
    public static final String ROLE_ADMIN = "admin";
    /** 管理員角色ID */
    public static final long ROLE_ADMIN_ID = 41L;
    /** 系統登陸名 */
    public final static String SYSTEM_LOGIN_NAME = "system";
    /** 系統管理員ID */
    public final static String SYSTEM_USER_ID = "99999999";
    // ----------------------買家服務專員系統角色----------------------
    /** 買家服務專員系統角色ID */
    public final static String BUYERSERVICE_USER_ID = "99999998";
    // --------------------------log root--------------------------
    /** 組件目錄 */
    public static final String LOG_ROOT_OSS = "focus_oss";
    public static final String LOG_ROOT_SERVICE = "focus_oss";
    public static final String LOG_ROOT_WEB = "focus_oss";
    public static final String LOG_ROOT_SCHEDULER = "focus_oss";
    public static final String LOG_ROOT_DAO = "focus_oss";
    // --------------------------logic type--------------------------
    /** String true */
    public static final String LOGIC_TRUE = "1";
    /** String false */
    public static final String LOGIC_FALSE = "0";
    // --------------------------site type--------------------------
    /** 網站類型:MIC英文版 */
    public static final String SITE_TYPE_MIC_EN = "0";
    /** 網站類型:MIC中文版 */
    public static final String SITE_TYPE_MIC_CN = "1";
    /** 網站類型:靈動 */
    public static final String SITE_TYPE_SMART = "2";
    // ----------------------departmentID---------------------------
    /** 焦點總部 */
    public static final String DEPARTMENT_FOCUSTECH = "0000";
    // ----------------------department active----------------------
    /** 部門狀態:可用 */
    public static final String DEPARTMENT_ACTIVE = "1";
    /** 部門狀態:禁用 */
    public static final String DEPARTMENT_FORBIDDEN = "0";
    // ----------------------------------部門類型------------------------------
    /** 1︰本部 */
    public static final String DEPARTMENT_TYPE_HQ = "1";
    /** 2︰辦事處 */
    public static final String DEPARTMENT_TYPE_OFFICE = "2";
    /** 3︰一級代理商 */
    public static final String DEPARTMENT_TYPE_AGENT_FIRST = "3";
    /** 4︰二級代理商 */
    public static final String DEPARTMENT_TYPE_AGENT_SECOND = "4";
    /** 5︰非公司機構 */
    public static final String DEPARTMENT_TYPE_NOT_COMPANY = "5";
    // ----------------------------------語言版本簡稱-----------------------------
    /** 英文 */
    public static final String LANGUAGE_SHORT_EN = "EN";
    /** 中文 */
    public static final String LANGUAGE_SHORT_CN = "CN";
    //
    /** 一級菜單 */
    public static final String ROOT_MENU = "0";
    /** 一級部門 */
    public static final String ROOT_DEPARTMENT = "0";
    // /** 目錄菜單 */
    // public static final String CATALOG_MENU = "folder";
    // /** 文件菜單 */
    // public static final String FILE_MENU = "file";
    // ---------------------------系統模塊----------------------------
    /** 所有模塊 */
    public static final String SYSTEM_MODEL_ALL = "ALL";
    // --------------------------sex--------------------------------
    /** 性別:男 */
    public static final String SEX_MALE = "1";
    /** 性別:女 */
    public static final String SEX_FEMALE = "2";
    // ------------------------資源是否顯示---------------------------
    /** 顯示資源 */
    public static final String RESOURCE_DISPLAY = "1";
    /** 不顯示資源 */
    public static final String RESOURCE_HIDDEN = "0";
    // --------------------------流程常量------------------------------
    /** 流程(合同)ID */
    public static final long CONTRACT_PROCESS_ID = 1L;
    // -------------------------流程狀態是否初始狀態----------------------------
    /** 是初始狀態 */
    public static final String IS_START_STATE = "1";
    /** 不是初始狀態 */
    public static final String ISNT_START_STATE = "0";
    // ------------------------匯率信息---------------------------
    /** 匯率歷史記錄 */
    public static final String HISTROY_RECORD = "0";
    /** 匯率正式記錄 */
    public static final String FORMAL_RECORD = "1";
    // ------------------------客戶類型---------------------------------
    /** MIC客戶 */
    public static final String ACCOUNT_TYPE = "0";
    /** 海外買家客戶 */
    public static final String ABROAD_PURCHASE = "1";
    // -------------------------客戶付款方式-----------------------------
    /** 未確定 */
    public static final String ACCOUNT_PAYMENT_NOTSURE = "0";
    // -------------------------客戶屬性------------------------------------
    /** 私有 */
    public static final String ACCOUNT_PROPERTY_PRIVATE = "0";
    /** 開放 */
    public static final String ACCOUNT_PROPERTY_OPEN = "1";
    /** 疑似撞單 */
    public static final String ACCOUNT_PROPERTY_MAYBE_CROSS = "2";
    /** 撞單 */
    public static final String ACCOUNT_PROPERTY_CROSS = "3";
    /** 集中處理 */
    public static final String ACCOUNT_PROPERTY_PROCESS = "4";
    // -------------------------客戶分配日志類型------------------------------------
    /** 公共客戶分配 */
    public static final String ACCOUNT_PUBLIC_ALLOT = "0";
    // ---------------------------合同屬性狀態------------------------------
    /** 合同客戶屬性 0為介紹（默認） */
    public static final String CUSTOMER_TYPE_INTRO = "0";
    /** 1為詢盤 */
    public static final String CUSTOMER_TYPE_ENQUIRE = "1";
    /** 2為繼承 */
    public static final String CUSTOMER_TYPE_EXTEND = "2";
    /** 3為自有 */
    public static final String CUSTOMER_TYPE_PRIVATE = "3";
    // ----------------------------合同客戶證件類型---------------------------
    /** 0: 無 */
    public static final String CONTRACT_CARD_TYPE_NONE = "0";
    // 銷售系統合同狀態
    /** 銷售系統合同狀態:作廢合同 */
    public final static String SAL_CONTRACT_STATUS_DELETED_OFFICIAL = "0";
    /** 銷售系統合同狀態:草擬合同 */
    public final static String SAL_CONTRACT_STATUS_ROUGH = "1";
    /** 銷售系統合同狀態:待審核合同 */
    public final static String SAL_CONTRACT_WAIT_CHECK = "2";
    /** 銷售系統合同狀態:正式合同 */
    public final static String SAL_CONTRACT_STATUS_OFFICIAL = "3";
    /** 銷售系統合同狀態:審核未通過 */
    public final static String SAL_CONTRACT_STATUS_BACK = "4";
    // ---------------------------發票郵寄方式------------------------------
    /** 掛號 */
    public final static String INVOICE_MAILING_REGISTER = "1";
    /** 快遞 */
    public final static String INVOICE_MAILING_EXPRESS = "2";
    /** 平信 */
    public final static String INVOICE_MAILING_ORDOMARY_MAIL = "3";
    /** 其他 */
    public final static String INVOICE_MAILING_OTHER = "4";
    // ------ 設置關聯合同類型 1︰普通合同，2︰補充合同，3︰續約合同------------
    /** 0︰普通合同 */
    public static final String RELATED_CONTRACT_COMMON = "0";
    /** 1︰續約合同 */
    public static final String RELATED_CONTRACT_RENEW = "1";
    /** 2︰升級合同 */
    public static final String RELATED_CONTRACT_UPGRADE = "2";
    /** 3︰補充合同 */
    public static final String RELATED_CONTRACT_REINFORCE = "3";
    // --------------聯系人類型--------------------------------------------
    /** 主聯系人 */
    public static final String CONTACT_TYPE = "0";
    /** 有效聯系人 */
    public static final String CONTACT_TYPE_USEFUL = "1";
    /** 無效聯系人 */
    public static final String CONTACT_TYPE_DISABLED = "2";
    // -------------------貨幣類型-----------------------------------
    /** 1︰元 */
    public static final String CURRENCY_UNIT_RMB = "1";
    /** 2︰美元 */
    public static final String CURRENCY_UNIT_MY = "2";
    // ------------------------ 代理商部門 ----------------------
    public static final String AGENT_DEPARTMENT = "22";
    // 合同財務信息
    /** 財務憑證標志 1︰有憑證 */
    public static final String CONTRACT_PAYMENT_VOUCHER_FLAG_TRUE = "1";
    /** 財務憑證標志 0︰無憑證 */
    public static final String CONTRACT_PAYMENT_VOUCHER_FLAG_FALSE = "0";
    /** 財務狀態 0為都未到 */
    public static final String CONTRACT_PAYMENT_STATUS_ALLNOT = "0";
    /** 財務狀態 2為部分金額到 */
    public static final String CONTRACT_PAYMENT_STATUS_SOME = "2";
    /** 財務狀態 3為結清 */
    public static final String CONTRACT_PAYMENT_STATUS_SQUARE = "3";
    /** 開票通知狀態 0︰未通知 */
    public static final String INVOICE_NOTICE_STATUS_NOT = "0";
    /** 開票通知狀態 1︰發送通知 */
    public static final String INVOICE_NOTICE_STATUS_SEND = "1";
    /** 開票通知狀態 2︰填寫完畢 */
    public static final String INVOICE_NOTICE_STATUS_OVER = "2";
    /** 發票寄送狀態 0︰未寄出 */
    public static final String INVOICE_SEND_STATUS_NOT = "0";
    /** 發票寄送狀態 1︰寄出部分金額發票 */
    public static final String INVOICE_SEND_STATUS_SEND = "1";
    /** 發票寄送狀態 2︰寄送完畢 */
    public static final String INVOICE_SEND_STATUS_OVER = "2";
    /** 是否發送郵件 1.是 */
    public static final String SEND_MAIL_YES = "1";
    /** 是否發送郵件 0.否 */
    public static final String SEND_MAIL_NO = "0";
    // ------------------------------重要部門編號-----------------------------
    /** 銷售部門號 */
    public static final String SAL_DEPARTMENT_NO = "2000";
    /** 渠道部門號 */
    public static final String CHANNEL_DEPARTMENT_NO = "2200";
    /** 業務拓展中心部門號 */
    public static final String OPERATION_DEPARTMENT_NO = "2300";
    /** 直銷部門號 */
    public static final String SAL_DIRECT_DEPARTMENT_NO = "2100";
    /** 客服部門號 */
    public static final String CST_DEPARTMENT_NO = "1200";
    /** 落地客服部門號 */
    public static final String COW_DEPARTMENT_NO = "1210";
    // -----------------------------附件類型---------------------------------
    /** 合同附件 */
    public static final String CONTRACT_SLAVE_TYPE = "1";
    /** 客戶附件 */
    public static final String CUSTOMER_SLAVE_TYPE = "2";
    /** 業務聯系附件 */
    public static final String CONTACT_SLAVE_TYPE = "3";
    // -----------------------------代理商審核狀態------------------------------
    /** 邏輯刪除 */
    public static final String AGENT_ACCOUNT_LOGIC_DELETE = "0";
    /** 待審核 */
    public static final String AGENT_ACCOUNT_DEAL_WITH_AUDIT = "1";
    /** 審核通過 */
    public static final String AGENT_ACCOUNT_PASS_AUDIT = "2";
    /** 審核不通過 */
    public static final String AGENT_ACCOUNT_CAN_NOT_PASS_AUDIT = "3";
    // -----------------------------代理商記錄類型------------------------------
    /** 代理商 */
    public static final String RECORD_TYPE_AGENT_ACCOUNT = "0";
    /** 辦事處 */
    public static final String RECORD_TYPE_AGENT_OFFICE = "1";
    // -----------------------------代理商帳戶------------------------------
    /** 帳戶初始金額 */
    public static final Double DEFAULE_AGENT_ACCOUNT_BALANCE = 0.0;
    // -----------------------------代理商付款類型------------------------------
    /** 付款 */
    public static final String AGENT_PAYMENT = "1";
    /** 返點 */
    public static final String AGENT_BACK_POINT = "2";
    /** 修正合同扣款 */
    public static final String AGENT_CORRECT_CONTRACT = "3";
    /** 保證金 */
    public static final String AGENT_BAIL = "4";
    /** 合同扣款 */
    public static final String AGENT_DEDUCT_MONEY = "5";
    // -----------------------------------付款類型------------------------
    /** 0︰無人認領 */
    public static final String PAYMENT_TYPE_NO = "0";
    /** 1︰確定認領 */
    public static final String PAYMENT_TYPE_CONFIRM = "1";
    /** 2︰回款記錄 */
    public static final String PAYMENT_TYPE_RECORD = "2";
    // -----------------------------------財務回款類型------------------------
    /** 0︰無人認領 */
    public static final String FINANCE_PAYMENT_TYPE_NO = "0";
    /** 1︰確定認領 */
    public static final String FINANACE_PAYMENT_TYPE_CONFIRM = "1";
    /** 2︰認領完畢 */
    public static final String FINANACE_PAYMENT_TYPE_RESULT = "2";
    /** 3︰認領完畢並已回款 */
    public static final String FINANACE_PAYMENT_TYPE_RESULT_AND_FUND = "3";
    /*******************************************************************************************************************
     * 業務狀態/ /** 1︰啟用
     */
    public static final String ACTIVE_TYPE_ALLOW = "1";
    /** 0︰禁用 */
    public static final String ACTIVE_TYPE_FORBID = "0";
    // -----------------------------------付款方式-------------------------------
    /** 未確認 */
    public static final String PAYMENT_MODE_UNCONFIRM = "0";
    /** 現金 */
    public static final String PAYMENT_MODE_CASH = "1";
    /** Paypal支付 */
    public static final String PAYMENT_MODE_PAYPAL = "9";
    /** 系統回款 */
    public static final String PAYMENT_MODE_SYSTEM = "13";
    // -----------------------------價格單位---------------------------------
    /** 元 */
    public static final String PRICE_UNIT_RMB = "1";
    /** 美元 */
    public static final String PRICE_UNIT_DOLLAR = "2";
    // -----------------------------周期單位---------------------------------
    /** 天 */
    public static final String PRICE_CYCLE_UNIT_DAY = "1";
    /** 周 */
    public static final String PRICE_CYCLE_UNIT_WEEK = "2";
    /** 月 */
    public static final String PRICE_CYCLE_UNIT_MONTH = "3";
    /** 年 */
    public static final String PRICE_CYCLE_UNIT_YEAR = "4";
    /** 季度 */
    public static final String PRICE_CYCLE_UNIT_SEASON = "5";
    /** CPM */
    public static final String PRICE_CYCLE_UNIT_CPM = "6";
    // ---------------------------------購買標識------------------------
    /** 贈送 */
    public static final String BUY_TYPE_PRESENT = "0";
    /** 購買 */
    public static final String BUY_TYPE_BUY = "1";
    // -----------------------------------合同流程ID-------------------------------
    /** 合同流程ID:變更合同 */
    public static final long CONTRACT_PROCESS_ALTER = 4;
    /** 執行確認 */
    public static final String CONTRACT_TRANSFER_COMFIRM = "11";
    /** 正式合同 修改 */
    public static final String CONTRACT_TRANSFER_MODIFY = "14";
    /** 執行確認 標志 */
    public static final String CONTRACT_ZHIXING = "CONTRACT_ZHIXING";
    // ------------------------------合同產品執行狀態------------------------------
    /** 不可執行 */
    public static final String PRODUCT_EXECUTED_STATUS_CANNOT = "0";
    /** 可執行 */
    public static final String PRODUCT_EXECUTED_STATUS_CAN = "1";
    /** 已執行 */
    public static final String PRODUCT_EXECUTED_STATUS_HAS = "2";
    /** 暫停 */
    public static final String PRODUCT_EXECUTED_STATUS_PAUSE = "3";
    /** 終止 */
    public static final String PRODUCT_EXECUTED_STATUS_CANCEL = "4";
    /** 5:已添加客戶服務記錄 */
    public static final String PRODUCT_EXECUTED_STATUS_NOTED = "5";
    // ---------------------------------合同產品標識------------------------
    /** 普通產品 */
    public static final String PRODUCT_FLAG_COMMON = "1";
    /** 記錄變更產品 */
    public static final String PRODUCT_FLAG_CHANGING = "2";
    /** 續約產品 */
    public static final String PRODUCT_FLAG_SEQUENCE = "3";
    /** 取所有產品 */
    public static final String PRODUCT_FLAG_ALL = "ALL";
    // -----------------------------預定大類-----------------------------------
    /** 關鍵詞 */
    public static final String BOOKING_TYPE_KEYWORD = "1";
    /** 目錄 */
    public static final String BOOKING_TYPE_CATALOG = "2";
    /** 頻道 */
    public static final String BOOKING_TYPE_CHANNEL = "3";
    // -----------------------------資源保護期類型-----------------------------------
    /** 合同開始後 */
    public static final String RESOURCE_PERIOD_TYPE_AFTER = "1";
    /** 合同結束前 */
    public static final String RESOURCE_PERIOD_TYPE_BEFORE = "2";
    // -----------------------------是否預定-----------------------------------
    public static final String PROD_TYPE_ISBOOKED_FALSE = "0";
    //
    public static final String PROD_TYPE_ISBOOKED_TRUE = "1";
    // -----------------------------固定排名第一-----------------------------------
    public static final String PARAM_TYPE_EXECUTE_POSITION_FIRST = "100";
    // -----------------------------隨機排名-----------------------------------
    public static final String PARAM_TYPE_EXECUTE_POSITION_RANDON = "0";
    // -----------------------------資源預定狀態-----------------------------------
    /** 釋放 */
    public static final String PARAM_TYPE_RESOURCE_PROTECT_STATUS_RELEASE = "3";
    // -------------------------------------------------------------------------
    /** 預訂 */
    public static final String PARAM_TYPE_USED_STATUS_ORDER = "0";
    /** 草擬合同中使用 */
    // public static final String PARAM_TYPE_USED_STATUS_BUDGET_CONTRACT = "1";
    /** 正式合同中使用 */
    public static final String PARAM_TYPE_USED_STATUS_FORMAL_CONTRACT = "2";
    /** 自己釋放資源 */
    public static final String PARAM_TYPE_USED_STATUS_RELEASE = "3";
    /** 續訂資源 */
    public static final String PARAM_TYPE_USED_STATUS_RELET = "4";
    // 實體目錄、鏈接目錄
    /** 實體目錄 */
    public static final String CATALOG_TYPE_ENTITY = "0";
    /** 連接目錄 */
    public static final String CATALOG_TYPE_LINK = "1";
    public static final String CATALOG_STATUS_COMMON = "1";
    public static final String CATALOG_STATUS_TRANSFERORUNITE = "2";
    public static final String CATALOG_PROPERTY_NEWORMODIFY_LEVEL1 = "1";
    /** 語言標識--中文 */
    public static final String PARAMETER_LANCOD_zh_CN = "1";
    /** 語言標識--英文 */
    public static final String PARAMETER_LANCOD_en_US = "0";
    // **************************會員相關參數***********************************
    /**
     * 會員升級申請操作
     */
    public static String MEMBER_UPGRADE_ACTION_NEW = "0";
    public static String MEMBER_UPGRADE_ACTION_FREE = "1";
    public static String MEMBER_UPGRADE_ACTION_NO_FREE = "2";
    public static String MEMBER_RENEW_ACTION = "3";
    // 我也不知道這些是什麼
    public final static String MEMBER_SERVICE_TYPE_CHINASUPPLIER = "1";
    public final static String MEMBER_SERVICE_TYPE_GLOBETRADE = "2";
    public final static String MEMBER_SERVICE_TYPE_NO = "0";
    public final static String GLOBALTRADE_LEVEL_NONE = "0";
    /** 會員類型 */
    public final static String MEMBER_TYPE_VISITOR = "0";
    public final static String MEMBER_TYPE_CHINA_SUPPLIER = "1";
    public final static String MEMBER_TYPE_GLOBE_TRADER = "2";
    /** ******************會員級別定義數字索引************************ */
    // public final static String MEMBER_LEVEL_FOREIGN_FREEMEMBER_INDEX)= "8";
    public final static int MEMBER_LEVEL_VISITOR_INDEX = 9;
    public final static int CS_LEVEL_FREEMEMBER_INDEX = 0;
    public final static int CS_LEVEL_TDCMEMBER_INDEX = 5;
    public final static int CS_LEVEL_COPPERMEMBER_INDEX = 10;
    public final static int CS_LEVEL_SILVERMEMBER_INDEX = 20;
    public final static int CS_LEVEL_GOLDMEMBER_INDEX = 30;
    public final static int GT_LEVEL_FREEMEMBER_INDEX = 0;
    public final static int GT_LEVEL_COPPERMEMBER_INDEX = 10;
    public final static int GT_LEVEL_SILVERMEMBER_INDEX = 20;
    public final static int GT_LEVEL_GOLDMEMBER_INDEX = 30;
    /** ******************會員級別定義（字符串）************************ */
    /** 訪客 */
    public final static String MEMBER_LEVEL_VISITOR = "VISITOR";
    /** CS免費會員 */
    public final static String MEMBER_LEVEL_FREEMEMBER = "FREEMEMBER";
    /** CS COPPER會員 */
    public final static String MEMBER_LEVEL_COPPERMEMBER = "COPPERMEMBER";
    /** CS TDC會員 */
    public final static String MEMBER_LEVEL_TDCMEMBER = "TDCMEMBER";
    /** CS銀牌會員 */
    public final static String MEMBER_LEVEL_SILVERMEMBER = "SILVERMEMBER";
    /** CS金牌會員 */
    public final static String MEMBER_LEVEL_GOLDMEMBER = "GOLDMEMBER";
    /** 空 */
    public final static String GT_LEVEL_NONE = "NONE";
    /** 全球代理商 */
    public final static String GT_LEVEL_COPPERMEMBER = "GLOBAL_TRADER";
    /** GT銀牌會員 */
    public final static String GT_LEVEL_SILVERMEMBER = "SILVERMEMBER";
    /** GT金牌會員 */
    public final static String GT_LEVEL_GOLDMEMBER = "GOLDMEMBER";
    /** ******************會員升級用************************ */
    public final static String COMPANY_COUNTRY_CHINA = "China";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_PREFERENTIAL = "UPGRADE_PREFERENTIAL_FEE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_BASE_FEE = "UPGRADE_FEE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_MONTH_FEE = "UPGRADE_MONTH_FEE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_EXTEND2 = "UPGRADE_EXTEND2_FEE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_EXTEND3 = "UPGRADE_EXTEND3_FEE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_PREFERENTIAL_TIME = "UPGRADE_YOUHUI_TIME";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_DISCOUNT_TIME = "UPGRADE_DISCOUNT_TIME";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_DISCOUNT = "UPGRADE_DISCOUNT_RATE";
    public final static String SYSTEM_PARAMETER_TYPE_UPGRADE_MAX_REFUND_MONTH = "UPGRADE_MAX_REFUND_MONTH";
    // -----------------------------資源權限範圍配置-------------------------------
    public static final String RESOURCE_SCOPE_SQL = "sql://";
    public static final String RESOURCE_SCOPE_JAVA = "java://";
    // -----------------------------備注類型-------------------------------
    /** 銷售備注類型 */
    public final static String SALE_REMARK_TYPE = "1";
    /** 審核備注類型 */
    public final static String CHECK_REMARK_TYPE = "2";
    /** 客服備注類型 */
    public final static String CUSTOM_REMARK_TYPE = "3";
    /** 財務備注類型 */
    public final static String FINANCE_REMARK_TYPE = "4";
    /** 合同付款備注類型 */
    public final static String CONTRACT_PAYMENT_REMARK_TYPE = "5";
    // -----------------------------備注分類-------------------------------
    /** 合同付款備注信息 */
    public final static String CONTRACT_PAYMENT_REMARK_INFO = "1";
    /** 合同財務狀態備注 */
    public final static String CONTRACT_FINANCE_STATUS_REMARK = "2";
    /** 財務回款備注信息 */
    public final static String FINANCE_PAYMENT_REMARK_INFO = "3";
    // ------------------------------時間格式定義----------------------------------
    /** 日期時間格式 24小時制 */
    // public final static String DATE_TIME_PATTERN_SEP = "yyyy-mm-dd HH:mm:ss";
    /** 日期格式 */
    // public final static String DATE_PATTERN_SEP = "yyyy-mm-dd";
    /** 時間格式 24小時制 */
    // public final static String TIME_PATTERN_SEP = "HH:mm:ss";
    // -------------------------------excel文件頭------------------------------
    /** 打印信封文件頭 */
    public final static String PRINT_MAIL_HEAD =
            "he_tong_hao,shou_piao_ren,shou_piao_dan_wei,shou_piao_di_zhi,youbian,xiao_shou_ren_yuan,shi_jian";
    /** 日期 */
    public static final String DATEPATTERN_SEP = "yyyy-MM-dd";
    public static final String DATEPATTERN_NOSEP = "yyyyMMdd";
    public static final String TIMEPATTERN_SEP = "HH:mm:ss";
    public static final String TIMEPATTERN_NOSEP = "HHmmss";
    public static final String DATETIMEPATTERN_SEP = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIMEPATTERN_SEP_MI = "yyyy-MM-dd HH24:mi:ss";
    public static final String DATETIMEPATTERN_NOSEP = "yyyyMMddHHmmss";
    // 用戶級別
    /** 用戶級別:未定級 */
    public static final String CUSTOMER_LEVEL_NOT_DEFINED = "0";
    /** 用戶級別:撞單 */
    public static final String CUSTOMER_LEVEL_CRASH = "1";
    /** 用戶級別:放棄 */
    public static final String CUSTOMER_LEVEL_GIVE_UP = "2";
    /** 用戶級別:流失 */
    public static final String CUSTOMER_LEVEL_DISAPPRAR = "3";
    /** 用戶級別:未聯系 */
    public static final String CUSTOMER_LEVEL_NOT_CONTACTED = "4";
    /** 用戶級別:一般潛在 */
    public static final String CUSTOMER_LEVEL_NORMAL_LATENCY = "5";
    /** 用戶級別:優質潛在 */
    public static final String CUSTOMER_LEVEL_HIGH_GRADE_LATENCY = "6";
    /** 用戶級別:待簽 */
    public static final String CUSTOMER_LEVEL_WAIT_SIGN_UP = "7";
    /** 用戶級別:簽約 */
    public static final String CUSTOMER_LEVEL_SIGNED_UP = "8";
    /** 用戶級別:系統開放 */
    public static final String CUSTOMER_LEVEL_SYSTEM_OPEN = "9";
    // 用戶開放標志
    /** 用戶開放標志:未開放 */
    public static final String CUSTOMER_OPEN_FLAG_NOTOPEN = "0";
    /** 用戶開放標志:開放客戶 */
    public static final String CUSTOMER_OPEN_FLAG_OPEN = "1";
    // 支付sgs方式
    /** 支付sgs方式:百分比 */
    public static final String SGS_PAYMENT_TYPE_PERCENT = "1";
    /** 支付sgs方式:固定值 */
    public static final String SGS_PAYMENT_TYPE_FASTNESS_VALUE = "2";
    /** SGS分成方式 */
    public final static String SGS_APPORTION_PERCENT_TYPE = "1";
    // 金額匯總類型
    /** 金額匯總類型:AS */
    public static final String MONEY_COLLECT_TYPE_AS = "0";
    public static final String ADMIN_NAME = "SYSTEM";
    public static final String ADMIN_ID = "99999999";
    /** 轉帳狀態 0，初始狀態；1，付款成功； 2，付款失敗；3，付款延時 */
    public static String SGS_CONFIRM_START_STATUS = "0";
    public static String SGS_CONFIRM_SUCCESS_STATUS = "1";
    public static String SGS_CONFIRM_FAIL_STATUS = "2";
    public static String SGS_CONFIRM_TIMELAPSE_STATUS = "3";
    /** 對帳狀態 0，初始狀態；1，付款成功； 2，付款失敗；3，用戶退款 */
    public static String SGS_ACCOUNTS_START_STATUS = "0";
    public static String SGS_ACCOUNTS_SUCCESS_STATUS = "1";
    public static String SGS_ACCOUNTS_FAIL_STATUS = "2";
    public static String SGS_ACCOUNTS_REFUNDMENT_STATUS = "3";
    /** 在線支付 */
    public static String SGS_CONSTRACT_PART_B = "中國制造網有限公司";
    /** 在線支付 */
    public static String SGS_CONSTRACT_PARTUSER_B = "在線支付";
    /** 在線支付臨時使用人ID */
    public static String SGS_CONSTRACT_SAL_ID = "21500003";
    /** 在線支付臨時使用人在線支付臨時使用人 */
    public static String SGS_CONSTRACT_SAL_NAME = "馮娟";
    /** 在線支付臨時使用人ID */
    public static String SGS_CONSTRACT_CST_ID = "13000001";
    /** 在線支付臨時使用人在線支付臨時使用人 */
    public static String SGS_CONSTRACT_CST_NAME = "馮娟";
    /** 客戶類型:AR */
    public static final String CUSTOMER_TYPE_AUDIT_REPORTS = "9";
    // 用戶角色類型
    /** 銷售主管 */
    public static String USER_ROLE_SAL_DIRECTOR = "0";
    /** 銷售經理 */
    public static String USER_ROLE_SAL_MANAGER = "1";
    /** 業務拓展中心經理 */
    public static String USER_ROLE_EXPAND_CENTER_MANAGER = "2";
    // 產品類型
    /** TOKEN產品類型 */
    public static String PRODUCT_TYPE_TOKEN = "100";
    /** 套餐產品類型 */
    public static String PRODUCT_TYPE_TAOCAN = "0";
    /** 默認提醒次數:AR */
    public static final Long REMIND_TIMES = 1L;
    /** 默認執行狀態:AR */
    public static final String REMIND_EXE_ACTIVE = "0";
    public static final String REMIND_EXECUTE_ACTIVE = "1";
    /** 從CRM_REMIND中每次取5條:AR */
    public static Integer CRM_REMIND_MAX_RESULT = 5;
    // 郵件發送狀態
    /** 已發送 */
    public static String MAIL_SEND_FLAG_SUCC = "1";
    /** 未發送 */
    public static String MAIL_SEND_FLAG = "0";
    /** 發送失敗 */
    public static String MAIL_SEND_FLAG_FAIL = "-1";
    // 續定狀態
    /** 已續定 */
    public static String PROD_BOOKING_STATUS_HAS = "1";
    /** 未續定 */
    public static String PROD_BOOKING_STATUS_NO = "0";
    /** 系統管理員 */
    public static String SYSTEM_ADMIN = "SYSTEM_ADMIN";
    // 合同使用狀態
    /** 打印失敗 */
    public static String CONTRACT_USED_STATUS_FAIL = "-1";
    /** 初始取號 */
    public static String CONTRACT_USED_STATUS_INIT = "0";
    /** 打印成功 */
    public static String CONTRACT_USED_STATUS_SUCC = "1";
    /** 領取使用中 */
    public static String CONTRACT_USED_STATUS_USE = "2";
    /** 簽約寄回 */
    public static String CONTRACT_USED_STATUS_SEND = "3";
    /** 歸檔 */
    public static String CONTRACT_USED_STATUS_PASS = "4";
    /** 作廢寄回 */
    public static String CONTRACT_USED_STATUS_CANCEL = "9";
    // ******************************抓取數據處理所需要的常量************************//
    /** 未導入 */
    public static String IMPORT_FLAG_NO = "0";
    /** 導入成功 */
    public static String IMPORT_FLAG_YES = "1";
    /** 導入成功，但發生疑似撞單 */
    public static String IMPORT_FLAG_YES_MAYBE_CRASH = "2";
    /** 已操作，但發生撞單，導入失敗 */
    public static String IMPORT_FLAG_YES_CRASH = "3";
    // *******************************產品展台首頁相關變量***************************//
    // 產品展台首頁第一頁標簽產品類型ID
    public static String PROD_TYPE_FIRST_LABEL_EN = "6";
    // 產品展台首頁非第一頁標簽產品類型ID
    public static String PROD_TYPE_NON_FIRST_LABEL_EN = "136";
    // 產品展台中文首頁第一頁標簽產品類型ID
    public static String PROD_TYPE_FIRST_LABEL_CN = "1480";
    // 產品展台中文首頁非第一頁標簽產品類型ID
    public static String PROD_TYPE_NON_FIRST_LABEL_CN = "1481";
    // 產品展台首頁驗證虛擬內容
    public static String SPOTLIGHT_FIRST_LABEL_VIRTUAL_CONTENT = "SpotlightFirstLabel";
    /** 開放客戶︰特殊城市 */
    public static String OPEN_CUSTOMER_SPECIAL_CITY = "CITY_LIST_SAL_OPEN";
    // ======================客戶來源類型============================
    /** 普通 */
    public static final String ACCOUNT_ORIGIN_TYPE_COMMON = "1";
    /** 展會名片 */
    public static final String ACCOUNT_ORIGIN_TYPE_EXHIBITION = "2";
    /** MIC免費會員 */
    public static final String ACCOUNT_ORIGIN_TYPE_MIC = "3";
    /** 抓取數據 */
    public static final String ACCOUNT_ORIGIN_TYPE_FETCH = "4";
    /** 阿里客戶 */
    public static final String ACCOUNT_ORIGIN_TYPE_ALI = "7";
    /** 環球資源 */
    public static final String ACCOUNT_ORIGIN_TYPE_WORD = "8";
    // =====================EPAY付款狀態===============================
    /** 初始狀態 */
    public static final String PAYMENT_INIT_STATUS = "0";
    /** 付款成功 */
    public static final String PAYMENT_SUCCESS_STATUS = "1";
    /** 付款失敗 */
    public static final String PAYMENT_CANCEL_STATUS = "2";
    /** 付款延時 */
    public static final String PAYMENT_OVERTIME_STATUS = "3";
    // =====================EPAY對帳狀態===============================
    /** 未確定 */
    public static final String ACCOUNT_UNDETERMINED_STATUS = "0";
    /** 付款成功 */
    public static final String ACCOUNT_SUCCESS_STATUS = "1";
    /** 付款失敗 */
    public static final String ACCOUNT_CANCEL_STATUS = "2";
    /** 用戶退款 */
    public static final String ACCOUNT_REFUNDMENT_STATUS = "3";
    // =====================EPAY下單狀態===============================
    /** 初始狀態 */
    public static final String ORDERFORM_INIT_STATUS = "0";
    /** 下單成功 */
    public static final String ORDERFORM_SUCCESS_STATUS = "1";
    /** 下單失敗 */
    public static final String ORDERFORM_CANCEL_STATUS = "2";
    // =====================領動二級域名後綴===============================
    /** Leadong.com */
    public static final String LEADONG_DOMAIN_SUFFIX = ".Leadong.com";
    // =====================銀企直聯系統狀態===============================
    /** 銀行通訊成功 */
    public static final String BANK_COMMUNICATION_SUCCEED = "1";
    /** 銀行通訊失敗 */
    public static final String BANK_COMMUNICATION_FAILED = "0";
    /** 銀行交易類別:入賬 */
    public static final String BANK_TRADE_TYPE_ENTER = "1";
    /** 銀行交易類別:支付 */
    public static final String BANK_TRADE_TYPE_PAY = "2";
    /** 未完成對賬 */
    public static final String BANK_TRADE_NOT_SQUARED = "0";
    /** 完成對賬 */
    public static final String BANK_TRADE_SQUARED = "1";
    /** 銀行幣種 人民幣 */
    public static final String BANK_BILL_TYPE_RMB = "10";
    /** 銀行幣種 美圓 */
    public static final String BANK_BILL_TYPE_USD = "32";
    /** 系統對賬 */
    public static final String BANK_CONFIRM_SYSTEM_PAYMENT = "0";
    /** 人工對賬並回款 */
    public static final String BANK_CONFIRM_AND_PAYMENT = "1";
    /** 人工對賬且不回款 */
    public static final String BANK_CONFIRM_NOT_PAYMENT = "2";
    // ======================市場活動信息狀態==============================
    /** 鎖定，表示正在打印 */
    public static final String MARKET_ACTIVITY_INFO_FORBIDDEN = "0";
    /** 激活，表示數據正在準備 */
    public static final String MARKET_ACTIVITY_INFO_ACTIVE = "1";
    // ======================市場客戶信息狀態==============================
    /** 市場客戶信息狀態︰已刪除 */
    public static final String MARKET_CUSTOMER_INFO_DELETED = "0";
    /** 市場客戶信息狀態︰已審核 */
    public static final String MARKET_CUSTOMER_INFO_CHECKED = "1";
    /** 市場客戶信息狀態︰未審核 */
    public static final String MARKET_CUSTOMER_INFO_UNCHECKED = "2";
    // ======================詢盤處理狀態===============================
    /** 詢盤處理狀態︰未處理 */
    public static final String PROCESS_NOT = "0";
    /** 詢盤處理狀態︰已處理 */
    public static final String PROCESS_SUCC = "1";
    // ======================財務備注狀態===============================
    /** 不要提醒 */
    public static final String FINANCE_REMARK_NO_AWOKE = "0";
    /** 提醒 */
    public static final String FINANCE_REMARK_AWOKE = "1";
    /** 處理完成 */
    public static final String FINANCE_REMARK_PROCESS_COMPLETE = "2";
    /** 返回處理 */
    public static final String FINANCE_REMARK_RETURN_PROCESS = "3";
    /** 關閉 */
    public static final String FINANCE_REMARK_CLOSED = "4";
    // ======================共享狀態===============================
    /** 不共享 */
    public static final Long CONTACT_NOT_SHARE_FLAG = 0L;
    /** 共享 */
    public static final Long CONTACT_SHARE_FLAG = 1L;
    // =======================公司名稱==============================
    /** 焦點科技股份有限公司 */
    public static final String FOCUS_CHINA_COMPANY = "焦點科技股份有限公司";
    /** 中國制造網有限公司 */
    public static final String MADE_IN_CHINA_COMPANY = "中國制造網有限公司";
    /** 文筆網路科技有限公司 */
    public static final String TTNET_CHINA_COMPANY = "文筆網路科技有限公司";
    // ==========================證件類型=============================
    /** 公司營業執照 **/
    public static final String LICENCE_TYPE_COMPANY = "1";
    /** 個人身份證 **/
    public static final String LICENCE_TYPE_PERSONAL = "2";
    /** 其他 **/
    public static final String LICENCE_TYPE_OTHER = "3";
    // ==========================證件使用範圍=============================
    /** 簽約時使用 **/
    public static final String LICENCE_USE_SIGN_ON = "1";
    /** 發布時使用 **/
    public static final String LICENCE_USE_ISSUE = "2";
    /** 簽約發布都使用 **/
    public static final String LICENCE_USE_ALL = "3";
    // =========================撞單處理==============================
    /** 撞單日志發生的操作類型︰添加 */
    public static final String CRASH_LOG_OPERATE_TYPE_ADD = "1";
    /** 撞單日志發生的操作類型︰修改 */
    public static final String CRASH_LOG_OPERATE_TYPE_MODIFY = "2";
    /** 撞單日志發生的操作類型︰撞單檢查 */
    public static final String CRASH_LOG_OPERATE_TYPE_CHECK = "3";
    // ===========================撞單處理結果==========================
    /** 撞單處理的結果︰不做任何處理！ */
    public static final String CRASH_LOG_RESULT_TYPE_NOTHING = "1";
    /** 撞單處理的結果︰置為疑似撞單！ */
    public static final String CRASH_LOG_RESULT_TYPE_MAYBE = "2";
    /** 撞單處理的結果︰添加客戶活動信息！ */
    public static final String CRASH_LOG_RESULT_TYPE_MESSAGE = "3";
    /** 撞單處理的結果︰與多個撞單，添加疑似撞單客戶！ */
    public static final String CRASH_LOG_RESULT_TYPE_CUSTOMER = "4";
    // ===========================貨幣單位==========================
    //新台幣
    public static final Integer UNIT_TWD = 1;
    //美元
    public static final Integer UNIT_USD = 2;
    // ===========================退款狀態==========================
    //申請中
    public static final Integer REFUND_APPLY = 1;
    //已確認
    public static final Integer REFUND_CONFIRM = 2;
    //已取消
    public static final Integer REFUND_CANCEL = 3;
    //已退款
    public static final Integer REFUND_FINISH = 4;

    // ===========================服務狀態==========================
    //待發布
    public static final Integer SERVICE_AWAITING = 1;
    //發布中
    public static final Integer SERVICE_PUBLISHING = 2;
    //已暫停
    public static final Integer SERVICE_SUSPENDED = 3;
    //已終止
    public static final Integer SERVICE_DISCONTINUED = -1;
 // ===========================訂單狀態==========================
    //未付款
    public static final Integer ORDER_UNPAY = 1;
    //已付款
    public static final Integer ORDER_PAY = 2;
    //退款中
    public static final Integer ORDER_REFUNDING = 3;
    //已退款
    public static final Integer ORDER_REFUNDED = 4;
    //取消
    public static final Integer ORDER_CANCEL = 5;

    // =========================注冊資金單位==============================
    /** 元 */
    public static final String REG_UNIT_1 = "1";
    /** 萬 */
    public static final String REG_UNIT_2 = "2";
    // ==========================發送底單附件郵件其它接受人=================
    public static final String OTHER_EMAIL_RECEIVE = "-1";
    // =========================會員推廣標志============================
    /** 是 */
    public static final String IS_SPREAD_TRUE = "1";
    /** 否 */
    public static final String IS_SPREAD_FALSE = "0";
    /** 開放客戶角色控制︰需要區分區域的角色 **/
    public static final Long[] OPEN_CUTOMER_ROLES_0 =
            new Long[]{100800L, 100500L, 10007L, 10010L, 10008L, 101241L, 10002L, 100960L};
    /** 開放客戶角色控制︰直銷部助理 **/
    public static final Long[] OPEN_CUTOMER_ROLES_1 = new Long[]{10003L, 10006L};
    /** 開放客戶角色控制︰彭棟 **/
    public static final Long[] OPEN_CUTOMER_ROLES_2 = new Long[]{100560L};
    /** 開放客戶角色控制︰分公司其他的角色 **/
    public static final Long[] OPEN_CUTOMER_ROLES_3 = new Long[]{10005L, 100580L};

    /**
     * 展会平面图
     */
    public static final int EXHIBITION_PIC_TYPE = 1;
    /**
     * 场景加载图
     */
    public static final int SCENCE_PIC_TYPE = 2;

}
