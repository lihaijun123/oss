package com.focustech.oss2008.utils;

import java.io.IOException;
import java.io.StringReader;

import freemarker.template.Template;

/**
 * <li>FreeMarker工具類</li>
 *
 * @author yangpeng 2008-8-4 下午05:18:54
 */
public class FreeMarkerUtils {
    public static Template createTemplate(String content) {
        StringReader reader = new StringReader(content);
        try {
            return new Template("", reader, null);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("content:" + content + " accour IOException!", e);
        }
    }
}
