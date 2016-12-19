package com.focustech.table.web.ext.extcomp;

import net.sf.json.util.PropertyFilter;

import com.focustech.oss2008.model.TableConfig;

public class ColumnFilter implements PropertyFilter {
    private TableConfig tableConfig;

    public ColumnFilter(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public boolean apply(Object source, String name, Object value) {
        String absName = source.getClass().getSimpleName();
        String aname = absName.substring(absName.lastIndexOf(".") + 1, absName.length());
        String[] fieldNames = tableConfig.getFieldsNames();
        if (tableConfig.hasFieldName(name, fieldNames)) {
            return false;
        }
        return true;
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }
}
