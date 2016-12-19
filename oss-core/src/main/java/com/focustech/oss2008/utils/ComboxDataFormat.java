package com.focustech.oss2008.utils;

import java.util.List;
import java.util.Map;

public class ComboxDataFormat {
    /*
     * 功能︰將對象轉換為json格式 (non-Javadoc)
     * @see ext.format.DataFormat#format(java.util.List)
     */
    public String format(Map[] data, int index, String name) {
        // construct data
        String result = "[['','中國'],['','美國']]";
        StringBuffer sbf = new StringBuffer();
        // for 國家
        sbf.append("{index:" + index + ",data:");
        sbf.append(result);
        sbf.append("}");
        return sbf.toString();
    }

    /** 根據數組獲得選擇框的值 */
    public static String format(String[] data, int index) {
        // construct data
        String result = "";
        StringBuffer dataSbf = new StringBuffer();
        dataSbf.append("[");
        if (data == null) {

        }
        else {
            int len = data.length;
            if (len >= 1) {
                dataSbf.append("['m','" + data[0] + "']");
            }
            for (int i = 1; i < len; i++) {
                dataSbf.append(",['n','" + data[i] + "']");
            }

        }
        dataSbf.append("]");
        result = dataSbf.toString();
        StringBuffer sbf = new StringBuffer();
        // for 國家
        sbf.append("{index:" + index + ",data:");
        sbf.append(result);
        sbf.append("}");
        return sbf.toString();
    }

    /** 根據數組獲得選擇框的值 */
    public static String format(List<String[]> data, int index) {
        // construct data
        String result = "";
        StringBuffer dataSbf = new StringBuffer();
        dataSbf.append("[");
        if (data.size() == 0) {

        }
        else {
            int len = data.size();

            if (len >= 1) {
                String[] item = data.get(0);
                dataSbf.append("['" + item[0] + "','" + item[1] + "']");
            }
            for (int i = 1; i < len; i++) {
                String[] item = data.get(i);
                dataSbf.append(",['" + item[0] + "','" + item[1] + "']");
            }

        }
        dataSbf.append("]");
        result = dataSbf.toString();
        StringBuffer sbf = new StringBuffer();
        sbf.append("{index:" + index + ",data:");
        sbf.append(result);
        sbf.append("}");
        return sbf.toString();
    }
}
