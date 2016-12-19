package com.focustech.focus3d.common.codegenerate;

/**
 * 编号生产接口 *
 *
 * @author lihaijun
 */
public interface CodeGenerate {
    /** 通用id生成 */
    public static final String COMMON = "common";

	/** 产品 */
	public static final String PRODUCT = "product";
	/** 展会 */
	public static final String EXB = "exb";
	/** 模型 */
	public static final String MODEL = "model";
	/**
	 * 展馆
	 *
	 *
	 */
	public static final String EXBHALL = "exbhall";

	/**
	 * 展区
	 *
	 */
	public static final String EXBAREA = "exbarea";

	/**
	 * 会员
	 */
	public static final String MEMBER = "member";

	/** 广告 */
	public static final String AD = "ad";
	/** 广告 */
	public static final String AD_POS = "adpos";
	/** 黑名单 */
	public static final String BLACK_LIST = "blacklist";
	/** 敏感词 */
	public static final String SENSITIVE = "sensitive";

	/** lsy start */
	/** 群组 */
	public static final String CHATGROUPS = "chatgroups";
	/** 销售方案 */
	public static final String SALESSCHEME = "salesscheme";

	/** lsy end */

	/**
	 * *
	 *
	 * @return
	 */
	public String generate();

}
