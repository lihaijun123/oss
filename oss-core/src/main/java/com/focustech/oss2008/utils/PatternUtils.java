package com.focustech.oss2008.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.oss2008.Constants;

/**
 * <li></li>
 *
 * @author yangpeng 2008-7-9 下午03:24:58
 */
public class PatternUtils {
    private static Log log = LogFactory.getLog(Constants.LOG_ROOT_SERVICE);
    private static Map<String, Pattern> patterns = new HashMap<String, Pattern>();

    // private final static PatternMatcher perl5Matcher = new Perl5Matcher();
    /**
     * perl5 pattern 驗證
     * <p>
     * 驗證目標字符串是否匹配源字符串
     *
     * @param resource 源
     * @param target 目標
     * @param arg
     * @throws IllegalArgumentException compile時發生錯誤
     */
    public static boolean isPerl5Match(String resource, String target, int arg) {
        // Pattern compiledPattern;
        // Perl5Compiler perl5Compiler = new Perl5Compiler();
        // try
        // {
        // compiledPattern = perl5Compiler.compile(resource, arg);
        // }
        // catch (MalformedPatternException mpe)
        // {
        // throw new IllegalArgumentException("Malformed regular expression: " + resource, mpe);
        // }
        // try
        // {
        // return perl5Matcher.matches(target, compiledPattern);
        // }
        // catch (ArrayIndexOutOfBoundsException mpe)
        // {
        // log.error("resource:" + resource + ";target:" + target + ";Pattern" + compiledPattern.getPattern());
        // throw mpe;
        // }
        Pattern pattern = patterns.get(resource);
        if (null == pattern) {
            pattern = Pattern.compile(resource);
            patterns.put(resource, pattern);
        }
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isJavaPatternMatch(String resource, String target) {
        Pattern pattern = patterns.get(resource);
        if (null == pattern) {
            pattern = Pattern.compile(resource);
            patterns.put(resource, pattern);
        }
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isJavaPatternFound(String resource, String target) {
        Pattern pattern = patterns.get(resource);
        if (null == pattern) {
            pattern = Pattern.compile(resource);
            patterns.put(resource, pattern);
        }
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static Pattern getPattern(String url) {
        return patterns.get(url);
    }
}
