package com.focustech.oss2008.model;

import java.util.ArrayList;

import com.focustech.table.web.ext.extcomp.SearchPanel;

/**
 * <li></li>
 *
 * @author yangpeng 2008-5-4 下午04:59:25
 */
public class Table {
    /*
     * 列表的總頁數
     */
    protected int totalPage;
    /*
     * 列表的表頭
     */
    protected ArrayList header;
    /*
     * 每頁幾條數據
     */
    protected int countsPerPage;
    /*
     * 列表的總的記錄數
     */

    protected int totalRecords;
    protected TableConfig tableConfig;
    protected int width = 800;
    protected int height = 300;
    protected SearchPanel searchPanel = new SearchPanel();

    /** 表格的HTML代碼 */
    protected String getHTML() {
        return "";
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList getHeader() {
        return header;
    }

    public void setHeader(ArrayList header) {
        this.header = header;
    }

    public int getCountsPerPage() {
        return countsPerPage;
    }

    public void setCountsPerPage(int countsPerPage) {
        this.countsPerPage = countsPerPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public void setSearchPanel(SearchPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

}
