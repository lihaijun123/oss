package com.focustech.oss2008.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * <li>Map比較類</li>
 *
 * @author tuminfei 2009-12-28 下午06:39:27
 */
public class ComparatorMap implements Comparator {
    private String mapKey = "";

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public int compare(Object arg0, Object arg1) {
        Map map0 = (Map) arg0;
        Map map1 = (Map) arg1;
        // 根據key取得比較的map value
        String compare0 = map0.get(getMapKey()).toString();
        String compare1 = map1.get(getMapKey()).toString();
        int flag = Integer.valueOf(compare0).compareTo(Integer.valueOf(compare1));
        return flag;
    }
}
