package com.focustech.oss2008.service;

import java.util.List;
import java.util.Map;

public interface FocusVTable {
    void clear();

    int getColumnCount();

    int getDataLineCount();

    List getAllTitle();

    int getIndexByKeyValue(String keyValue);

    String getTitleByIndex(int index);

    String getValueByKeyValue(String keyValue);

    List getAllData();

    List getDataLineByIndex(int index);

    void addTitleColumn(String titleColumnName);

    void delTitleColumn(int titleColumnIndex);

    void addDataLine(List listData);

    Map getAllDataMap();

    /**
     * 得到當前數據記錄的位置
     *
     * @return true:當前記錄位置是有效，false:無效
     */
    boolean next();

    /**
     * 移支當前指針到結果的最前面
     */
    void beforeFirst();

    /**
     * 移動當前指針到當前結果的第一條
     *
     * @return true:如果當前有數據 ,false:沒有數據
     */
    boolean first();
}
