package com.focustech.table.web.ext.extcomp;

import java.io.Serializable;

public class TableParameters implements Serializable {

    private int start;
    private int limit;
    private String[] names;
    private String[] operas;
    private String values[];
    private String[] sortableNamesAndValues;
    private String andOr;
    private String tableName;
    private int countPerPage;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getOperas() {
        return operas;
    }

    public void setOperas(String[] operas) {
        this.operas = operas;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String[] getSortableNamesAndValues() {
        return sortableNamesAndValues;
    }

    public void setSortableNamesAndValues(String[] sortableNamesAndValues) {
        this.sortableNamesAndValues = sortableNamesAndValues;
    }

    public String getAndOr() {
        return andOr;
    }

    public void setAndOr(String andOr) {
        this.andOr = andOr;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

}
