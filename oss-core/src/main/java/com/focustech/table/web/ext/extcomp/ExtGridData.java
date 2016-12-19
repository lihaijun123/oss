package com.focustech.table.web.ext.extcomp;

import java.util.ArrayList;
import java.util.List;

public class ExtGridData {
    private int total;
    private List results = new ArrayList();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public void setData(List list) {
        this.results = list;
    }
}
