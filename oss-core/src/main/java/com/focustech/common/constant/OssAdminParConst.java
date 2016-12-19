package com.focustech.common.constant;


/**
 * 对应oss_admin_parameter表中的常量 *
 *
 * @author lihaijun
 */
public class OssAdminParConst implements NumberConst {
    // 礼品类型
    public static final String POINT_GIFT_CLASS = "GIFT_CLASS_LIST";
    /** 图书/影视 */
    public static final String POINT_GIFT_CLASS_1 = ONE;
    /** 数码/家电 */
    public static final String POINT_GIFT_CLASS_2 = TWO;
    /** 家居/生活 */
    public static final String POINT_GIFT_CLASS_3 = THREE;
    /** 美容/护理 */
    public static final String POINT_GIFT_CLASS_4 = FOUR;
    /** 运动/健康 */
    public static final String POINT_GIFT_CLASS_5 = FIVE;
    /** 钟表/首饰 */
    public static final String POINT_GIFT_CLASS_6 = SIX;
    /** 鞋靴/服饰 */
    public static final String POINT_GIFT_CLASS_7 = SEVEN;
    /** 母婴/玩具 */
    public static final String POINT_GIFT_CLASS_8 = EIGHT;
    /** 箱包/礼品 */
    public static final String POINT_GIFT_CLASS_9 = NINE;
    /** 兑 换 券 */
    public static final String POINT_GIFT_CLASS_10 = TEN;
    // 礼品频道
    public static final String POINT_GIFT_CHANNELS = "GIFT_CHANNELS_LIST";
    /** 无 频 道 */
    public static final String POINT_GIFT_CHANNELS_1 = ONE;
    /** 兑换专区 */
    public static final String POINT_GIFT_CHANNELS_2 = TWO;
    /** 推荐礼品 */
    public static final String POINT_GIFT_CHANNELS_3 = THREE;
    /** 热门礼品 */
    public static final String POINT_GIFT_CHANNELS_4 = FOUR;
    // 快递公司
    public static final String POINT_DELIVERY_COMPANY = "DELIVERY_COMPANY_LIST";
    /** 圆通快递 */
    public static final String POINT_DELIVERY_COMPANY_1 = ONE;
    /** 天天快递 */
    public static final String POINT_DELIVERY_COMPANY_2 = TWO;
    /** 顺丰快递 */
    public static final String POINT_DELIVERY_COMPANY_3 = THREE;
    /** 申通快递 */
    public static final String POINT_DELIVERY_COMPANY_4 = FOUR;
    /** 汇通快递 */
    public static final String POINT_DELIVERY_COMPANY_5 = FIVE;
    /** 韵达快递 */
    public static final String POINT_DELIVERY_COMPANY_6 = SIX;
    // 礼品状态
    public static final String POINT_GIFT_STATUS = "GIFT_STATUS";
    /** 下架 */
    public static final String POINT_GIFT_STATUS_1 = ONE;
    /** 上架 */
    public static final String POINT_GIFT_STATUS_2 = TWO;
    /** 废除 */
    public static final String POINT_GIFT_STATUS_3 = THREE;
    // 发货状态
    public static final String POINT_DELIVERY_STATUS = "DELIVERY_STATUS";
    /** 未发货 */
    public static final String POINT_DELIVERY_STATUS_1 = ONE;
    /** 已发货 */
    public static final String POINT_DELIVERY_STATUS_2 = TWO;
    /** 已收货 */
    public static final String POINT_DELIVERY_STATUS_3 = THREE;
    /** 撤 销 */
    public static final String POINT_DELIVERY_STATUS_4 = FOUR;
    // 积分来源 1交易获得、2交易使用、3兑换礼品、4活动赠送、5好友赠送,6退单7.礼品不足8.礼品退货
    public static final String POINT_POINTS_SOURCE = "POINTS_SOURCE";
    /** 交易获得 */
    public static final String POINT_POINTS_SOURCE_1 = ONE;
    /** 交易使用 */
    public static final String POINT_POINTS_SOURCE_2 = TWO;
    /** 兑换礼品 */
    public static final String POINT_POINTS_SOURCE_3 = THREE;
    /** 活动赠送 */
    public static final String POINT_POINTS_SOURCE_4 = FOUR;
    /** 好友赠送 */
    public static final String POINT_POINTS_SOURCE_5 = FIVE;
    /** 退单 */
    public static final String POINT_POINTS_SOURCE_6 = SIX;
    /** 礼品不足 */
    public static final String POINT_POINTS_SOURCE_7 = SEVEN;
    /** 礼品退货 */
    public static final String POINT_POINTS_SOURCE_8 = EIGHT;
    public static final String POINT_POINTS_STATUS = "POINTS_STATUS";
    // 积分状态 1已发放、2已冻结、3已使用、4已过期
    /** 已发放 */
    public static final String POINT_POINTS_STATUS_1 = ONE;
    /** 已冻结 */
    public static final String POINT_POINTS_STATUS_2 = TWO;
    /** 已使用 */
    public static final String POINT_POINTS_STATUS_3 = THREE;
    /** 已过期 */
    public static final String POINT_POINTS_STATUS_4 = FOUR;

    // 积分赠送状态 状态：1:新建、2完成、3取消、4删除
    public static final String POINT_POINTS_PRESENT_STATUS = "POINTS_PRESENT_STATUS";
    /** 新建 */
    public static final String POINT_POINTS_PRESENT_STATUS_1 = ONE;
    /** 完成 */
    public static final String POINT_POINTS_PRESENT_STATUS_2 = TWO;
    /** 取消 */
    public static final String POINT_POINTS_PRESENT_STATUS_3 = THREE;
    /** 删除 */
    public static final String POINT_POINTS_PRESENT_STATUS_4 = FOUR;

    // 积分奖励方式 奖励方式：1比例、2固定额度、3 满多少送多少
    public static final String POINT_POINTS_REWARD_STATUS = "POINTS_REWARD_STATUS";
    /** 比例 */
    public static final String POINT_POINTS_REWARD_STATUS_1 = ONE;
    /** 固定额度 */
    public static final String POINT_POINTS_REWARD_STATUS_2 = TWO;
    /** 满多少送多少 */
    public static final String POINT_POINTS_REWARD_STATUS_3 = THREE;
    /** 默认的站点类型-1 */
    public static final String DEFAULT_WEB_SITE = ONE;
    /** 默认状态-1 */
    public static final String DEFAULT_STATUS = ONE;
    // 礼品操作类型：0 ：入库、1：出库
    public static final String POINT_CHANGE_TYPE = "POINTS_CHANGE_TYPE";
    /** 入库-1 */
    public static final String POINT_CHANGE_TYPE_0 = ZERO;
    /** 出库-1 */
    public static final String POINT_CHANGE_TYPE_1 = ONE;
    /** 展台类型 */
    public static final String EXB_DESK = "EXB_BOOTH_TYPE";
    /** 单位 */
    public static final String SALES_SERVICE_TYPE = "SALES_SERVICE_TYPE";

    /** 系统广播类型 */
    public static final String TYPE_CODE = "TYPE_CODE";
    /** 系统广播获取方式 */
    public static final String GET_WAY = "GET_WAY";
    /** 系统广播链接地址 */
    public static final String LINK_ADDRESS = "LINK_ADDRESS";
    /** 认证服务 */
    public static final String AUTHEN_SERVICE_LEVEL = "AUTHEN_SERVICE_LEVEL";
    /** 常用服务一级类型 */
    public static final String COMMON_SERVICE_ONE = "COMMON_SERVICE_TYPE";
    /** 常用服务 二级类型 */
    public static final String COMMON_SERVICE_TWO = "COMMON_SERVICE_NAME";
    /** 版本类型 */
    public static final String WEBSITE_1 = ONE;
    /** 广告一 级类型 */
    public static final String AD_SERVICE_ONE = "AD_KIND";
    /** 广告二 级类型 */
    public static final String AD_SERVICE_TWO = "AD_POSITION";
    /** 广告三 级类型 */
    public static final String AD_SERVICE_THREE = "AD_TYPE";
    /** 公司对外邮箱 */
    public static final String COMPANY_EMAIL_TYPE = "COMPANY_EMAIL_TYPE";

	/** 性別类型 */
	public static final String USER_SEX = "USER_SEX";

	/**
	 * 参数类型常量——城市
	 */
	public final static String PT_CITY_LIST = "CITY_LIST";

	/**
	 * 参数类型常量——省份
	 */

	public final static String PT_PROVINCE_LIST = "PROVINCE_LIST";

    /** 地图所在场景类型标识 */
    public static final String X3D_MAP_SCENE_TYPE = "X3D_MAP_SCENE_TYPE";

    /** 会议室名称 */
    public static final String MEETING_ROOM_LIST = "MEETING_ROOM_LIST";

    /** 展会图片链接类型 */
    public static final String EXB_PIC_TYPE = "EXB_PIC_TYPE";
}
