package com.focustech.extend.uitool;
/**
 * mysql 排序
 * *
 * @author lihaijun
 *
 */
public class UIToolListOrderForMysql implements com.focustech.uitool.list.spi.UIToolListOrder {

	public static final String[] intFiledAry = new String[]{"CAPACITY", "prod_count", "PRIORITY"};

	@Override
	public String getOrderSql(String orderField, String orderOper) {
		if (isIntColumn(orderField)) {
			return " ORDER BY " + orderField + " " + orderOper;
		}
		return " ORDER BY convert(" + orderField + " using gbk) collate gbk_chinese_ci " + orderOper;
	}

    /**
     * 是否int类型列 *
     *
     * @param fieldInput
     * @return
     */
    public static boolean isIntColumn(String fieldInput) {
        for (String value : intFiledAry) {
            if (value.equals(fieldInput)) {
                return true;
            }
        }
        return false;
    }
}
