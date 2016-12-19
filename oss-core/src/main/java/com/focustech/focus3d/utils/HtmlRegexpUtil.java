package com.focustech.focus3d.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.focustech.common.utils.StringUtils;
/**
 * HTML 相关的正则表达式工具类
 * *
 *
 * @author lihaijun
 *
 */
public class HtmlRegexpUtil {

	private final static String REGXP_FOR_HTML = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
//	private final static String REGXB_FOR_IMGTAG = "<\\s*img\\s+([^>]*)\\s*>";// 找出IMG标签
//	private final static String REGXP_FOR_IMGTAGSRCATTR = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

	/**
	 * 过滤掉所有以"<>"开头和结尾的标签
	 * *
	 * @param str
	 * @return
	 */
	public static String filterHtml(String str){
		Pattern pattern = Pattern.compile(REGXP_FOR_HTML);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcher.find();
		while(result){
			matcher.appendReplacement(sb, "");
			result = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	/**
	 *
	 * *
	 * @param input
	 * @return
	 */
	public String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	/**
	 *
	 * *
	 * @param input
	 * @return
	 */
	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

    /**
     * 将html特殊字符转换
     */
    public static String escapeSpecialChars(String str) {
        return StringUtils.trimToEmpty(str).replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&#039;");
    }
    /**
     * 将html特殊字符转换
     */
    public static String unEscapeSpecialChars(String str) {
        return StringUtils.trimToEmpty(str).replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&#039;", "'");
    }

	public static void main(String[] args){
		String test1 = "abc<a>dsfdsfdsf</a><b>dfdfd</b>dfsfsdf<imag src=334343434343/>kk3333";
		filterHtml(test1);
	}
}
