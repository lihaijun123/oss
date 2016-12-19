package com.focustech.oss2008.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.FocusVTable;

@Service
public class FocusVTableImpl extends AbstractServiceSupport implements FocusVTable {
    /**
     * 用于存放Vtable的列頭
     */
    private List titleList = new ArrayList(5);
    /**
     * 用于存放Vtable的數據行
     */
    private List dataList = new ArrayList(5);
    /**
     * 用于存放數據行的Hashmap形式
     */
    private Map dataMap = new HashMap(20);
    /**
     * 表頭字段名索引
     */
    private Map mapTitleIndex = new HashMap(20);
    /**
	 *
	 */
    private int pos = -1;

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public List getAllTitle() {
        return titleList;
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public String getTitleByIndex(int index) {
        if (index < getColumnCount())
            return (String) titleList.get(index);
        else
            return "";
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public List getAllData() {
        return dataList;
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public List getDataLineByIndex(int index) {
        if (index < getDataLineCount()) {
            return (List) dataList.get(index);
        }
        else {
            return new Vector(1, 1);
        }
    }

    /**
     * 根據KEY得到KEY對應的VALUE
     *
     * @param keyValue 要得到值的KEY
     * @return 返回對應的VALUE
     */
    public String getValueByKeyValue(String keyValue) {
        String strRet = "";
        if (pos < 0 || pos >= getDataLineCount())
            throw new IndexOutOfBoundsException(String.valueOf(pos));
        List lisDataLine = (List) dataList.get(pos);
        strRet = (String) lisDataLine.get(getIndexByKeyValue(keyValue));
        return strRet;
    }

    /**
     * @return
     */
    public boolean next() {
        pos++;
        if (pos < 0 || pos >= getDataLineCount()) {
            return false;
        }
        return true;
    }

    public void beforeFirst() {
        pos = -1;
    }

    public boolean first() {
        if (getDataLineCount() <= 0)
            return false;
        pos = 0;
        return true;
    }

    public int getIndexByKeyValue(String keyValue) {
        String strIndex = (String) mapTitleIndex.get(keyValue);
        if (strIndex == null || "".equals(strIndex))
            throw new IndexOutOfBoundsException("invalid column name:" + keyValue);
        int iIndex = Integer.parseInt(strIndex);
        if (iIndex < 0)
            throw new IndexOutOfBoundsException("invalid column name:" + keyValue);
        return iIndex;
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public void addTitleColumn(String titleColumnName) {
        titleList.add(titleColumnName);
        mapTitleIndex.put(titleColumnName, String.valueOf(titleList.size() - 1));
        for (Iterator itr = dataList.iterator(); itr.hasNext();) {
            List dataLine = (List) itr.next();
            int iDataLineCount = dataLine.size();
            for (int i = 0; i < getColumnCount() - iDataLineCount; i++) {
                dataLine.add("");
            }
        }
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public void delTitleColumn(int titleColumnIndex) {
        titleList.remove(titleColumnIndex);
        for (Iterator itr = dataList.iterator(); itr.hasNext();) {
            List dataLine = (List) itr.next();
            try {
                dataLine.remove(titleColumnIndex);
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public void addDataLine(List listData) {
        if (getColumnCount() == listData.size()) {
            this.dataList.add(listData);
        }
        else if (getColumnCount() > listData.size()) {
            int iDataLineCount = listData.size();
            for (int i = 0; i < getColumnCount() - iDataLineCount; i++) {
                listData.add("");
            }
            this.dataList.add(listData);
        }
        else {
            int removeCount = listData.size() - getColumnCount();
            for (int i = 0; i < removeCount; i++) {
                listData.remove(listData.size() - 1);
            }
            this.dataList.add(listData);
        }
        // 生成Map型的數據
        if (getColumnCount() == 2) {
            dataMap.put(listData.get(0), listData.get(1));
        }
    }

    /**
     * 描述................. Create Time: 2004-7-9 12:57:37
     *
     * @author JimCheng/成俊杰
     */
    public void clear() {
        this.dataList.clear();
        this.titleList.clear();
        this.dataMap.clear();
    }

    /**
     * 描述................. Create Time: 2004-7-9 13:00:40
     *
     * @author JimCheng/成俊杰
     */
    public int getColumnCount() {
        return this.titleList.size();
    }

    /**
     * 描述................. Create Time: 2004-7-9 13:04:14
     *
     * @author JimCheng/成俊杰
     */
    public int getDataLineCount() {
        return this.dataList.size();
    }

    /**
     * 描述................. Create Time: 2004-8-10 17:29:08
     *
     * @author JimCheng/成俊杰
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        // sb.append(titleList.toString());
        Iterator iter = dataList.iterator();
        while (iter.hasNext()) {
            List oneLine = (List) iter.next();
            try {
                String key = (String) oneLine.get(0);
                sb.append(key);
                sb.append("\n");
            }
            catch (Exception e) {
            }
            try {
                String value = (String) oneLine.get(1);
                sb.append(value);
                sb.append("\n");
            }
            catch (Exception e) {
            }
        }
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.core.FocusVTable#getAllDataMap()
     */
    public Map getAllDataMap() {
        return dataMap;
    }
}
