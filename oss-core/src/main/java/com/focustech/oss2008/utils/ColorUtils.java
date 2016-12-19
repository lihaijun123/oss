/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.oss2008.utils;

import java.awt.Color;

/**
 *  ColorUtils.java
 *
 * @author liushuyan
 */
public class ColorUtils {

    /**
     * 将十六进制的颜色标示转换为对应的Color对象
     * 
     * @param hex
     * @return
     */
    public static Color getColorFromHex(String hex) {
        try {
            return new Color(Integer.valueOf(hex.substring(1), 16));
        }
        catch (Exception e) {
            return Color.WHITE;
        }
    }
}
