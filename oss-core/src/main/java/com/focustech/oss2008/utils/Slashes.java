package com.focustech.oss2008.utils;

/**
 * 類 Slashes 是一系列字符（串）替換的方法集合。
 */
public class Slashes {
    /**
     * 方法 replace 可以把源字符串中的指定字符串替換為其它字符串。
     *
     * @param strSource 替換前的字符串
     * @param strFrom 將被替換的字符串
     * @param strTo 將被替換成的字符串
     * @return 替換後的字符串
     */
    public static String replace(String strSource, String strFrom, String strTo) {
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;
        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }

    /**
     * 方法 toHtml 可以把源字符串中的不能在網頁中正確顯示的 字符替換為可以顯示的相應字符串。
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String toHtml(String strSource) {
        // char charInter='\n';
        char charLt = '<';
        char charGt = '>';
        char charQuot = '"';
        char charAmp = '&';
        StringBuffer StrBufReturn = new StringBuffer();
        for (int i = 0; i < strSource.length(); i++) {
            // if (strSource.charAt(i)==charInter)
            // StrBufReturn.append("<BR>");
            // else
            if (strSource.charAt(i) == charLt)
                StrBufReturn.append("&lt;");
            else if (strSource.charAt(i) == charGt)
                StrBufReturn.append("&gt;");
            else if (strSource.charAt(i) == charQuot)
                StrBufReturn.append("&quot;");
            else if (strSource.charAt(i) == charAmp)
                StrBufReturn.append("&amp;");
            else
                StrBufReturn.append(strSource.charAt(i));
        }
        return StrBufReturn.toString();
    }

    /**
     * 方法 ToHtml 為方法 toHtml 的重載。 目的為了兼容規範與前期設計時已使用過此方法。
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String ToHtml(String strSource) {
        return toHtml(strSource);
    }

    /**
     * 方法 toText 可以把源字符串中的在網頁中顯示時的 特殊字符替換為其在數據庫中本來的字符。
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String toText(String strSource) {
        String[] strFromArray = new String[5];
        strFromArray[0] = "&#59";
        strFromArray[1] = "<br>";
        strFromArray[2] = "&#40;";
        strFromArray[3] = "&#41;";
        strFromArray[4] = "&#37;";
        // strFromArray[0] = "&amp;";
        // strFromArray[1] = "&quot;";
        // strFromArray[2] = "&gt;";
        // strFromArray[3] = "&lt;";
        String[] strToArray = new String[5];
        strToArray[0] = "";
        strToArray[1] = "";
        strToArray[2] = "(";
        strToArray[3] = ")";
        strToArray[4] = "%";
        // strToArray[0] = "&";
        // strToArray[1] = "\"";
        // strToArray[2] = ">";
        // strToArray[3] = "<";
        for (int i = 0; i < 0; i++) {
            String strFrom = strFromArray[i];
            String strTo = strToArray[i];
            String strDest = "";
            int intFromLen = strFrom.length();
            int intPos;
            while ((intPos = strSource.indexOf(strFrom)) != -1) {
                strDest = strDest + strSource.substring(0, intPos);
                strDest = strDest + strTo;
                strSource = strSource.substring(intPos + intFromLen);
            }
            strDest = strDest + strSource;
            strSource = strDest;
        }
        return strSource;
    }

    /**
     * 添加斜杠
     *
     * @param inputStr 添加前的字符串
     * @return 添加後的字符串
     */
    public static String addSlashes(String inputStr) {
        inputStr = replace(inputStr, "\\", "\\\\");
        inputStr = replace(inputStr, "'", "\\'");
        inputStr = replace(inputStr, "\"", "\\\"");
        return inputStr;
    }

    /**
     * 移除斜杠
     *
     * @param inputStr 移除前的字符串
     * @return 移除後的字符串
     */
    public static String removeSlashes(String inputStr) {
        while (inputStr.indexOf("\\\"") != -1)
            inputStr = replace(inputStr, "\\\"", "\"");
        while (inputStr.indexOf("\\'") != -1)
            inputStr = replace(inputStr, "\\'", "'");
        inputStr = replace(inputStr, "\\\\", "\\");
        return inputStr;
    }

    /**
     * 把文字中的回車替換成<br>
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String replaceReturn(String strSource) {
        char charInter = '\n';
        StringBuffer StrBufReturn = new StringBuffer();
        for (int i = 0; i < strSource.length(); i++) {
            if (strSource.charAt(i) == charInter)
                StrBufReturn.append("<BR>");
            else
                StrBufReturn.append(strSource.charAt(i));
        }
        return StrBufReturn.toString();
    }

    /**
     * 把文字中的回車替換成<br>
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String replaceReturn(String strSource, boolean tohtml) {
        if (tohtml) {
            // strSource = replaceReturn(strSource);
            strSource = replace(strSource, "\n", "\n<BR>");
        }
        else {
            strSource = replace(strSource, "<br>", "\n");
            strSource = replace(strSource, "<BR>", "\n");
            strSource = replace(strSource, "\n\n", "\n");
        }
        return strSource;
    }

    /**
     * 把文字中的回車替換成<br>
     *
     * @param strSource 替換前的字符串
     * @return 替換後的字符串
     */
    public static String replaceSpace(String strSource, boolean tohtml) {
        if (tohtml) {
            strSource = replace(strSource, "  ", " &nbsp;");
            strSource = replace(strSource, "\t", " &nbsp;");
        }
        else {
            strSource = replace(strSource, "&nbsp;", " ");
        }
        return strSource;
    }
}
