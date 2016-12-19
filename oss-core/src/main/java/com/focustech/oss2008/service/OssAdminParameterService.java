package com.focustech.oss2008.service;

import java.util.List;

import com.focustech.oss2008.exception.service.MailSendErrorException;
import com.focustech.oss2008.model.OssAdminParameter;

/**
 * <li>参数组件</li>
 *
 * @author xufei 2008-4-17 上午11:50:14
 */
public interface OssAdminParameterService {
    /** 性別類型參數 */
    public static final String PARAM_TYPE_SEX = "PARAM_TYPE_SEX_TYPE";
    /** 業務狀態參數 */
    public static final String PARAM_TYPE_ACTIVE = "PARAM_TYPE_ACTIVE";
    /** 部門類型參數 */
    public static final String PARAM_TYPE_DEPART = "PARAM_TYPE_DEPART";
    /** 資源類型參數 */
    public static final String PARAM_TYPE_RESOURCE = "PARAM_TYPE_RESOURCE";
    /** 資源顯示參數 */
    public static final String PARAM_TYPE_DISPLAY = "PARAM_TYPE_DISPLAY";
    /** 產品價格單位 */
    public static final String PARAM_TYPE_PRICE_UNIT = "PARAM_TYPE_PRICE_UNIT";
    /** 產品價格周期單位 */
    public static final String PARAM_TYPE_PRICE_CYC_UNIT = "PARAM_TYPE_PRICE_CYCLE_UNIT";
    /** 客戶類型 */
    public static final String PARAM_TYPE_ACCOUNT_TYPE = "PARAM_TYPE_ACCOUNT_TYPE";
    /** 客戶級別 */
    public static final String PARAM_TYPE_ACCOUNT_LEVEL = "PARAM_TYPE_ACCOUNT_LEVEL";
    /** 客戶屬性 */
    public static final String PARAM_TYPE_ACCOUNT_PROPERTY = "PARAM_TYPE_ACCOUNT_PROPERTY";
    /** 銷售付款方式 */
    public static final String PARAM_TYPE_INVOICE_TYPE = "PARAM_TYPE_INVOICE_TYPE";
    /** 開票通知狀態 */
    public static final String PARAM_TYPE_INVOICE_NOTICE_STATUS = "PARAM_TYPE_INVOICE_NOTICE_STATUS";
    // jibin 2008-11-28
    /** 發票寄送狀態 */
    public static final String PARAM_TYPE_INVOICE_SEND_STATUS = "PARAM_TYPE_INVOICE_SEND_STATUS";
    /** 財務憑證標志 */
    public static final String PARAM_TYPE_PAYMENT_VOUCHER_FLAG = "PARAM_TYPE_PAYMENT_VOUCHER_FLAG";
    /** 財務狀態 */
    public static final String PARAM_TYPE_STATUS = "PARAM_TYPE_FINANCE_STATUS";
    /** 資源類型 */
    public static final String PARAM_TYPE_RESOURCE_TYPE = "PARAM_TYPE_RESOURCE_TYPE";
    /** 匯率 */
    public static final String PARAM_TYPE_EXCHANGE_RATE = "EXCHANGE_RATE";
    /** 貨幣類型 */
    public static final String PARAM_TYPE_MONEY_TYPE = "PARAM_TYPE_CURRENCY_UNIT";
    /** 手續費 */
    public static final String PARAM_TYPE_POUNDAGE = "POUNDAGE";
    /** 是否預定 */
    public static final String PROD_TYPE_ISBOOKED = "PROD_TYPE_ISBOOKED";
    /** 站點類型（所屬站點） */
    public static final String WEBSITE_TYPE = "SITE_CODE";
    /** 資源保護類型 */
    public static final String PROTECT_PERIOD_TYPE = "PARAM_TYPE_PROTECT_PERIOD_TYPE";
    /** 客戶聯系人類型 */
    public static final String PARAM_TYPE_CONTACT_TYPE = "PARAM_TYPE_CONTACT_TYPE";
    /** 付款類型 */
    public static final String PARAM_TYPE_PAYMENT_TYPE = "PARAM_TYPE_PAYMENT_TYPE";
    /** 財務回款類型 */
    public static final String PARAM_TYPE_FINANCE_PAYMENT_TYPE = "PARAM_TYPE_FINANCE_PAYMENT_TYPE";
    /** 發票郵寄方式 */
    public static final String PARAM_TYPE_INVOICE_MAILING_MODE = "PARAM_TYPE_INVOICE_MAILING_MODE";
    /** 合同客戶屬性 */
    public static final String PARAM_PROPERTY_CONCRACT_CUSTOMER = "PARAM_TYPE_CONTRACT_CUSTOMER_TYPE";
    /** 折扣類型 */
    public static final String PARAM_TYPE_DISCOUNT_TYPE = "PARAM_TYPE_DISCOUNT_TYPE";
    /** 合同關聯類型 */
    public static final String PARAM_TYPE_RELATED_CONTRACT = "PARAM_TYPE_RELATED_CONTRACT_TYPE";
    /** 稱呼 */
    public static final String PARAM_TYPE_SALUTATION = "PARAM_TYPE_CONTACT_SALUTATION";
    /** 銷售進度 */
    public static final String PARAM_TYPE_CONTACT_REASON = "PARAM_TYPE_CONTACT_REASON";
    /** 購買標識 */
    public static final String PARAM_TYPE_BUY_FLAG = "PARAM_TYPE_BUY_FLAG";
    /** 合同產品執行狀態 */
    public static final String PARAM_TYPE_EXECUTED_STATUS = "PARAM_TYPE_EXECUTED_STATUS";
    /** 合同產品標識 */
    public static final String PARAM_TYPE_PRODUCT_FLAG = "PARAM_TYPE_PRODUCT_FLAG";
    /** 預訂排名 */
    public static final String PARAM_TYPE_EXECUTE_POSITION = "PARAM_TYPE_EXECUTE_POSITION";
    /** 代理商審核狀態 */
    public static final String PARAM_TYPE_AGENT_ACCOUNT_AUDIT = "PARAM_TYPE_AGENT_ACCOUNT_AUDIT";
    /** 代理商付款類型 */
    public static final String PARAM_TYPE_AGENT_PAYMENT_TYPE = "PARAM_TYPE_AGENT_PAYMENT_TYPE";
    /** 代理商記錄類型 */
    public static final String PARAM_TYPE_AGENT_RECORD_TYPE = "PARAM_TYPE_AGENT_RECORD_TYPE";
    /** 國家類型 */
    public static final String PARAM_TYPE_COUNTRY = "COUNTRY_LIST";
    /** 省份類型 */
    public static final String PARAM_TYPE_PROVINCE = "PROVINCE_LIST";
    /** 城市類型 */
    public static final String PARAM_TYPE_CITY = "CITY_LIST";
    /** 城區類型 */
    public static final String PARAM_TYPE_CITYZONE = "CITYZONE_LIST";
    /** 展會名片中部門與地區對應關系 */
    public static String SAL_ORG_CITY_RELATIVE = "SAL_ORG_CITY_RELATIVE";
    /** 銷售郵件抄送地址 */
    public static final String PARAM_TYPE_SAL_COPY_MAIL_ADDRESS = "SAL_COPY_MAIL_ADDRESS";
    /** SGS付款類型 */
    public static final String PARAM_TYPE_SGS_PARAM_PROD_TYPE = "SGS_PARAM_PROD_TYPE";
    /** SGS對賬狀態 */
    public static final String PARAM_TYPE_PAYMENT_ACTIVE_TYPE = "SGS_PAYMENT_ACTIVE_TYPE";
    /** 財務備注狀態 */
    public static final String PARAM_TYPE_FINANCE_REMARK_TYPE = "PARAM_TYPE_FINANCE_REMARK";
    /** 客戶來源 */
    public static final String PARAM_TYPE_ORIGIN_TYPE = "PARAM_TYPE_ORIGIN_TYPE";
    /** 外部客戶簽約狀態 */
    public static final String PARAM_TYPE_ORIGIN_STATUS = "PARAM_TYPE_ORIGIN_STATUS";
    // ---------------------------------------MIC公司詳細頁面中使用------------------------------
    /** GT級別(全球供應商級別) */
    public static final String GLOBAL_TRADE_LEVEL = "GLOBAL_TRADE_LEVEL";
    /** CS會員級別︰按照中英文，分金、銀、銅三級 */
    String CS_MEMBER_LEVEL = "CS_LEVEL";
    /** MIC會員公司類型 */
    public static final String COMPANY_SERVICE_TYPE = "COMPANY_SERVICE_TYPE";
    /** 公司營業額 */
    public static final String COM_TURN_OVER = "COM_TURN_OVER";
    /** 公司員工人數類型 */
    public static final String EMPLOYEE_NUM_TYPE = "EMPLOYEE_NUM_TYPE";
    /** 邏輯是否 */
    public static final String PARAM_TYPE_LOGIC_TYPE = "PARAM_TYPE_LOGIC_TYPE";
    /** 會員級別 */
    public static final String PARAM_TYPE_CS_LEVEL = "CS_LEVEL";
    /** 時區類型 */
    public static final String PARAM_TYPE_TIMEZONE_LIST = "TIMEZONE_LIST";
    /** 審核狀態(即審核操作) */
    public static final String PARAM_TYPE_CHECK_OPERATE = "CHECK_OPERATE";
    /** 商情交易類型 */
    public static final String PARAM_TYPE_OFFER_TYPE = "PARAM_TYPE_OFFER_TYPE";
    /** 角色類型 */
    public static final String PARAM_TYPE_USER_ROLE = "PARAM_TYPE_USER_ROLE";
    /** MIC會員數據範圍限制的部門地區對應關系 */
    public static final String OFFICE_DEPARTMENT_REGION = "OFFICE_DEPARTMENT_REGION";
    /** 銀企直聯 幣種 */
    public static final String PARAM_TYPE_BANK_MONEY_TYPE = "BANK_MONEY_TYPE";
    /** 銀企直聯 入賬確認 */
    public static final String PARAM_TYPE_CONFIRM_BANK_FINANCE = "CONFIRM_BANK_FINANCE";
    // --------------------------------------顯示抓取的數據詳細信息頁面中使用-----------------------
    /** 客戶級別: 是否收費 */
    public static final String ROBERT_CUSTOMER_LEVEL_LIST = "ROBERT_CUSTOMER_LEVEL_LIST";
    /** 分配狀態︰是否已分配 */
    public static final String PARAMETER_PARAM_ALLOT_STATUS_LIST = "PARAMETER_PARAM_ALLOT_STATUS_LIST";
    /** 數據來源︰alibaba,環球資源等 */
    public static final String ROBERT_CUSTOMER_ORIGIN_LIST = "ROBERT_CUSTOMER_ORIGIN_LIST";
    /** 接收郵件人 */
    public static final String PARAMETER_KEY_SALEMAN_EMAIL_RECEIVER = "SALEMAN_EMAIL_RECEIVE";
    /** 抄收郵件人 */
    public static final String PARAMETER_KEY_SALEMAN_EMAIL_MAKE_COPY = "SALEMAN_EMAIL_MAKE_COPY";
    /** 日期格式 */
    public static final String PARAMETER_KEY_DATE_FORMAT = "DATE_FORMAT";
    // ---------------------------------------開放客戶配置-------------------------------------
    public static final String PARAMETER_TYPE_OPEN_CUSTOMER_NOOPEN_DEPARTMENT = "OPEN_CUSTOMER_NO_OPEN_DEPARTMENT";
    public static final String PARAMETER_TYPE_OPEN_CUSTOMER_NOOPEN_USER = "OPEN_CUSTOMER_NOOPEN_USER";
    public static final String PARAMETER_TYPE_OPEN_CUSTOMER_NOOPEN_ROLE = "OPEN_CUSTOMER_NOOPEN_ROLE";
    public static final String PARAMETER_TYPE_OPEN_CUSTOMER_ESPECIAL_CITY = "OPEN_CUSTOMER_ESPECIAL_CITY";
    // =======================================市場活動管理======================================
    /** 市場客戶信息審核狀態 */
    public static final String PARAM_TYPE_MARKET_STATUS = "PARAM_TYPE_STATUS";
    // =======================================領動用戶管理======================================
    /** 基礎產品號 */
    public static final String BASE_PRODUCT_CODE = "BASE_PRODUCT_CODE";
    /** 標準產品號 */
    public String STANDARD_PRODUCT_CODE = "STANDARD_PRODUCT_CODE";
    /** 域名地址 */
    public static final String BASE_PRODUCT_KEY_DOMAIN_ADDRESS = "DOMAIN_ADDRESS";
    /** 域名空間大小 */
    public static final String BASE_PRODUCT_KEY_DOMAIN_SPACE_CAPACITY = "DOMAIN_SPACE_CAPACITY";
    /** 帳號數量 */
    public static final String BASE_PRODUCT_KEY_DOMAIN_ACCOUNT_NUM = "DOMAIN_ACCOUNT_NUM";
    /** 銷售系統訪問網段控制 */
    public static final String PARAMETER_TYPE_IP_RANGE = "PARAM_TYPE_IP_RANGE";
    /** 領動續約日終錯誤發送郵件地址 */
    public static final String PARAMETER_TYPE_LEADONG_RELETED_MAIL = "PARAMETER_TYPE_LEADONG_RELETED_MAIL";
    /** 重合記錄條數限制類型 */
    public static final String PARAMETER_ADS_SUPERPOSE_RECORD_LIMIT = "ADS_SUPERPOSE_RECORD_LIMIT";
    // ======================================開放客戶管理======================================
    /** 開放客戶日終整合參數 **/
    public static final String PARAMETER_OPEN_CUSTOMER = "PARAM_TYPE_OPEN_CUSTOMER";
    /** 開放客戶日終整合間隔時間參數 **/
    public static final String PARAMETER_OPEN_CUSTOMER_SPLIT = "PARAM_TYPE_OPEN_CUSTOMER_SPLIT";
    /** 服務信息執行人員角色參數 **/
    public static final String PARAMETER_EXECUTOR_ROLES = "PARAM_TYPE_EXECUTOR_ROLES";
    // ======================================中英文目錄對應關系=====================================
    /** 中英文目錄對應關系參數 **/
    public static final String PARAMETER_CATEGORY = "PARAM_TYPE_CATEGORY";
    public static final String PARAMETER_STATUS_1 = "1";
    public static final String SITE_TYPE_1 = "1";
    public static final String EXBITION_STATUS = "EXB_STATUS";

    //======================================场景模型资源============================================
    /**模型资源类型*/
    public static final String SCENE_OBJECT_RESOURCE_TYPE = "SCENE_OBJECT_RESOURCE_TYPE";
    /**模型资源摆放位置类型*/
    public static final String SCENE_OBJECT_RESOURCE_POSITION_TYPE = "SCENE_OBJECT_RESOURCE_POSITION_TYPE";

    /**
     * 新增參數
     */
    public void addParameter(OssAdminParameter parameter);

    /**
     * 取得參數列表
     */
    public List<OssAdminParameter> getAllParameters();

    /**
     * 根據參數類型獲取參數信息集合
     */
    public List<OssAdminParameter> listParameters(String parameterType);

    /**
     * 根據參數類型,站點類型取得一條參數信息
     */
    public List<OssAdminParameter> listParameters(String parameterType, String siteType);

    /**
     * 修改一條參數信息
     */
    public void modifyParameter(OssAdminParameter parameter);

    /**
     * 根據參數類型、參數值和站點類型獲取參數
     *
     * @param parameterType 參數類型
     * @param parameterKey 參數值
     * @param website 站點類型
     * @return
     */
    public OssAdminParameter selectParameterByTypeKeyAndSite(String parameterType, String parameterKey, String website);
    /**
     *
     * *
     * @param parameterType
     * @param parameterKey
     * @return
     */
    public OssAdminParameter selectDefaultParameterByTypeKey(String parameterType, String parameterKey);

    /**
     * 合同結清時根據部門編號獲取收件人地址
     *
     * @throws MailSendErrorException 該合同已結清,合同對應的銷售人員不在當前發送銷售組當中,郵件無法發送！
     */
    public String getReceiverAddressByOrgNoSquare(String orgNo) throws MailSendErrorException;

    /**
     * 發送底單時根據部門編號獲取收件人地址
     *
     * @return parameterValue 當parameter為null時，返回"";
     */
    public String getReceiverAddressByOrgNoAttach(String orgNo);
    /**
     * 根据状态查询信息
     * *
     * @param parameterType 类型
     * @param status 状态
     * @param siteType 站点类型 默认1
     * @return
     */
    public List<OssAdminParameter> listParameters(String parameterType, String status, String siteType);
    /**
     * 通过类型删除记录
     * *
     * @param parameterType
     */
    public void deleteByParameterType(String parameterType);
    /**
     * 查询信息
     * *
     * @param parentParKey 父亲节点
     * @param parameterType 类型
     * @param status 状态
     * @param siteType 站点类型 默认1
     *
     * @return
     */
    public List<OssAdminParameter> listParameters(String parentParKey, String parameterType, String status, String siteType);

    /**
     * 根据父亲节点、类型、key、状态和站点类型 查询信息 *
     *
     * @param parentParKey 父亲节点
     * @param parameterType 类型
     * @param parameterKey
     * @param status 状态
     * @param siteType 站点类型 默认1
     * @return
     */
    public OssAdminParameter listParametersByTypeKeyParAndSite(String parentParKey, String parameterType,
            String parameterKey, String status, String siteType);
}
